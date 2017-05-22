package com.nmid.headline.launcher.teacherlist;

import android.support.annotation.NonNull;

import com.nmid.headline.data.TeachersDataSource;
import com.nmid.headline.data.TeachersRepository;
import com.nmid.headline.data.bean.Teacher;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by xwysu on 2016/12/4.
 */

public class TeacherListPresenter implements TeacherListContract.Presenter{

    TeacherListContract.View mView;
    TeachersRepository mRepository;
    List<Teacher> allTeachers;
    int recordId=0;

    public TeacherListPresenter(@NonNull TeacherListContract.View view,@NonNull TeachersRepository repository){
        mView=view;
        mRepository=repository;
        mView.setPresenter(this);
    }
    @Override
    public void start() {
        loadTeachers();
    }
    private void loadTeachers(int lastId ,int limit){
        mRepository.getTeachers(new TeachersDataSource.LoadTeachersCallback() {
            @Override
            public void onTeachersLoad(List<Teacher> teachers) {
                checkNotNull(teachers);
                if (!teachers.isEmpty()){
                    recordId=teachers.get(teachers.size()-1).getTeacherInfoPid();
                }
                if (mView.isActive()){
                    if (lastId>0){
                        mView.showMoreTeachers(teachers);
                    }else {
                        mView.showTeachers(teachers);
                    }
                }
            }

            @Override
            public void onDataNotAvailable() {
                if (mView.isActive()){
                    mView.showError();
                }
            }
        },lastId,limit);
    }
    private void filterTeachers(String string){
        List<Teacher> filter=new ArrayList<>();
        if (string!=null){
            if (string.equals("")){
                mView.showFilterTeachers(allTeachers);
            }else {
                for (Teacher t:allTeachers
                     ) {
                    if (t.getName().contains(string)){
                        filter.add(t);
                    }
                }
                mView.showFilterTeachers(filter);
            }
        }else {
            mView.showFilterTeachers(allTeachers);
        }
    }

    @Override
    public void loadTeachers() {
        recordId=0;
        loadTeachers(TeachersDataSource.FIRST_REQUEST,TeachersDataSource.DEFAULT_LIMIT);
    }

    @Override
    public void loadMoreTeachers() {
        loadTeachers(recordId,TeachersDataSource.DEFAULT_LIMIT);
    }

    @Override
    public void openTeacherDetails(Teacher teacher) {
        if (mView.isActive()){
            mView.showDetailsUi(teacher);
        }
    }

    @Override
    public void loadFilterTeachers(String filterString) {
        if (allTeachers==null){
            mRepository.getTeachers(new TeachersDataSource.LoadTeachersCallback() {
                @Override
                public void onTeachersLoad(List<Teacher> teachers) {
                    checkNotNull(teachers);
                    allTeachers=teachers;
                    filterTeachers(filterString);
                }

                @Override
                public void onDataNotAvailable() {
                    if (mView.isActive()){
                        mView.showError();
                    }
                }
            },TeachersDataSource.FIRST_REQUEST,TeachersDataSource.ALL_LIMIT);
        }else {
            filterTeachers(filterString);
        }
    }

}
