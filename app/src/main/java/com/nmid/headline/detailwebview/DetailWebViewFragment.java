package com.nmid.headline.detailwebview;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.nmid.headline.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by xwysu on 2016/12/4.
 */

public class DetailWebViewFragment extends Fragment implements DetailWebViewContract.View {


    //html
    static final String MIME_TYPE = "text/html";
    static final String ENCODING = "utf-8";
    DetailWebViewContract.Presenter mPresenter;
    @BindView(R.id.floatingActionButton)
    FloatingActionButton floatingActionButton;
    @BindView(R.id.webview)
    WebView webview;
    Unbinder unbinder;

    public static DetailWebViewFragment newInstance() {

        Bundle args = new Bundle();

        DetailWebViewFragment fragment = new DetailWebViewFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_web, container, false);
        unbinder = ButterKnife.bind(this, root);
        WebSettings webSettings = webview.getSettings();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(DetailWebViewContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showBaseUrl(String url) {

    }

    @Override
    public void showHtml(String html) {
        String htmlData = "<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" /> <body class= \"gloable\"> "
                + html
                + "</body>";
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                webview.loadDataWithBaseURL("file:///android_asset/", htmlData, MIME_TYPE, ENCODING, null);
            }
        });
    }

    @Override
    public void showError() {

    }

    @Override
    public void showLoadingIndicator(boolean active) {

    }

    @Override
    public void setFloatBarVisible(int visible) {
        floatingActionButton.setVisibility(visible);
        if (visible==View.VISIBLE){
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPresenter.clickEvent();
                }
            });
        }
    }

    @Override
    public void showFloatBarStatus(boolean saved) {
        if (saved){
            floatingActionButton.setImageResource(R.drawable.ic_favorite_black_24dp);
        }else {
            floatingActionButton.setImageResource(R.drawable.ic_favorite_border_black_24dp);
        }
    }

    @Override
    public void showFloatBar() {
        floatingActionButton.setImageResource(R.drawable.ic_launch_black_24dp);
    }

    @Override
    public void openUri(Uri uri) {
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        getActivity().startActivity(intent);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
