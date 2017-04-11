package com.nmid.headline.data.source.remote;

import android.support.annotation.NonNull;

import com.nmid.headline.data.NewsDataSource;
import com.nmid.headline.data.bean.Content;
import com.nmid.headline.data.bean.HttpResults;
import com.nmid.headline.data.bean.New;

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
                callback.onDataNotAvailable();
            }
        });
    }
    public void getNewDetail(@NonNull NewsDataSource.LoadDetailCallback callback,String type,int id){
        Call<HttpResults<Content>> call=httpService.getNewDetail(id,type);
        call.enqueue(new Callback<HttpResults<Content>>() {
            @Override
            public void onResponse(Call<HttpResults<Content>> call, Response<HttpResults<Content>> response) {
                checkNotNull(response.body());
                if (response.body().getCode()==HeadlineHttpService.STATUS_OK){
                    callback.onDetailLoad(response.body().getData().get(0).getContent());
                }else {
                    callback.onDataNotAvailable();
                }

            }

            @Override
            public void onFailure(Call<HttpResults<Content>> call, Throwable t) {

            }
        });

    }
}
