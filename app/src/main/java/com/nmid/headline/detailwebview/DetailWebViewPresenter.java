package com.nmid.headline.detailwebview;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.nmid.headline.data.NewsDataSource;
import com.nmid.headline.data.NewsRepository;
import com.nmid.headline.data.bean.New;
import com.nmid.headline.util.ACache;
import com.nmid.headline.util.ActivityUtils;
import com.nmid.headline.util.AppContext;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by xwysu on 2016/12/4.
 */

public class DetailWebViewPresenter implements DetailWebViewContract.Presenter{
    NewsRepository newsRepository;
    DetailWebViewContract.View view;
    New aNew;
    String url;
    int type;
    boolean floatBarStatus=false;
    ACache mAcache=ACache.get(AppContext.getContext());
    public DetailWebViewPresenter(@NonNull New aNew, @NonNull NewsRepository repository,@NonNull DetailWebViewContract.View detailWebView){
        checkNotNull(aNew);
        checkNotNull(repository);
        checkNotNull(detailWebView);
        this.aNew=aNew;
        newsRepository=repository;
        view=detailWebView;
        type=DetailWebViewActivity.TYPE_TEXT;
        view.setPresenter(this);
    }
    public DetailWebViewPresenter(@Nullable String url, @NonNull NewsRepository repository, @NonNull DetailWebViewContract.View detailWebView){
        checkNotNull(url);
        checkNotNull(repository);
        checkNotNull(detailWebView);
        this.url=url;
        newsRepository=repository;
        view=detailWebView;
        type=DetailWebViewActivity.TYPE_URL;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        if (type==DetailWebViewActivity.TYPE_TEXT){
            loadHtml(aNew.getType(),aNew.getNewsPid());
            if (view.isActive()){
                view.setFloatBarVisible(View.VISIBLE);
                floatBarStatus=newsRepository.isSavedFavorite(aNew);
                view.showFloatBarStatus(floatBarStatus);
            }
        }else {
            if (view.isActive()){
                view.showBaseUrl(url);
            }
        }
    }
    private void loadHtml(@Nullable String type,int id){
        newsRepository.getNewDetail(new NewsDataSource.LoadDetailCallback() {
            @Override
            public void onDetailLoad(String html) {
                checkNotNull(html);
                if (view.isActive()){
                    view.showHtml(html);
                }
            }

            @Override
            public void onDataNotAvailable() {
                Log.e("DetailHttpFail","HttpFail");
            }
        },type,id);
    }

    @Override
    public void loadHtml() {
        loadHtml(aNew.getType(),aNew.getNewsPid());
    }

    @Override
    public void loadUrl() {
        view.showBaseUrl(url);
    }

    @Override
    public void operateFavoriteNew() {
        if (floatBarStatus){
            newsRepository.deleteFavorite(aNew);
        }else {
            newsRepository.saveFavorite(aNew);
        }
        floatBarStatus=!floatBarStatus;
        if (view.isActive()){
            view.showFloatBarStatus(floatBarStatus);
        }
    }
}
