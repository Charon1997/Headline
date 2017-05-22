package com.nmid.headline.data;

import android.support.annotation.NonNull;

import com.nmid.headline.data.bean.Teacher;

import java.util.List;

/**
 * Created by xwysu on 2017/4/8.
 */

public interface TeachersDataSource {

    int FIRST_REQUEST=-1;

    int DEFAULT_LIMIT=15;

    interface LoadTeachersCallback{

        void onTeachersLoad(List<Teacher> teachers);

        void onDataNotAvailable();

    }
    interface LoadDetailCallback{

        void onDetailLoad(String html);

        void onDataNotAvailable();
    }

    void getTeachers(@NonNull LoadTeachersCallback callback,int id,int limit);

    void getTeacherDetail(@NonNull LoadDetailCallback callback,int id);

}
