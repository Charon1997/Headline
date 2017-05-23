package com.nmid.headline.data.source.remote;

import android.support.annotation.NonNull;
import android.util.Log;

import com.nmid.headline.data.CourseDataSource;
import com.nmid.headline.data.bean.Course;
import com.nmid.headline.data.bean.CourseResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by xwysu on 2017/4/24.
 */

public class CourseHttp {

    private Retrofit retrofit;
    private CourseHttpService httpService;

    private CourseHttp(){
        retrofit=new Retrofit.Builder()
                .baseUrl(CourseHttpService.END_POINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        httpService=retrofit.create(CourseHttpService.class);
    }
    private static class SingletonHolder{
        private static final CourseHttp sInstance=new CourseHttp();
    }
    public static CourseHttp getInstance(){
        return SingletonHolder.sInstance;
    }

    public void  getCourseList(@NonNull CourseDataSource.LoadCourseCallback callback,String stuNum,String stuId){
        Call<CourseResult<Course>> call=httpService.getCourse(stuNum,stuId);
        call.enqueue(new Callback<CourseResult<Course>>() {
            @Override
            public void onResponse(Call<CourseResult<Course>> call, Response<CourseResult<Course>> response) {
                checkNotNull(response.body());
                callback.onCourseLoaded(response.body().getData(),response.body().getNowWeek());
            }

            @Override
            public void onFailure(Call<CourseResult<Course>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
