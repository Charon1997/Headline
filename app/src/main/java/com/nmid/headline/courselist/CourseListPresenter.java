package com.nmid.headline.courselist;

import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.nmid.headline.data.CourseDataSource;
import com.nmid.headline.data.CourseRepository;
import com.nmid.headline.data.bean.Course;
import com.nmid.headline.util.AppContext;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by xwysu on 2016/12/4.
 */

public class CourseListPresenter implements CourseListContract.Presenter{

    private final CourseRepository mCourseRepository;
    private final CourseListContract.View mCourseView;
    private String savedStuNum;
    private int displayWeek=-1;
    public CourseListPresenter(@NonNull CourseRepository repository, @NonNull CourseListContract.View view){
        mCourseRepository=checkNotNull(repository);
        mCourseView=checkNotNull(view);
        mCourseView.setPresenter(this);
    }
    @Override
    public void start() {
        getStuNum();
    }
    private void loadHttpCourse(String stuNum,String stuId){
        mCourseRepository.getCourseList(new CourseDataSource.LoadCourseCallback() {
            @Override
            public void onCourseLoaded(List<Course> courses,int nowWeek) {
                if (courses!=null&&!courses.isEmpty()){
                    mCourseRepository.saveCourseList((ArrayList<Course>) courses);
                    mCourseRepository.saveStuNum(savedStuNum);
                    mCourseRepository.saveWeek(nowWeek);
                    if (mCourseView.isActive()){
                        mCourseView.showCourseList(courses,nowWeek);
                        mCourseView.setWeekInfo(nowWeek);
                    }
                }else {
                    Toast.makeText(AppContext.getContext(),"查询失败，请检查输入数据是否正确",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onDataNotAvailable() {

            }
        },stuNum,stuId);
    }
    private void loadOldCourse(){
        mCourseRepository.getCourseOldList(new CourseDataSource.LoadCourseCallback() {
            @Override
            public void onCourseLoaded(List<Course> courses,int nowWeek) {
                if (mCourseView.isActive()){
                    if (displayWeek!=-1){
                        //被修改过值，按照修改的显示
                        mCourseView.showCourseList(courses,displayWeek);
                        mCourseView.setWeekInfo(displayWeek);
                    }else {
                        //未被修改过值，按照返回的显示
                        if (nowWeek!=-1){
                            mCourseView.showCourseList(courses,nowWeek);
                            mCourseView.setWeekInfo(nowWeek);
                        }else {
                            loadHttpCourseList();
                        }

                    }

                }
            }

            @Override
            public void onDataNotAvailable() {
                loadHttpCourseList();
            }
        });
    }

    @Override
    public void backCurrentWeek() {
        displayWeek=-1;
        loadOldCourseList();
    }

    @Override
    public void saveStuNum(String stuNum) {
        if (stuNum!=null){
            savedStuNum=stuNum;
            loadHttpCourseList();
        }
    }

    @Override
    public void getStuNum() {
        savedStuNum=mCourseRepository.getStuNum();
        if (savedStuNum==null){
            mCourseView.setStuNum("");
        }else{
            mCourseView.setStuNum(savedStuNum);
            loadOldCourseList();
        }
    }

    @Override
    public void saveDisplayWeek(int week) {
        displayWeek=week;
        loadOldCourseList();
    }

    @Override
    public void loadHttpCourseList() {
        checkNotNull(savedStuNum);
        if (!savedStuNum.equals("")){
            loadHttpCourse(savedStuNum,"");
        }else {
            Log.d(getClass().getSimpleName(),"stuNum is empty");
        }
    }

    @Override
    public void openCourseDetail() {

    }

    @Override
    public void loadOldCourseList() {
        if (savedStuNum!=null&&!savedStuNum.equals("")){
            loadOldCourse();
        }else {
            Log.d(getClass().getSimpleName(),"stuNum is empty");
        }
    }
}
