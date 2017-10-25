package com.two.football.view.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.two.football.model.Match;
import com.two.football.R;
import com.two.football.adapter.MainAdapter;
import com.two.football.model.Video;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private DrawerLayout drawerLayout;
    private ImageView btnNavigation;
    private ArrayList<Match> matches;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Button btnMenuHome, btnMenuLive, btnMenuHighlight;
    private MainAdapter mainAdapter;
    DatabaseReference mReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initPager();
    }

    private void initPager() {
        viewPager = (ViewPager) findViewById(R.id.main_viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        FragmentManager manager = getSupportFragmentManager();

        mainAdapter = new MainAdapter(manager);

        viewPager.setAdapter(mainAdapter);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setTabsFromPagerAdapter(mainAdapter);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_live);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_highlight);
    }

    private void initView() {
        mReference = FirebaseDatabase.getInstance().getReference();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        btnNavigation = (ImageView) findViewById(R.id.btn_navigation);
        btnNavigation.setOnClickListener(this);

        btnMenuHome = (Button) findViewById(R.id.btn_menu_home);
        btnMenuLive = (Button) findViewById(R.id.btn_menu_live);
        btnMenuHighlight = (Button) findViewById(R.id.btn_menu_higlight);

        btnMenuHome.setOnClickListener(this);
        btnMenuLive.setOnClickListener(this);
        btnMenuHighlight.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_navigation:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
            case R.id.btn_menu_home:
                viewPager.setCurrentItem(0);
                drawerLayout.closeDrawers();
                break;

            case R.id.btn_menu_live:
                viewPager.setCurrentItem(1);
                drawerLayout.closeDrawers();
                break;

            case R.id.btn_menu_higlight:
                viewPager.setCurrentItem(2);
                drawerLayout.closeDrawers();
                break;
            default:
                break;
        }
    }
}
