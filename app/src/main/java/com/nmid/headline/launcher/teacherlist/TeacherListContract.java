package com.nmid.headline.launcher.teacherlist;

import com.nmid.headline.BasePresenter;
import com.nmid.headline.BaseView;
import com.nmid.headline.data.bean.Teacher;

import java.util.List;

/**
 * Created by xwysu on 2016/12/4.
 * 第二部分，教师列表
 */

public interface TeacherListContract {
    interface View extends BaseView<Presenter> {
        boolean isActive();
        void showTeachers(List<Teacher> teachers);
        void showEmptyError();
        void showFilterResult();
        void showFilterAll();
        void showFilterError();
        void showLoadingIndicator(boolean active);
        void showDetailsUi(int teacherId);
    }
    interface Presenter extends BasePresenter {
        void loadTasks(boolean forceUpdate);
        void openTeacherDetails(Teacher teacher);
        void filterName(String input);
    }
}
