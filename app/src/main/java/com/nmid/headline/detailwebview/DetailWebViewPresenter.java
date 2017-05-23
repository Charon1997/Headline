package com.nmid.headline.detailwebview;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.nmid.headline.data.NewsDataSource;
import com.nmid.headline.data.NewsRepository;
import com.nmid.headline.data.TeachersDataSource;
import com.nmid.headline.data.TeachersRepository;
import com.nmid.headline.data.bean.New;
import com.nmid.headline.data.bean.Teacher;
import com.nmid.headline.data.source.local.ACache;
import com.nmid.headline.util.AppContext;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by xwysu on 2016/12/4.
 * 包含了新闻详情和教师详情
 */

public class DetailWebViewPresenter implements DetailWebViewContract.Presenter{
    NewsRepository newsRepository;
    TeachersRepository teachersRepository;
    DetailWebViewContract.View view;
    New aNew;
    Teacher teacher;
    String url;
    int type;
    boolean floatBarStatus=false;
    public DetailWebViewPresenter(@NonNull New aNew, @NonNull NewsRepository newsRepository,@NonNull DetailWebViewContract.View detailWebView){
        checkNotNull(aNew);
        checkNotNull(newsRepository);
        checkNotNull(detailWebView);
        this.aNew=aNew;
        this.newsRepository=newsRepository;
        view=detailWebView;
        type=DetailWebViewActivity.TYPE_NEW;
        view.setPresenter(this);
    }
    public DetailWebViewPresenter(@NonNull Teacher teacher, @NonNull TeachersRepository teachersRepository, DetailWebViewContract.View detailWebView){
        checkNotNull(teacher);
        checkNotNull(detailWebView);
        checkNotNull(teachersRepository);
        this.teacher=teacher;
        this.teachersRepository=teachersRepository;
        view=detailWebView;
        type=DetailWebViewActivity.TYPE_TEACHER;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        switch (type){
            case DetailWebViewActivity.TYPE_NEW:
                loadNewsHtml(aNew.getType(),aNew.getNewsPid());
                if (view.isActive()){
                    view.setFloatBarVisible(View.VISIBLE);
                    floatBarStatus=newsRepository.isSavedFavorite(aNew);
                    view.showFloatBarStatus(floatBarStatus);
                }
                break;
            case DetailWebViewActivity.TYPE_TEACHER:
                loadTeacherHtml(teacher.getTeacherInfoPid());
                if (view.isActive()){
                    view.setFloatBarVisible(View.VISIBLE);
                    view.showFloatBar();
                }
                break;
        }
    }
    private void loadNewsHtml(@Nullable String type,int id){
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
            }
        },type,id);
    }
    private void loadTeacherHtml(int id){
        teachersRepository.getTeacherDetail(new TeachersDataSource.LoadDetailCallback() {
            @Override
            public void onDetailLoad(String html) {
                checkNotNull(html);
                if (view.isActive()){
                    view.showHtml(html);
                }
            }

            @Override
            public void onDataNotAvailable() {
            }
        },id);
    }

    @Override
    public void loadHtml() {
        switch (type){
            case DetailWebViewActivity.TYPE_NEW:
                loadNewsHtml(aNew.getType(),aNew.getNewsPid());
                break;
            case DetailWebViewActivity.TYPE_TEACHER:
                loadTeacherHtml(teacher.getTeacherInfoPid());
                break;
        }
    }

    @Override
    public void loadUrl() {
        view.showBaseUrl(url);
    }

    @Override
    public void clickEvent() {
        switch (type){
            case DetailWebViewActivity.TYPE_NEW:
                if (floatBarStatus){
                    newsRepository.deleteFavorite(aNew);
                }else {
                    newsRepository.saveFavorite(aNew);
                }
                floatBarStatus=!floatBarStatus;
                if (view.isActive()){
                    view.showFloatBarStatus(floatBarStatus);
                }
                break;
            case DetailWebViewActivity.TYPE_TEACHER:
                Uri uri=Uri.parse(teacher.getHomePage());
                if (view.isActive()){
                    view.openUri(uri);
                }
                break;
        }
    }

}
