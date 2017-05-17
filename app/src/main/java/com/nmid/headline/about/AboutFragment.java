package com.nmid.headline.about;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nmid.headline.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by xwysu on 2016/12/4.
 */

public class AboutFragment extends Fragment implements AboutContract.View {
    @BindView(R.id.appIcon)
    ImageView appIcon;
    @BindView(R.id.appName)
    TextView appName;
    @BindView(R.id.version)
    TextView version;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.developerLogo)
    ImageView developerLogo;
    @BindView(R.id.developerName)
    TextView developerName;
    Unbinder unbinder;

    AboutContract.Presenter mPresenter;

    public static AboutFragment newInstance() {

        Bundle args = new Bundle();
        AboutFragment fragment = new AboutFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setPresenter(AboutContract.Presenter presenter) {
        mPresenter=presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void openWeb(Uri uri) {
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        getActivity().startActivity(intent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_about, container, false);
        unbinder = ButterKnife.bind(this, v);
        developerLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.joinUs();
            }
        });
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
