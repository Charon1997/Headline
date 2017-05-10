package com.nmid.headline.data;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.nmid.headline.data.bean.Course;
import com.nmid.headline.data.source.remote.CourseHttp;
import com.nmid.headline.util.ACache;
import com.nmid.headline.util.AppContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xwysu on 2017/4/8.
 */

public class CourseRepository implements CourseDataSource{

    private final String KEY_COURSE="courseList";

    private final String KEY_STUNUM="stuNum";

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

    @Override
    public int getNowWeek() {
        return 1;
    }

    private static class SingletonHolder{
        private static final CourseRepository sInstance=new CourseRepository();
    }

}
