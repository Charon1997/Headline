package com.nmid.headline.courselist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;

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
        fragment= (CourseListFragment) getSupportFragmentManager().findFragmentByTag(CourseListFragment.class.getSimpleName());
        if (fragment==null){
            fragment=CourseListFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    fragment,R.id.courseList_container,CourseListFragment.class.getSimpleName());
        }
        mPresenter=new CourseListPresenter(CourseRepository.getInstance(),fragment);
    }
    public void showDialog(Course course){

    }
}
