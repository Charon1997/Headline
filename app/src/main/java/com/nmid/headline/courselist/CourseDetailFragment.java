package com.nmid.headline.courselist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nmid.headline.R;
import com.nmid.headline.data.bean.Course;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 课表详情
 * Created by xwysu on 2017/5/15.
 */

public class CourseDetailFragment extends DialogFragment {

    Course mCourse;
    @BindView(R.id.timeText)
    TextView timeText;
    @BindView(R.id.courseName)
    ImageView courseName;
    @BindView(R.id.nameText)
    TextView nameText;
    @BindView(R.id.teacherIcon)
    ImageView teacherIcon;
    @BindView(R.id.teacherName)
    TextView teacherName;
    @BindView(R.id.locationText)
    TextView locationText;
    @BindView(R.id.locationIcon)
    ImageView locationIcon;
    @BindView(R.id.timeIcon)
    ImageView timeIcon;
    @BindView(R.id.dateIcon)
    ImageView dateIcon;
    @BindView(R.id.dateText)
    TextView dateText;
    Unbinder unbinder;

    public static CourseDetailFragment newInstance(Course c) {
        Bundle args = new Bundle();
        CourseDetailFragment fragment = new CourseDetailFragment();
        fragment.setArguments(args);
        fragment.mCourse = c;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dialog, container, false);
        unbinder = ButterKnife.bind(this, root);
        updateText(mCourse);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void updateText(Course course){
        mCourse=course;
        nameText.setText(mCourse.getCourse());
        timeText.setText(mCourse.getLesson());
        dateText.setText(mCourse.getRawWeek());
        locationText.setText(mCourse.getClassroom());
        teacherName.setText(mCourse.getTeacher());
    }
}
