package com.nmid.headline.data;

import android.support.annotation.NonNull;

import com.nmid.headline.data.bean.New;

/**
 * Created by xwysu on 2017/4/8.
 */

public class NewsRepository implements NewsDataSource{
    @Override
    public void getNews(@NonNull LoadNewsCallback callback, @NonNull String type, int lastId) {

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
