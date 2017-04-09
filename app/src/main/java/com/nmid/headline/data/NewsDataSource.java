package com.nmid.headline.data;

import android.support.annotation.NonNull;

import com.nmid.headline.data.bean.New;

import java.util.List;

/**
 * Created by xwysu on 2017/4/8.
 */

public interface NewsDataSource {

    //type
    String TYPE_JINGWEI="a";

    String TYPE_NEWS="b";

    int FIRST_REQUEST=-1;

    int DEFAULT_LIMIT=15;

    interface LoadNewsCallback {

        void onNewsLoaded(List<New> news);

        void onDataNotAvailable();
    }

    interface GetNewCallback {

        void onNewLoaded(New aNew);

        void onDataNotAvailable();
    }

    void getNews(@NonNull LoadNewsCallback callback,@NonNull String type,int lastId);

    void getNew(@NonNull GetNewCallback callback,int id,@NonNull String type);

    void refreshNews(@NonNull String type);

    void refreshAllNews();

    void deleteAllNews();

    void deleteNew(int id,@NonNull String type);

    void saveCollectionNew(New aNew);

    void getAllCollectionNews();

}
