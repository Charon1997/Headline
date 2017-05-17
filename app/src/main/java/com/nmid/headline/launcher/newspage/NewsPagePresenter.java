package com.nmid.headline.launcher.newspage;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.nmid.headline.data.NewsDataSource;
import com.nmid.headline.data.NewsRepository;
import com.nmid.headline.data.bean.New;
import com.nmid.headline.launcher.newspage.NewsPageContract;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by xwysu on 2017/4/9.
 */

public class NewsPagePresenter implements NewsPageContract.Presenter {

    private final NewsRepository mNewsRepository;
    private final NewsPageContract.View mNewsView;
    private boolean mFirstLoad = true;
    //recordId用来记录最后的PID，是实际上的lastId，因为MVP的特性，这样写好一些
    int recordId=0;
    String type;

    public NewsPagePresenter(@NonNull NewsRepository newsRepository, @NonNull NewsPageContract.View view,String type){
        mNewsRepository=checkNotNull(newsRepository);
        mNewsView=checkNotNull(view);
        mNewsView.setPresenter(this);
        this.type=type;
    }
    @Override
    public void start() {
        loadNews();
    }

    @Override
    public void loadNews() {
        recordId=0;
        loadNews(NewsDataSource.FIRST_REQUEST);
    }
    private void loadNews(int lastId){
        Log.d("lastid","lastid "+lastId+" recordID "+recordId);
        mNewsRepository.getNews(new NewsDataSource.LoadNewsCallback() {
            @Override
            public void onNewsLoaded(List<New> news) {
                checkNotNull(news);
                if (!news.isEmpty()){
                    recordId=news.get(news.size()-1).getNewsPid();
                }
                if (mNewsView.isActive()){
                    if (lastId>0){
                        mNewsView.showMoreNews(news);
                    }else {
                        mNewsView.showNews(news);
                    }
                }

            }

            @Override
            public void onDataNotAvailable() {
                if (mNewsView.isActive()){
                    mNewsView.showError();
                }
            }
        },type,lastId);
    }

    @Override
    public void loadOldNews() {

    }

    @Override
    public void loadMoreNews() {
        loadNews(recordId);
    }

    @Override
    public void openNewDetail(New aNew) {
        mNewsView.showNewDetail(aNew);
    }
}
