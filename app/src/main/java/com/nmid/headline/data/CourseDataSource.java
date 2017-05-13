package com.nmid.headline.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.nmid.headline.data.bean.Course;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xwysu on 2017/4/8.
 */

public interface CourseDataSource {

    interface LoadCourseCallback{

        void onCourseLoaded(List<Course> courses,int nowWeek);

        void onDataNotAvailable();

    }

    void getCourseList(@NonNull LoadCourseCallback callback,String stuNum,String idNum);

    void getCourseOldList(@NonNull LoadCourseCallback callback);

    void saveCourseList(ArrayList<Course> courses);

    String getStuNum();

    void saveStuNum(String stuNum);

    int getNowWeek();

    void saveWeek(int nowWeek);

    List<Course> getTodayCourse();


}
