package com.nmid.headline.data.source.remote;

import android.support.annotation.NonNull;

import com.nmid.headline.data.NewsDataSource;
import com.nmid.headline.data.TeachersDataSource;
import com.nmid.headline.data.bean.HttpResults;
import com.nmid.headline.data.bean.New;
import com.nmid.headline.data.bean.Teacher;
import com.nmid.headline.launcher.teacherlist.TeacherListContract;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by xwysu on 2017/4/10.
 */

public class HttpMethods {

    private Retrofit retrofit;
    private HeadlineHttpService httpService;

    private HttpMethods(){
        retrofit=new Retrofit.Builder()
                .baseUrl(HeadlineHttpService.END_POINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        httpService=retrofit.create(HeadlineHttpService.class);
    }
    private static class SingletonHolder{
        private static final HttpMethods sInstance=new HttpMethods();
    }
    public static HttpMethods getInstance(){
        return SingletonHolder.sInstance;
    }

    public void getNews(@NonNull NewsDataSource.LoadNewsCallback callback, int id, int limit, String type){
        Call<HttpResults<New>> call=httpService.getNews(id,limit,type);
        call.enqueue(new Callback<HttpResults<New>>() {
            @Override
            public void onResponse(Call<HttpResults<New>> call, Response<HttpResults<New>> response) {
                checkNotNull(response.body());
                if (response.body().getCode()==HeadlineHttpService.STATUS_OK){
                    callback.onNewsLoaded(response.body().getData());
                }else {
                    callback.onDataNotAvailable();
                }

            }

            @Override
            public void onFailure(Call<HttpResults<New>> call, Throwable t) {
                t.printStackTrace();
                callback.onDataNotAvailable();
            }
        });
    }
    public void getNewDetail(@NonNull NewsDataSource.LoadDetailCallback callback,String type,int id){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(HeadlineHttpService.END_POINT+
                "/Headline/api/news/content?id="+id+"&type="+type).build();
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                e.printStackTrace();
                callback.onDataNotAvailable();
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                checkNotNull(response);
                callback.onDetailLoad(response.body().string());
            }
        });

    }
    public void getTeachers(@NonNull TeachersDataSource.LoadTeachersCallback callback, int id, int limit){
        Call<HttpResults<Teacher>> call=httpService.getTeachers(id,limit);
        call.enqueue(new Callback<HttpResults<Teacher>>() {
            @Override
            public void onResponse(Call<HttpResults<Teacher>> call, Response<HttpResults<Teacher>> response) {
                checkNotNull(response.body());
                if (response.body().getCode()==HeadlineHttpService.STATUS_OK){
                    callback.onTeachersLoad(response.body().getData());
                }else {
                    callback.onDataNotAvailable();
                }
            }

            @Override
            public void onFailure(Call<HttpResults<Teacher>> call, Throwable t) {
                t.printStackTrace();
                callback.onDataNotAvailable();
            }
        });
    }
    public void getTeacherDetail(@NonNull TeachersDataSource.LoadDetailCallback callback,int id){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(HeadlineHttpService.END_POINT+
                "/Headline/api/teacherInfo/getResume?id="+id).build();
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                e.printStackTrace();
                callback.onDataNotAvailable();
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                checkNotNull(response);
                callback.onDetailLoad(response.body().string());
            }
        });

    }
}
