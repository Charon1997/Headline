package com.nmid.headline.courselist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.nmid.headline.R;
import com.nmid.headline.data.bean.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by xwysu on 2016/12/4.
 */

public class CourseListFragment extends Fragment implements CourseListContract.View {
    @BindView(R.id.schedule_all_week)
    TextView scheduleAllWeek;
    @BindView(R.id.gridLayout)
    GridLayout gridLayout;
    @BindView(R.id.imageButton)
    ImageButton imageButton;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.button)
    Button button;
    Unbinder unbinder;
    //算上标题栏
    private final int COL_MAX=8;
    private final int ROW_MAX=7;

    private List<Course> weekCourses;
    int courseId=0;
    int currentWeek=0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_courselist, container, false);
        unbinder = ButterKnife.bind(this, root);
        String[] mItems=getResources().getStringArray(R.array.weeks);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, mItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner .setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mPresenter.saveDisplayWeek(position+1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.saveCurrentWeek(currentWeek);
            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.saveStuNum(checkNotNull(editText.getText().toString().trim()));
            }
        });
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    private void initCourseList(){
//        checkNotNull(courses);
        checkNotNull(gridLayout);
        gridLayout.removeViews(20, gridLayout.getChildCount() - 20);
        courseId=0;
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        //确定每一项子view的宽度和高度，如果不进行这一步，内容将显示不正确
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        int item_width = width / 8;
        int item_height = height / 7;
        Course c;
        boolean isLoaded=false;
        for (int columnSpec=1;columnSpec<COL_MAX;columnSpec++){
            for (int rowSpec=1;rowSpec<ROW_MAX;rowSpec++){
                LinearLayout view= (LinearLayout) layoutInflater.inflate(R.layout.item_course_grid,null);
                TextView item=(TextView)view.findViewById(R.id.courseItem);
                if (!isLoaded&&weekCourses.get(courseId).getHashDay()==(columnSpec-1)&&
                        weekCourses.get(courseId).getBeginLesson()==(rowSpec*2-1)){
                    c=weekCourses.get(courseId);
                    item.setText(c.getCourse()+"\n"+c.getClassroom());
                    courseId++;
                    if (courseId<weekCourses.size()){
                        isLoaded=false;
                    }else {
                        isLoaded=true;
                    }
                }
                GridLayout.LayoutParams param = new GridLayout.LayoutParams();
                param.columnSpec = GridLayout.spec(columnSpec, 1);
                param.rowSpec = GridLayout.spec(rowSpec * 2 - 1, 2);
                param.setGravity(Gravity.FILL);
                param.setMargins(1, 1, 1, 1);
                param.width = item_width - 5;
                param.height = item_height;
                gridLayout.addView(view, param);
            }
        }
    }
    public static CourseListFragment newInstance() {
        Bundle args = new Bundle();
        CourseListFragment fragment = new CourseListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    CourseListContract.Presenter mPresenter;

    @Override
    public void setPresenter(CourseListContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showCourseDetail(Course course) {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showCourseList(List<Course> courses, int week) {
        currentWeek=week;
        weekCourses=new ArrayList<>();
        checkNotNull(courses);
        for (Course c:courses
             ) {
            if (c.getWeek().contains(week)){
                weekCourses.add(c);
            }
        }
        initCourseList();
    }

    @Override
    public void setWeekInfo(int week) {

    }

    @Override
    public void setStuNum(int stuNum) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
