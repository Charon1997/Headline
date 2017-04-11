package com.nmid.headline.data.source.remote;

import android.support.annotation.NonNull;

import com.nmid.headline.data.NewsDataSource;
import com.nmid.headline.data.bean.HttpResult;
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
        Call<HttpResult<New>> call=httpService.getNews(id,limit,type);
        call.enqueue(new Callback<HttpResult<New>>() {
            @Override
            public void onResponse(Call<HttpResult<New>> call, Response<HttpResult<New>> response) {
                checkNotNull(response.body());
                if (response.body().getCode()==HeadlineHttpService.STATUS_OK){
                    callback.onNewsLoaded(response.body().getData());
                }else {
                    callback.onDataNotAvailable();
                }

            }

            @Override
            public void onFailure(Call<HttpResult<New>> call, Throwable t) {
                callback.onDataNotAvailable();
            }
        });
    }
}
