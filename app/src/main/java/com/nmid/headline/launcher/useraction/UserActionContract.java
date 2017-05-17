package com.nmid.headline.launcher.useraction;

import com.nmid.headline.BasePresenter;
import com.nmid.headline.BaseView;

/**
 * Created by xwysu on 2016/12/4.
 * 最后一部分，用户相关操作
 */

public interface UserActionContract {
    interface View extends BaseView<Presenter> {

        boolean isActive();

        void showAboutPage();

        void showFavoritePage();

        void showCourseList();


    }
    interface Presenter extends BasePresenter {

        void openCourseList();

        void openFavoritePage();

        void openAboutPage();

    }
}
