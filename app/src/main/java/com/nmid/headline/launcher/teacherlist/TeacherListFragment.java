package com.nmid.headline.launcher.teacherlist;

import com.nmid.headline.data.bean.Teacher;

import java.util.List;

/**
 * Created by xwysu on 2016/12/4.
 */

public class TeacherListFragment implements TeacherListContract.View {
    @Override
    public void setPresenter(TeacherListContract.Presenter presenter) {

    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void showTeachers(List<Teacher> teachers) {

    }

    @Override
    public void showEmptyError() {

    }

    @Override
    public void showFilterResult() {

    }

    @Override
    public void showFilterAll() {

    }

    @Override
    public void showFilterError() {

    }

    @Override
    public void showLoadingIndicator(boolean active) {

    }

    @Override
    public void showDetailsUi(int teacherId) {

    }
}
