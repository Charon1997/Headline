package com.nmid.headline.data;

import android.support.annotation.NonNull;

import com.nmid.headline.data.bean.New;
import com.nmid.headline.data.source.remote.HeadlineHttpService;
import com.nmid.headline.data.source.remote.HttpMethods;

/**
 * Created by xwysu on 2017/4/8.
 */

public class NewsRepository implements NewsDataSource{

    private NewsRepository(){

    }
    private static class SingletonHolder{
        private static final NewsRepository sInstance=new NewsRepository();
    }
    public static NewsRepository getInstance(){
        return SingletonHolder.sInstance;
    }

    @Override
    public void getNews(@NonNull LoadNewsCallback callback, @NonNull String type, int lastId) {
        HttpMethods.getInstance().getNews(callback,lastId, NewsDataSource.DEFAULT_LIMIT,type);
    }

    @Override
    public void getNewDetail(@NonNull LoadDetailCallback callback, @NonNull String type, int id) {
        HttpMethods.getInstance().getNewDetail(callback,type,id);
    }

    @Override
    public void getNew(@NonNull GetNewCallback callback, int id, @NonNull String type) {

    }

    @Override
    public void refreshNews(@NonNull String type) {

    }

    @Override
    public void refreshAllNews() {

    }

    @Override
    public void deleteAllNews() {

    }

    @Override
    public void deleteNew(int id, @NonNull String type) {

    }

    @Override
    public void saveCollectionNew(New aNew) {

    }

    @Override
    public void getAllCollectionNews() {

    }
}
