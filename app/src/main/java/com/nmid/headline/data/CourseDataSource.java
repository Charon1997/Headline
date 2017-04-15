package com.nmid.headline.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by xwysu on 2017/4/8.
 */

public interface CourseDataSource {

    interface LoadCourseCallback{

        void onCourseLoaded();

        void onDataNotAvailable();

    }

    void getCourseList(@NonNull LoadCourseCallback callback,String stuNum,String idNum);



}
