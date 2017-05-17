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
import com.nmid.headline.about.AboutActivity;
import com.nmid.headline.courselist.CourseListActivity;
import com.nmid.headline.favoritelist.FavoriteListActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by xwysu on 2016/12/4.
 */

public class UserActionFragment extends Fragment implements UserActionContract.View {

    UserActionContract.Presenter mPresenter;
    @BindView(R.id.userInfo)
    CardView userInfo;
    @BindView(R.id.classText)
    TextView classText;
    @BindView(R.id.classImage)
    ImageView classImage;
    @BindView(R.id.myclass)
    ConstraintLayout myclass;
    @BindView(R.id.favoriteText)
    TextView favoriteText;
    @BindView(R.id.favoriteImage)
    ImageView favoriteImage;
    @BindView(R.id.favorite)
    ConstraintLayout favorite;
    @BindView(R.id.aboutText)
    TextView aboutText;
    @BindView(R.id.aboutImage)
    ImageView aboutImage;
    @BindView(R.id.about)
    ConstraintLayout about;
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
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.openFavoritePage();
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.openAboutPage();
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
    public void showAboutPage() {
        Intent intent = new Intent(getContext(), AboutActivity.class);
        startActivity(intent);
    }

    @Override
    public void showFavoritePage() {
        Intent intent = new Intent(getContext(), FavoriteListActivity.class);
        startActivity(intent);
    }


    @Override
    public void showCourseList() {
        Intent intent = new Intent(getContext(), CourseListActivity.class);
        startActivity(intent);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
