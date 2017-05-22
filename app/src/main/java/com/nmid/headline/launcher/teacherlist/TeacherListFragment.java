package com.nmid.headline.launcher.teacherlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.nmid.headline.R;
import com.nmid.headline.data.bean.Teacher;
import com.nmid.headline.detailwebview.DetailWebViewActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by xwysu on 2016/12/4.
 */

public class TeacherListFragment extends Fragment implements TeacherListContract.View {

    TeacherListContract.Presenter mPresenter;
    TeacherListAdapter adapter;
    @BindView(R.id.teacher_list)
    RecyclerView teacherList;
    @BindView(R.id.refresh_teacher_layout)
    SwipeRefreshLayout refreshTeacherLayout;
    Unbinder unbinder;
    private static final int lineItems = 3;
    @BindView(R.id.filterEdit)
    EditText filterEdit;
    @BindView(R.id.filter)
    ConstraintLayout filter;
    private boolean isLoadingMore = false;

    public static TeacherListFragment newInstance() {

        Bundle args = new Bundle();

        TeacherListFragment fragment = new TeacherListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new TeacherListAdapter(getActivity(), new ArrayList<Teacher>());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_teachers, container, false);
        unbinder = ButterKnife.bind(this, root);
        filterEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d("beforeTextChanged",s.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("onTextChanged",s.toString());
                Log.d("onTextChanged",filterEdit.getText().toString().trim()+"");
                mPresenter.loadFilterTeachers(filterEdit.getText().toString().trim());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), lineItems);
        teacherList.setLayoutManager(layoutManager);
        refreshTeacherLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadTeachers();
            }
        });
        teacherList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                int totalItemCount = layoutManager.getItemCount();
                //lastVisibleItem >= totalItemCount 表示剩下2个item自动加载
                // dy>0 表示向下滑动
                if (lastVisibleItem >= totalItemCount - 2 && dy > 0) {
                    Log.d("isloading", ":" + isLoadingMore);
                    if (!isLoadingMore) {
                        isLoadingMore = true;
                        mPresenter.loadMoreTeachers();
                    }
                }
            }
        });
        adapter.setOnItemClickListener(new TeacherListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position, Teacher teacher) {
                mPresenter.openTeacherDetails(teacher);
            }
        });
        teacherList.setAdapter(adapter);
        return root;
    }

    @Override
    public void setPresenter(TeacherListContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showTeachers(List<Teacher> teachers) {
        adapter.notifyAll(teachers);
        if (refreshTeacherLayout.isRefreshing()) {
            refreshTeacherLayout.setRefreshing(false);
        }
        isLoadingMore = false;
    }

    @Override
    public void showEmptyError() {

    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void showLoadingIndicator(boolean active) {
        if (getView() == null) {
            return;
        }
        // Make sure setRefreshing() is called after the layout is done with everything else.
        refreshTeacherLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshTeacherLayout.setRefreshing(active);
            }
        });
    }

    @Override
    public void showDetailsUi(Teacher teacher) {
        Intent intent = new Intent(getContext(), DetailWebViewActivity.class);
        intent.putExtra(DetailWebViewActivity.BUNDLE_TEACHER, teacher);
        intent.putExtra(DetailWebViewActivity.BUNDLE_TYPE, DetailWebViewActivity.TYPE_TEACHER);
        startActivity(intent);
    }

    @Override
    public void showMoreTeachers(List<Teacher> addNews) {
        adapter.notifyAdd(addNews);
        isLoadingMore = false;
    }

    @Override
    public void showError() {
        if (refreshTeacherLayout.isRefreshing()) {
            refreshTeacherLayout.setRefreshing(false);
        }
    }

    @Override
    public void showFilterTeachers(List<Teacher> teachers) {
        adapter.notifyAll(teachers);
        if (refreshTeacherLayout.isRefreshing()) {
            refreshTeacherLayout.setRefreshing(false);
        }
        isLoadingMore = true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
