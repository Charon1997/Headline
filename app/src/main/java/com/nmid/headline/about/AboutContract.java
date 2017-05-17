package com.nmid.headline.about;

import android.net.Uri;

import com.nmid.headline.BasePresenter;
import com.nmid.headline.BaseView;

/**
 * Created by xwysu on 2016/12/4.
 */

public interface AboutContract {
    interface View extends BaseView<Presenter> {
        boolean isActive();

        void openWeb(Uri uri);

    }
    interface Presenter extends BasePresenter {
        void joinUs();

        void checkUpdate();
    }
}
