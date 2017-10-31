package com.two.football.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.two.football.R;
import com.two.football.adapter.InfoClubAdapter;

/**
 * Created by TWO on 10/25/2017.
 */

public class InfoClubActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager pager;
    private TabLayout tabLayout;
    private InfoClubAdapter adapter;
    private String key;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_info);
        getSupportActionBar().hide();
        Intent intent = getIntent();
        key = intent.getStringExtra("ID_CLUB");
        initView();
        initInfo();
    }

    private void initInfo() {

        FragmentManager manager = getSupportFragmentManager();

        adapter = new InfoClubAdapter(manager);

        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);

        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setTabsFromPagerAdapter(adapter);

    }

    private void initView() {
        pager = (ViewPager) findViewById(R.id.club_info_viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout_club_info);

        findViewById(R.id.btn_club_info_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_club_info_back:
                onBackPressed();
                break;
            default:
                break;
        }
    }

    public String getKey() {
        return key;
    }
}
