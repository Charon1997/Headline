package com.nmid.headline.about;

import android.content.Intent;
import android.net.Uri;

/**
 * Created by xwysu on 2016/12/4.
 */

public class AboutPresenter implements AboutContract.Presenter{

    AboutContract.View mView;
    public AboutPresenter(AboutContract.View view){
        mView=view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void joinUs() {
        Uri uri = Uri.parse("http://202.202.43.205/");
        if (mView.isActive()){
            mView.openWeb(uri);
        }
    }

    @Override
    public void checkUpdate() {

    }
}
