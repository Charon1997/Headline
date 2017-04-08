package com.nmid.headline.launcher.newspage.newslist;

import com.nmid.headline.BasePresenter;
import com.nmid.headline.BaseView;
import com.nmid.headline.data.bean.New;

import java.util.List;

/**
 * Created by xwysu on 2016/12/4.
 * 第一部分，主要是新闻
 */

public interface NewsListContract {
    interface View extends BaseView<Presenter>{

        boolean isActive();

        void showOldnews(List<New> oldNews);

        void showError();

        void showNews(List<New> news);

        void showMoreNews(List<New> addNews);

        void showNewDetail(New item);

    }
    interface Presenter extends BasePresenter{

        void loadNews();

        void loadOldNews();

        void loadMoreNews(int lastId);

        void openNewDetail(New item);
    }
}
