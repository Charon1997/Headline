package com.nmid.headline.detailwebview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.nmid.headline.R;
import com.nmid.headline.data.NewsRepository;
import com.nmid.headline.data.bean.New;
import com.nmid.headline.util.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by xwysu on 2016/12/4.
 * 详情，webView
 */

public class DetailWebViewActivity extends AppCompatActivity {


    //bundle传参
    public static final String BUNDLE_NEW = "news";
    public static final String BUNDLE_URL = "url";
    public static final String BUNDLE_TYPE = "type";
    static final int TYPE_TEXT = 0;
    static final int TYPE_URL = 1;
    @BindView(R.id.web_container)
    ConstraintLayout webContainer;
    DetailWebViewPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        int type=getIntent().getIntExtra(BUNDLE_TYPE,-1);
        switch (type){
            case TYPE_TEXT:
                New aNew=getIntent().getParcelableExtra(BUNDLE_NEW);
                checkNotNull(aNew);
                DetailWebViewFragment fragment=(DetailWebViewFragment)getSupportFragmentManager().
                        findFragmentByTag(DetailWebViewFragment.class.getSimpleName());
                if (fragment==null){
                    fragment=DetailWebViewFragment.newInstance();
                    ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                            fragment,R.id.web_container,DetailWebViewFragment.class.getSimpleName());
                }
                mPresenter=new DetailWebViewPresenter(aNew, NewsRepository.getInstance(),fragment);
                break;
            case TYPE_URL:
                String url=getIntent().getStringExtra(BUNDLE_URL);
                checkNotNull(url);
                break;
            case -1:
                Toast.makeText(this,"内部错误",Toast.LENGTH_SHORT).show();
                break;
        }
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
