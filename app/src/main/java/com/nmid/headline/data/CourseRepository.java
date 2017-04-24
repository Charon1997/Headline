package com.nmid.headline.data;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.nmid.headline.data.source.remote.CourseHttp;

/**
 * Created by xwysu on 2017/4/8.
 */

public class CourseRepository implements CourseDataSource{

    private CourseRepository(){
    }

    public static CourseRepository getInstance() {
        return SingletonHolder.sInstance;
    }

    @Override
    public void getCourseList(@NonNull LoadCourseCallback callback, String stuNum, String idNum) {
        CourseHttp.getInstance().getCourseList(callback,stuNum,idNum);
    }

    private static class SingletonHolder{
        private static final CourseRepository sInstance=new CourseRepository();
    }

}
