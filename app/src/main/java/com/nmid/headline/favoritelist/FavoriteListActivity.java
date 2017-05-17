package com.nmid.headline.favoritelist;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.nmid.headline.R;
import com.nmid.headline.about.AboutFragment;
import com.nmid.headline.about.AboutPresenter;
import com.nmid.headline.data.NewsDataSource;
import com.nmid.headline.data.NewsRepository;
import com.nmid.headline.launcher.newspage.NewsPageFragment;
import com.nmid.headline.launcher.newspage.NewsPagePresenter;
import com.nmid.headline.util.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteListActivity extends AppCompatActivity {

    @BindView(R.id.favorite_container)
    ConstraintLayout favoriteContainer;

    NewsPageFragment fragment;
    NewsPagePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_list);
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setTitle(R.string.favorite);
        fragment= NewsPageFragment.newInstance(NewsDataSource.TYPE_FAVORITE);
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),fragment,R.id.favorite_container,getClass().getSimpleName());
        presenter=new NewsPagePresenter(NewsRepository.getInstance(),fragment,NewsRepository.TYPE_FAVORITE);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:// 点击返回图标事件
                this.finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
