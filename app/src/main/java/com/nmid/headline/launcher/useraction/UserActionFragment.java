package com.nmid.headline.launcher.useraction;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nmid.headline.R;
import com.nmid.headline.courselist.CourseListActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by xwysu on 2016/12/4.
 */

public class UserActionFragment extends Fragment implements UserActionContract.View {

    UserActionContract.Presenter mPresenter;
    @BindView(R.id.usertitle)
    TextView usertitle;
    @BindView(R.id.userName)
    TextView userName;
    @BindView(R.id.userIcon)
    ImageView userIcon;
    @BindView(R.id.userInfo)
    CardView userInfo;
    @BindView(R.id.classText)
    TextView classText;
    @BindView(R.id.classImage)
    ImageView classImage;
    @BindView(R.id.myclass)
    ConstraintLayout myclass;
    @BindView(R.id.cicleText)
    TextView cicleText;
    @BindView(R.id.circleImage)
    ImageView circleImage;
    @BindView(R.id.collegeCircle)
    ConstraintLayout collegeCircle;
    @BindView(R.id.cardView2)
    CardView cardView2;
    Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_user, container, false);
        unbinder = ButterKnife.bind(this, root);
        myclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.openCourseList();
            }
        });
        collegeCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.openCirclePage();
            }
        });
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    public static UserActionFragment newInstance() {
        Bundle args = new Bundle();
        UserActionFragment fragment = new UserActionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setPresenter(UserActionContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showUserInfo() {

    }

    @Override
    public void showLoginPage() {

    }

    @Override
    public void showCourseList() {
        Intent intent=new Intent(getContext(), CourseListActivity.class);
        startActivity(intent);
    }

    @Override
    public void showCirclePage() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
