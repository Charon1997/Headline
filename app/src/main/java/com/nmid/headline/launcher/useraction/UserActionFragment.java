package com.nmid.headline.launcher.useraction;

import android.support.v4.app.Fragment;

/**
 * Created by xwysu on 2016/12/4.
 */

public class UserActionFragment extends Fragment implements UserActionContract.View{
    @Override
    public void setPresenter(UserActionContract.Presenter presenter) {

    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void showUserInfo() {

    }

    @Override
    public void showLoginPage() {

    }

    @Override
    public void showCourseList() {

    }

    @Override
    public void showCirclePage() {

    }
}
