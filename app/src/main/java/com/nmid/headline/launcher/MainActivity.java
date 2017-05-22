package com.nmid.headline.launcher;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nmid.headline.R;
import com.nmid.headline.data.CourseRepository;
import com.nmid.headline.data.NewsRepository;
import com.nmid.headline.data.TeachersRepository;
import com.nmid.headline.launcher.newspage.NewsPageFragment;
import com.nmid.headline.launcher.newspage.NewsPagePresenter;
import com.nmid.headline.launcher.teacherlist.TeacherListFragment;
import com.nmid.headline.launcher.teacherlist.TeacherListPresenter;
import com.nmid.headline.launcher.useraction.UserActionFragment;
import com.nmid.headline.launcher.useraction.UserActionPresenter;
import com.nmid.headline.util.ActivityUtils;
import com.nmid.headline.util.BottomNavigationViewHelper;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {
    ActionBar actionBar;
    @BindView(R.id.bottom_nav)
    BottomNavigationView bottomNav;
    @BindView(R.id.contain)
    FrameLayout contain;
    NewsPageFragment collegeNewsFragment;
    NewsPagePresenter collegeNewsPresenter;
    NewsPageFragment newsletterFragment;
    NewsPagePresenter newsletterPresenter;
    TeacherListFragment teacherListFragment;
    TeacherListPresenter teacherListPresenter;
    UserActionFragment userActionFragment;
    UserActionPresenter userActionPresenter;
    EditText filterEdit;
    Fragment currentFragment;
    View view;
    private boolean actionbarVisible=true;
    private final String ACTIONBAR_STATUS="actionbar";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        actionBar = getSupportActionBar();
        view= getLayoutInflater().inflate(R.layout.actionbar_filter,new LinearLayout(this), false);
        filterEdit=(EditText)view.findViewById(R.id.filterEdit);
        actionBar.setCustomView(view);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayShowCustomEnabled(false);
        actionBar.setTitle(R.string.bottom_nav_tab1);
        BottomNavigationViewHelper.disableShiftMode(bottomNav);

        final FragmentManager fragmentManager=getSupportFragmentManager();
        collegeNewsFragment=(NewsPageFragment)fragmentManager.findFragmentByTag(NewsRepository.TYPE_JINGWEI) ;
        if (collegeNewsFragment==null){
            collegeNewsFragment=NewsPageFragment.newInstance(NewsRepository.TYPE_JINGWEI);
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),collegeNewsFragment,R.id.contain,NewsRepository.TYPE_JINGWEI);
            currentFragment=collegeNewsFragment;
        }
        collegeNewsPresenter =new NewsPagePresenter(NewsRepository.getInstance(),collegeNewsFragment,NewsRepository.TYPE_JINGWEI);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bottom_nav_news:
                        actionBar.setDisplayShowTitleEnabled(true);
                        actionBar.setDisplayShowCustomEnabled(false);
                        actionBar.setTitle(R.string.bottom_nav_tab1);
                        collegeNewsFragment=(NewsPageFragment)fragmentManager.findFragmentByTag(NewsRepository.TYPE_JINGWEI) ;
                        getSupportFragmentManager().beginTransaction().hide(currentFragment).show(collegeNewsFragment).commit();
                        currentFragment=collegeNewsFragment;
                        break;
                    case R.id.bottom_nav_newsletter:
                        actionBar.setDisplayShowTitleEnabled(true);
                        actionBar.setDisplayShowCustomEnabled(false);
                        newsletterFragment=(NewsPageFragment)fragmentManager.findFragmentByTag(NewsRepository.TYPE_NEWS) ;
                        if (newsletterFragment==null){
                            newsletterFragment=NewsPageFragment.newInstance(NewsRepository.TYPE_NEWS);
                            getSupportFragmentManager().beginTransaction().hide(currentFragment).commit();
                            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),newsletterFragment,R.id.contain,NewsRepository.TYPE_NEWS);
                        }else {
                            getSupportFragmentManager().beginTransaction().hide(currentFragment).show(newsletterFragment).commit();
                        }
                        currentFragment=newsletterFragment;
                        newsletterPresenter =new NewsPagePresenter(NewsRepository.getInstance(),newsletterFragment,NewsRepository.TYPE_NEWS);
                        actionBar.setTitle(R.string.bottom_nav_tab2);
                        break;
                    case R.id.bottom_nav_teachers:
                        actionBar.setDisplayShowTitleEnabled(false);
                        actionBar.setDisplayShowCustomEnabled(true);
                        teacherListFragment=(TeacherListFragment) fragmentManager.findFragmentByTag(TeacherListFragment.class.getSimpleName()) ;
                        if (teacherListFragment==null){
                            teacherListFragment=TeacherListFragment.newInstance();
                            getSupportFragmentManager().beginTransaction().hide(currentFragment).commit();
                            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),teacherListFragment,R.id.contain,TeacherListFragment.class.getSimpleName());
                        }else {
                            getSupportFragmentManager().beginTransaction().hide(currentFragment).show(teacherListFragment).commit();
                        }
                        currentFragment=teacherListFragment;
                        teacherListPresenter =new TeacherListPresenter(teacherListFragment, TeachersRepository.getInstance());
                        initEdit();
                        break;
                    case R.id.bottom_nav_explore:
                        actionBar.setDisplayShowTitleEnabled(true);
                        actionBar.setDisplayShowCustomEnabled(false);
                        userActionFragment=(UserActionFragment) fragmentManager.findFragmentByTag(UserActionFragment.class.getSimpleName()) ;
                        if (userActionFragment==null){
                            userActionFragment=UserActionFragment.newInstance();
                            getSupportFragmentManager().beginTransaction().hide(currentFragment).commit();
                            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),userActionFragment,R.id.contain,UserActionFragment.class.getSimpleName());
                        }else {
                            getSupportFragmentManager().beginTransaction().hide(currentFragment).show(userActionFragment).commit();
                        }
                        currentFragment=userActionFragment;
                        userActionPresenter =new UserActionPresenter(userActionFragment);
                        actionBar.setTitle(R.string.bottom_nav_tab4);
                        break;
                }
                return true;
            }
        });
    }
    private void initEdit(){
        filterEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d("beforeTextChanged", s.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("onTextChanged", s.toString());
                Log.d("onTextChanged", filterEdit.getText().toString().trim() + "");
                teacherListPresenter.loadFilterTeachers(filterEdit.getText().toString().trim());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        filterEdit.setText("");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(MainActivity.class.getSimpleName(),"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(MainActivity.class.getSimpleName(),"onDestroy");
    }

}
