package com.nmid.headline.detailwebview;

import com.nmid.headline.BasePresenter;
import com.nmid.headline.BaseView;

/**
 * Created by xwysu on 2016/12/4.
 */

public interface DetailWebViewContract {
    interface View extends BaseView<Presenter> {

        boolean isActive();

        void showBaseUrl(String url);

        void showHtml(String html);

        void showError();

        void showLoadingIndicator(boolean active);

    }
    interface Presenter extends BasePresenter {

        void loadHtml(String html);

    }
}
