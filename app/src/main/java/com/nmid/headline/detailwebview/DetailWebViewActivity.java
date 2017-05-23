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
import com.nmid.headline.data.TeachersRepository;
import com.nmid.headline.data.bean.New;
import com.nmid.headline.data.bean.Teacher;
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
    public static final String BUNDLE_TEACHER= "teacher";
    public static final String BUNDLE_TYPE = "type";
    public static final int TYPE_NEW = 0;
    public static final int TYPE_TEACHER = 1;
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
            case TYPE_NEW:
                actionBar.setTitle(R.string.detail_news);
                New aNew= (New) getIntent().getSerializableExtra(BUNDLE_NEW);
                checkNotNull(aNew);
                DetailWebViewFragment newsFragment=(DetailWebViewFragment)getSupportFragmentManager().
                        findFragmentByTag(BUNDLE_NEW);
                if (newsFragment==null){
                    newsFragment=DetailWebViewFragment.newInstance();
                    ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                            newsFragment,R.id.web_container,BUNDLE_NEW);
                }
                mPresenter=new DetailWebViewPresenter(aNew, NewsRepository.getInstance(),newsFragment);
                break;
            case TYPE_TEACHER:
                actionBar.setTitle(R.string.detail_teacher);
                Teacher teacher=(Teacher)getIntent().getSerializableExtra(BUNDLE_TEACHER);
                checkNotNull(teacher);
                DetailWebViewFragment teacherFragment=(DetailWebViewFragment)getSupportFragmentManager().
                        findFragmentByTag(BUNDLE_TEACHER);
                if (teacherFragment==null){
                    teacherFragment=DetailWebViewFragment.newInstance();
                    ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                            teacherFragment,R.id.web_container,BUNDLE_TEACHER);
                }
                mPresenter=new DetailWebViewPresenter(teacher,TeachersRepository.getInstance(),teacherFragment);
                break;
            case -1:
                Toast.makeText(this,R.string.error_app,Toast.LENGTH_SHORT).show();
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
