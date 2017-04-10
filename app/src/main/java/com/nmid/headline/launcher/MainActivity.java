package com.nmid.headline.launcher;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.nmid.headline.R;
import com.nmid.headline.util.BottomNavigationViewHelper;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {
    ActionBar actionBar;
    @BindView(R.id.bottom_nav)
    BottomNavigationView bottomNav;
    @BindView(R.id.contain)
    FrameLayout contain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        actionBar = getSupportActionBar();
        BottomNavigationViewHelper.disableShiftMode(bottomNav);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bottom_nav_news:
                        Toast.makeText(getApplicationContext(),"news",Toast.LENGTH_SHORT).show();
                        actionBar.show();
                        actionBar.setTitle(R.string.bottom_nav_tab1);
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
                return true;
            }
        });
    }
}
