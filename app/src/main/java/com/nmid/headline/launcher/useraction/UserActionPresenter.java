package com.nmid.headline.launcher.useraction;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.nmid.headline.data.CourseRepository;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by xwysu on 2016/12/4.
 */

public class UserActionPresenter implements UserActionContract.Presenter{

    private final UserActionContract.View mUserActionView;

    public UserActionPresenter(@NonNull UserActionContract.View view){
        mUserActionView =checkNotNull(view);
        mUserActionView.setPresenter(this);
    }

    @Override
    public void start() {

    }


    @Override
    public void openCourseList() {
        if (mUserActionView.isActive()){
            mUserActionView.showCourseList();
        }
    }

    @Override
    public void openFavoritePage() {
        if (mUserActionView.isActive()){
            mUserActionView.showFavoritePage();
        }
    }

    @Override
    public void openAboutPage() {
        if (mUserActionView.isActive()){
            mUserActionView.showAboutPage();
        }
    }


}
