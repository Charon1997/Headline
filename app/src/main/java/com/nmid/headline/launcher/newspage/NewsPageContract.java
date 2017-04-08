package com.nmid.headline.launcher.newspage;

import android.app.Fragment;

import com.nmid.headline.BasePresenter;
import com.nmid.headline.BaseView;

import java.util.List;

/**
 * Created by xwysu on 2016/12/4.
 * 第一部分，主要是新闻
 */

public interface NewsPageContract {
    interface View extends BaseView<Presenter>{

        boolean isActive();

        void showDefaultFra();

        void showError();

        void showTab();

        void showFragments(List<Fragment> fragments);

    }
    interface Presenter extends BasePresenter{

    }
}
