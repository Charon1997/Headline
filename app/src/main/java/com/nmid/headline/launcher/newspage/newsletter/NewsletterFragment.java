package com.nmid.headline.launcher.newspage.newsletter;

import android.support.v4.app.Fragment;

import com.nmid.headline.data.bean.New;
import com.nmid.headline.launcher.newspage.NewsPageContract;

import java.util.List;

/**
 * Created by xwysu on 2017/4/9.
 */

public class NewsletterFragment extends Fragment implements NewsPageContract.View {
    @Override
    public void setPresenter(NewsPageContract.Presenter presenter) {

    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void showLoadingIndicator(boolean active) {

    }

    @Override
    public void showEmptyError() {

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
