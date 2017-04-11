package com.nmid.headline.detailwebview;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.nmid.headline.data.NewsDataSource;
import com.nmid.headline.data.NewsRepository;
import com.nmid.headline.data.bean.New;
import com.nmid.headline.util.ActivityUtils;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by xwysu on 2016/12/4.
 */

public class DetailWebViewPresenter implements DetailWebViewContract.Presenter{
    NewsRepository newsRepository;
    DetailWebViewContract.View view;
    New aNew;
    String url;
    String type;
    public DetailWebViewPresenter(@NonNull New aNew, @NonNull NewsRepository repository,@NonNull DetailWebViewContract.View detailWebView){
        checkNotNull(aNew);
        checkNotNull(repository);
        checkNotNull(detailWebView);
        this.aNew=aNew;
        newsRepository=repository;
        view=detailWebView;
        type=DetailWebViewActivity.BUNDLE_NEW;
    }
    public DetailWebViewPresenter(@Nullable String url, @NonNull NewsRepository repository, @NonNull DetailWebViewContract.View detailWebView){
        checkNotNull(aNew);
        checkNotNull(repository);
        checkNotNull(detailWebView);
        this.url=url;
        newsRepository=repository;
        view=detailWebView;
        type=DetailWebViewActivity.BUNDLE_URL;
    }

    @Override
    public void start() {
        if (type.equals(DetailWebViewActivity.BUNDLE_NEW)){
            loadHtml(aNew.getType(),aNew.getNewsPid());
        }else {
            view.showBaseUrl(url);
        }
    }
    private void loadHtml(@Nullable String type,int id){
        newsRepository.getNewDetail(new NewsDataSource.LoadDetailCallback() {
            @Override
            public void onDetailLoad(String html) {
                view.showHtml(html);
            }

            @Override
            public void onDataNotAvailable() {

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
}
