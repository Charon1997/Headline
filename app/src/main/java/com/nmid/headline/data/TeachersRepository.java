package com.nmid.headline.data;

import android.support.annotation.NonNull;

import com.nmid.headline.data.source.local.ACache;
import com.nmid.headline.data.source.remote.HttpMethods;
import com.nmid.headline.util.AppContext;

/**
 * Created by xwysu on 2017/4/8.
 */

public class TeachersRepository implements TeachersDataSource{
    ACache mAcache=ACache.get(AppContext.getContext());

    private TeachersRepository(){

    }
    private static class SingletonHolder{
        private static final TeachersRepository sInstance=new TeachersRepository();
    }
    public static TeachersRepository getInstance(){
        return SingletonHolder.sInstance;
    }
    @Override
    public void getTeachers(@NonNull LoadTeachersCallback callback, int id, int limit) {
        HttpMethods.getInstance().getTeachers(callback,id,limit);
    }

    @Override
    public void getTeacherDetail(@NonNull LoadDetailCallback callback, int id) {
        HttpMethods.getInstance().getTeacherDetail(callback,id);
    }
}
