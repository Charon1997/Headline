package com.nmid.headline.launcher;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.nmid.headline.R;
import com.nmid.headline.data.NewsRepository;
import com.nmid.headline.launcher.newspage.collegenews.CollegeNewsFragment;
import com.nmid.headline.launcher.newspage.collegenews.CollegeNewsPresenter;
import com.nmid.headline.launcher.newspage.newsletter.NewsletterFragment;
import com.nmid.headline.launcher.teacherlist.TeacherListFragment;
import com.nmid.headline.launcher.useraction.UserActionFragment;
import com.nmid.headline.util.ActivityUtils;
import com.nmid.headline.util.BottomNavigationViewHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {
    ActionBar actionBar;
    @BindView(R.id.bottom_nav)
    BottomNavigationView bottomNav;
    @BindView(R.id.contain)
    FrameLayout contain;
    CollegeNewsFragment collegeNewsFragment;
    CollegeNewsPresenter collegeNewsPresenter;
    NewsletterFragment newsletterFragment;
    TeacherListFragment teacherListFragment;
    UserActionFragment userActionFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        actionBar = getSupportActionBar();
        BottomNavigationViewHelper.disableShiftMode(bottomNav);
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        collegeNewsFragment=(CollegeNewsFragment)fragmentManager.findFragmentByTag(CollegeNewsFragment.class.getSimpleName()) ;
        if (collegeNewsFragment==null){
            collegeNewsFragment=CollegeNewsFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),collegeNewsFragment,R.id.contain,CollegeNewsFragment.class.getSimpleName());
        }
        collegeNewsPresenter=new CollegeNewsPresenter(NewsRepository.getInstance(),collegeNewsFragment);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bottom_nav_news:
                        Toast.makeText(getApplicationContext(),"news",Toast.LENGTH_SHORT).show();
                        actionBar.show();
                        actionBar.setTitle(R.string.bottom_nav_tab1);
                        collegeNewsFragment=(CollegeNewsFragment)fragmentManager.findFragmentByTag(CollegeNewsFragment.class.getSimpleName()) ;
                        transaction.show(collegeNewsFragment);
                        break;
                    case R.id.bottom_nav_newsletter:
                        Toast.makeText(getApplicationContext(),"newsletter",Toast.LENGTH_SHORT).show();
                        actionBar.show();
                        actionBar.setTitle(R.string.bottom_nav_tab2);
                        break;
                    case R.id.bottom_nav_teachers:
                        Toast.makeText(getApplicationContext(),"teacher",Toast.LENGTH_SHORT).show();
                        actionBar.hide();
                        actionBar.setShowHideAnimationEnabled(false);
                        break;
                    case R.id.bottom_nav_explore:
                        Toast.makeText(getApplicationContext(),"explore",Toast.LENGTH_SHORT).show();
                        actionBar.show();
                        actionBar.setTitle(R.string.bottom_nav_tab4);
                        break;
                }
                transaction.commit();
                return true;
            }
        });
    }

}
