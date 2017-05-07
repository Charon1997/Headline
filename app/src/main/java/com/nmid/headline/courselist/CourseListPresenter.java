package com.nmid.headline.courselist;

import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.nmid.headline.data.CourseDataSource;
import com.nmid.headline.data.CourseRepository;
import com.nmid.headline.data.bean.Course;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by xwysu on 2016/12/4.
 */

public class CourseListPresenter implements CourseListContract.Presenter{

    private final CourseRepository mCourseRepository;
    private final CourseListContract.View mCourseView;
    private String savedStuNum;
    private int savedWeek;
    public CourseListPresenter(@NonNull CourseRepository repository, @NonNull CourseListContract.View view){
        mCourseRepository=checkNotNull(repository);
        mCourseView=checkNotNull(view);
        mCourseView.setPresenter(this);
    }
    @Override
    public void start() {
        getSavedStuNum();
        getSavedWeek();
        loadCourseList();
    }
    private void loadCourse(String stuNum,String stuId){
        mCourseRepository.getCourseList(new CourseDataSource.LoadCourseCallback() {
            @Override
            public void onCourseLoaded(List<Course> courses) {
                if (mCourseView.isActive()){
                    mCourseView.showCourseList(courses,savedWeek);
                }
            }

            @Override
            public void onDataNotAvailable() {

            }
        },stuNum,stuId);
    }

    private void getSavedStuNum(){
        savedStuNum= "2014210534";
    }
    private void getSavedWeek(){
        savedWeek= 2;
    }

    @Override
    public void saveCurrentWeek(int week) {

    }

    @Override
    public void saveStuNum(String stuNum) {
        savedStuNum=stuNum;
        loadCourseList();
    }

    @Override
    public void saveDisplayWeek(int week) {
        savedWeek=week;
        loadCourseList();
    }

    @Override
    public void loadCourseList() {
        checkNotNull(savedStuNum);
        if (!savedStuNum.equals("")){
           loadCourse(savedStuNum,"");
        }else {
            Log.d(getClass().getSimpleName(),"stuNum is empty");
        }
    }

    @Override
    public void openCourseDetail() {

    }
}
