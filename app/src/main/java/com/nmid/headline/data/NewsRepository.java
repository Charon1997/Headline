package com.nmid.headline.data;

import android.support.annotation.NonNull;

import com.nmid.headline.data.bean.New;
import com.nmid.headline.data.source.remote.HttpMethods;
import com.nmid.headline.data.source.local.ACache;
import com.nmid.headline.util.AppContext;

import java.util.ArrayList;

/**
 * Created by xwysu on 2017/4/8.
 */

public class NewsRepository implements NewsDataSource{


    ACache mAcache=ACache.get(AppContext.getContext());

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
        if (type.equals(NewsDataSource.TYPE_FAVORITE)){
            if (lastId==NewsDataSource.FIRST_REQUEST){
                getFavoriteNews(callback);
            }else {
                callback.onNewsLoaded(new ArrayList<>());
            }
        }else {
            HttpMethods.getInstance().getNews(callback,lastId, NewsDataSource.DEFAULT_LIMIT,type);
        }
    }

    @Override
    public void getNewDetail(@NonNull LoadDetailCallback callback, @NonNull String type, int id) {
        HttpMethods.getInstance().getNewDetail(callback,type,id);
    }

    @Override
    public void getFavoriteNews(@NonNull LoadNewsCallback callback) {
        ArrayList<New> savedNews=(ArrayList<New>) mAcache.getAsObject(NewsDataSource.TYPE_FAVORITE);
        if (savedNews!=null){
            callback.onNewsLoaded(savedNews);
        }else {
            callback.onDataNotAvailable();
        }
    }


    @Override
    public boolean isSavedFavorite(New aNew) {
        ArrayList<New> savedNews=(ArrayList<New>) mAcache.getAsObject(NewsDataSource.TYPE_FAVORITE);
        if (savedNews!=null&&!savedNews.isEmpty()){
            for (New n:savedNews
                 ) {
                if (n.getTitle().equals(aNew.getTitle())){
                    return true;
                }
            }
            return false;
        }else{
            return false;
        }
    }

    @Override
    public void saveFavorite(New aNew) {
        ArrayList<New> savedNews=(ArrayList<New>) mAcache.getAsObject(NewsDataSource.TYPE_FAVORITE);
        if (savedNews!=null&&!savedNews.isEmpty()){
            savedNews.add(0,aNew);
        }else{
            savedNews=new ArrayList<New>();
            savedNews.add(aNew);
        }
        mAcache.put(NewsDataSource.TYPE_FAVORITE,savedNews);
    }

    @Override
    public void deleteFavorite(New aNew) {
        ArrayList<New> savedNews=(ArrayList<New>) mAcache.getAsObject(NewsDataSource.TYPE_FAVORITE);
        if (savedNews!=null&&!savedNews.isEmpty()){
            for (int i=0;i<savedNews.size();i++){
                if (savedNews.get(i).getTitle().equals(aNew.getTitle())){
                    savedNews.remove(i);
                    break;
                }
            }
        }
        mAcache.put(NewsDataSource.TYPE_FAVORITE,savedNews);
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
