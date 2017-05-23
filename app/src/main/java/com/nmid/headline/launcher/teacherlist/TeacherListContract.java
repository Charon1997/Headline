package com.nmid.headline.launcher.teacherlist;

import com.nmid.headline.BasePresenter;
import com.nmid.headline.BaseView;
import com.nmid.headline.data.bean.Teacher;

import java.util.List;

/**
 * Created by xwysu on 2016/12/4.
 * Tab3，教师列表
 */

public interface TeacherListContract {
    interface View extends BaseView<Presenter> {

        boolean isActive();

        void showTeachers(List<Teacher> teachers);

        void showEmptyError();

        void showLoadingIndicator(boolean active);

        void showDetailsUi( Teacher teacher);

        void showMoreTeachers(List<Teacher> addNews);

        void showError();

        void showFilterTeachers(List<Teacher> teachers);

    }
    interface Presenter extends BasePresenter {

        void loadTeachers();

        void loadMoreTeachers();

        void openTeacherDetails(Teacher teacher);

        void loadFilterTeachers(String filterString);

    }
}
