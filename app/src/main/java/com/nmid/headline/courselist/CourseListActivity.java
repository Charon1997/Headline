package com.nmid.headline.courselist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.nmid.headline.R;
import com.nmid.headline.data.CourseRepository;
import com.nmid.headline.data.bean.Course;
import com.nmid.headline.util.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xwysu on 2016/12/4.
 * 课表相关
 */

public class CourseListActivity extends AppCompatActivity {
    @BindView(R.id.courseList_container)
    ConstraintLayout courseListContainer;
    CourseListFragment fragment;
    CourseListPresenter mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courselist);
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setTitle(R.string.course_title);
        fragment= (CourseListFragment) getSupportFragmentManager().findFragmentByTag(CourseListFragment.class.getSimpleName());
        if (fragment==null){
            fragment=CourseListFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    fragment,R.id.courseList_container,CourseListFragment.class.getSimpleName());
        }
        mPresenter=new CourseListPresenter(CourseRepository.getInstance(),fragment);
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
