package com.nmid.headline.courselist;

import com.nmid.headline.BasePresenter;
import com.nmid.headline.BaseView;
import com.nmid.headline.data.bean.Course;

import java.util.List;

/**
 * 课表界面
 * Created by xwysu on 2016/12/4.
 */

public interface CourseListContract {
    interface View extends BaseView<Presenter> {

        boolean isActive();

        void showCourseDetail(Course course);

        void showError();

        void showCourseList(List<Course> courses ,int week);

        void setWeekInfo(int week);

        void setStuNum(String stuNum);


    }
    interface Presenter extends BasePresenter {

        void backCurrentWeek();

        void saveStuNum(String stuNum);

        void getStuNum();

        void saveDisplayWeek(int week);

        void loadHttpCourseList();

        void openCourseDetail(Course course);

        void loadOldCourseList();


    }
}
