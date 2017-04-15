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
import com.nmid.headline.data.CourseRepository;
import com.nmid.headline.data.NewsRepository;
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
    Fragment currentFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        actionBar = getSupportActionBar();
        BottomNavigationViewHelper.disableShiftMode(bottomNav);
        final FragmentManager fragmentManager=getSupportFragmentManager();
        actionBar.setTitle(R.string.bottom_nav_tab1);
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
                        actionBar.show();
                        actionBar.setTitle(R.string.bottom_nav_tab1);
                        collegeNewsFragment=(NewsPageFragment)fragmentManager.findFragmentByTag(NewsRepository.TYPE_JINGWEI) ;
                        getSupportFragmentManager().beginTransaction().hide(currentFragment).show(collegeNewsFragment).commit();
                        currentFragment=collegeNewsFragment;
                        break;
                    case R.id.bottom_nav_newsletter:
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
                        actionBar.show();
                        actionBar.setTitle(R.string.bottom_nav_tab2);
                        break;
                    case R.id.bottom_nav_teachers:
                        Toast.makeText(getApplicationContext(),"teacher",Toast.LENGTH_SHORT).show();
                        actionBar.hide();
                        actionBar.setShowHideAnimationEnabled(false);
                        break;
                    case R.id.bottom_nav_explore:
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
                        actionBar.show();
                        actionBar.setTitle(R.string.bottom_nav_tab4);
                        break;
                }
                return true;
            }
        });
    }

}
