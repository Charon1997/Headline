package com.nmid.headline.data;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.nmid.headline.data.bean.Course;
import com.nmid.headline.data.source.remote.CourseHttp;
import com.nmid.headline.util.ACache;
import com.nmid.headline.util.AppContext;


import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDate;
import org.threeten.bp.Period;
import org.threeten.bp.temporal.ChronoUnit;
import org.threeten.bp.temporal.TemporalAdjuster;
import org.threeten.bp.temporal.TemporalAdjusters;
import org.threeten.bp.temporal.TemporalUnit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xwysu on 2017/4/8.
 */

public class CourseRepository implements CourseDataSource{

    private final String KEY_COURSE="courseList";

    private final String KEY_STUNUM="stuNum";

    private final String KEY_FIRSTDAY="firstDay";

    ACache mACache;

    private CourseRepository(){
        mACache=ACache.get(AppContext.getContext());
    }

    public static CourseRepository getInstance() {
        return SingletonHolder.sInstance;
    }

    @Override
    public void getCourseList(@NonNull LoadCourseCallback callback, String stuNum, String idNum) {
        CourseHttp.getInstance().getCourseList(callback,stuNum,idNum);
    }

    @Override
    public void getCourseOldList(@NonNull LoadCourseCallback callback) {
        List<Course> result=(ArrayList<Course>)mACache.getAsObject(KEY_COURSE);
        if (result==null){
            callback.onDataNotAvailable();
        }else {
            callback.onCourseLoaded(result,getNowWeek());
        }
    }

    @Override
    public void saveCourseList(ArrayList<Course> courses) {
        mACache.put(KEY_COURSE,courses);
    }

    @Override
    public String getStuNum() {
        return mACache.getAsString(KEY_STUNUM);
    }

    @Override
    public void saveStuNum(String stuNum) {
        mACache.put(KEY_STUNUM,stuNum);
    }
    /*
       这部分原本打算使用JAVA8的新时间类，但是google无权使用JDK，屏蔽了JDK里的java,time.*
       按照google推荐引入了https://github.com/JakeWharton/ThreeTenABP的第三方库，用法完全一致
     */
    @Override
    public int getNowWeek() {
        int result=-1;
        LocalDate now=LocalDate.now();
        LocalDate first=(LocalDate) mACache.getAsObject(KEY_FIRSTDAY);
        if (first!=null){
            result= (int) first.until(now, ChronoUnit.DAYS);
            result=result/7+1;
        }
        return result;
    }

    @Override
    public void saveWeek(int nowWeek) {
        LocalDate now=LocalDate.now();
        LocalDate date=now.minusDays(now.getDayOfWeek().getValue()-1).minusWeeks(nowWeek-1);
        mACache.put(KEY_FIRSTDAY,date);
    }

    private static class SingletonHolder{
        private static final CourseRepository sInstance=new CourseRepository();
    }

}
