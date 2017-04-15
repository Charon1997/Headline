package com.nmid.headline.courselist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nmid.headline.R;
import com.nmid.headline.data.bean.Course;

import java.util.List;

/**
 * Created by xwysu on 2016/12/4.
 */

public class CourseListFragment extends Fragment implements CourseListContract.View{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_courselist,container,false);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public static CourseListFragment newInstance() {
        Bundle args = new Bundle();
        CourseListFragment fragment = new CourseListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    CourseListContract.Presenter mPresenter;

    @Override
    public void setPresenter(CourseListContract.Presenter presenter) {
        mPresenter=presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showCourseDetail(Course course) {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showCourseList(List<Course> courses, int week) {

    }

    @Override
    public void setWeekInfo(int week) {

    }

    @Override
    public void setStuNum(int stuNum) {

    }
}
