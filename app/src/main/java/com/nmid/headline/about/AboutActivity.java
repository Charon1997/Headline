package com.nmid.headline.about;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.nmid.headline.R;
import com.nmid.headline.util.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xwysu on 2016/12/4.
 * 关于
 */

public class AboutActivity extends AppCompatActivity {
    @BindView(R.id.about_container)
    LinearLayout aboutContainer;
    AboutFragment fragment;
    AboutPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setTitle(R.string.about);
        fragment=AboutFragment.newInstance();
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),fragment,R.id.about_container,getClass().getSimpleName());
        presenter=new AboutPresenter(fragment);
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
