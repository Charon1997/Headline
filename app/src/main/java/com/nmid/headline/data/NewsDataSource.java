package com.nmid.headline.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.nmid.headline.data.bean.New;

import java.util.List;

/**
 * Created by xwysu on 2017/4/8.
 */

public interface NewsDataSource {

    //type
    String TYPE_JINGWEI="a";

    String TYPE_NEWS="b";

    String TYPE_FAVORITE="favorite";

    int FIRST_REQUEST=-1;

    int DEFAULT_LIMIT=15;

    interface LoadNewsCallback {

        void onNewsLoaded(List<New> news);

        void onDataNotAvailable();
    }

    interface LoadDetailCallback{

        void onDetailLoad(String html);

        void onDataNotAvailable();
    }

    void getNews(@NonNull LoadNewsCallback callback,@NonNull String type,int lastId);

    void getNewDetail(@NonNull LoadDetailCallback callback,@NonNull String type,int id);

    void getFavoriteNews(@NonNull LoadNewsCallback callback);

    boolean isSavedFavorite(New aNew);

    void saveFavorite(New aNew);

    void deleteFavorite(New aNew);

    void refreshNews(@NonNull String type);

    void refreshAllNews();

    void deleteAllNews();

    void deleteNew(int id,@NonNull String type);

    void saveCollectionNew(New aNew);

    void getAllCollectionNews();

}
