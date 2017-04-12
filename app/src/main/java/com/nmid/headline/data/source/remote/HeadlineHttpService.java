package com.nmid.headline.data.source.remote;

import com.nmid.headline.data.bean.HttpResults;
import com.nmid.headline.data.bean.New;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by xwysu on 2017/4/11.
 */

public interface HeadlineHttpService {
    String END_POINT="http://139.199.3.238:8080";
    String ID = "id";
    String TYPE="type";
    String LIMIT = "limit";
    int STATUS_OK=200;
    int STATUS_ERROR=500;

    @GET("/Headline/api/news/fresh")
    Call<HttpResults<New>> getNews(
                    @Query(ID) int id, @Query(LIMIT) int limit, @Query(TYPE) String type);
    @GET("/Headline/api/news/content")
    Call<String> getNewDetail(
            @Query(ID) int id, @Query(TYPE) String type);
}
