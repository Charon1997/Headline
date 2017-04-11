package com.nmid.headline.detailwebview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.nmid.headline.R;
import com.nmid.headline.data.bean.New;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by xwysu on 2016/12/4.
 */

public class DetailWebViewFragment extends Fragment implements DetailWebViewContract.View {


    @BindView(R.id.webview)
    WebView webview;
    Unbinder unbinder;
    //html
    static final String MIME_TYPE = "text/html";
    static final String ENCODING = "utf-8";

    DetailWebViewContract.Presenter mPresenter;

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
        WebSettings webSettings=webview.getSettings();
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
        return false;
    }

    @Override
    public void showBaseUrl(String url) {

    }

    @Override
    public void showHtml(String html) {
        String htmlData= "<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" /> <body class= \"gloable\"> "
                        + html
                        + "</body>";
        webview.loadDataWithBaseURL("file:///android_asset/", htmlData, MIME_TYPE, ENCODING, null);
    }

    @Override
    public void showError() {

    }

    @Override
    public void showLoadingIndicator(boolean active) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
