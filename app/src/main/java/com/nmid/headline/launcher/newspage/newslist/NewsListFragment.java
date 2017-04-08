package com.nmid.headline.launcher.newspage.newslist;

import com.nmid.headline.data.bean.New;

import java.util.List;

/**
 * Created by xwysu on 2016/12/4.
 */

public class NewsListFragment implements NewsListContract.View{
    @Override
    public void setPresenter(NewsListContract.Presenter presenter) {

    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void showOldnews(List<New> oldNews) {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showNews(List<New> news) {

    }

    @Override
    public void showMoreNews(List<New> addNews) {

    }

    @Override
    public void showNewDetail(New item) {

    }
}
