package com.nmid.headline.courselist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;

import com.nmid.headline.data.bean.Course;

/**
 * Created by xwysu on 2017/5/15.
 */

public class CourseDetailFragment extends DialogFragment{

    FragmentActivity mActivity;
    Course mCourse;

    public static CourseDetailFragment newInstance(Course c,FragmentActivity activity) {
        Bundle args = new Bundle();
        CourseDetailFragment fragment = new CourseDetailFragment();
        fragment.setArguments(args);
        fragment.mActivity=activity;
        fragment.mCourse=c;
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }
}
