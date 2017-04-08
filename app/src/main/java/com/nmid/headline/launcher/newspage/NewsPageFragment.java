package com.nmid.headline.launcher.newspage;

import android.app.Fragment;

import java.util.List;

/**
 * Created by xwysu on 2016/12/4.
 */

public class NewsPageFragment implements NewsPageContract.View{
    @Override
    public void setPresenter(NewsPageContract.Presenter presenter) {

    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void showDefaultFra() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showTab() {

    }

    @Override
    public void showFragments(List<Fragment> fragments) {

    }
}
