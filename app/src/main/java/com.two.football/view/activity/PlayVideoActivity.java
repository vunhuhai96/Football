package com.two.football.view.activity;


import android.content.Intent;

import android.content.SharedPreferences;

import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.two.football.R;
import com.two.football.adapter.PageAdapter;
import com.two.football.model.User;
import com.two.football.view.fragment.FragmentTT;

import java.io.File;

public class PlayVideoActivity extends AppCompatActivity implements View.OnClickListener {
    private Bundle getBundle = null;
    private String link,link2;
    private ViewPager viewPager;
    private FragmentManager fragmentManager;
    private PageAdapter pageAdapter;
    private TabLayout tabLayout;
    private VideoView video;
    private RecyclerView rcvBxh;
    private ProgressBar process;
    private MediaController controller;
    private TextView tvDetail;
    private LinearLayout toolbar;
    private RelativeLayout rvDetails;
    private ImageView back;

    private Intent intent;

    private String FILE_NAME = "user.txt";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);
        getSupportActionBar().hide();

        restoringPreferences();
        init();
        adControll();
        result();
        playVideo();
    }

    public User restoringPreferences() {
        SharedPreferences preferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        String userName = preferences.getString("name", "");
        String id = preferences.getString("id", "");
        String avatar = preferences.getString("avatar", "");
        User user=null;
        if (userName.equals("")||id.equals("")||avatar.equals("")){
        }
        else{
           user= new User(userName, id, avatar);
            return user;
        }
        return user;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            rvDetails.setVisibility(View.VISIBLE);
            toolbar.setVisibility(View.VISIBLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            rvDetails.setVisibility(View.GONE);
            toolbar.setVisibility(View.GONE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getSupportActionBar().hide();
        }
    }

    private void result() {
        getBundle = getIntent().getExtras();
        link = getBundle.getString("link");

        intent = getIntent();
        link2 = intent.getStringExtra("link");

    }

    private void init() {
        video = (VideoView) findViewById(R.id.videoView);
        process = (ProgressBar) findViewById(R.id.proBar);
        tvDetail = (TextView) findViewById(R.id.tv_detail);
        rvDetails = (RelativeLayout) findViewById(R.id.rv_detail);
        controller = new MediaController(this);

        back = (ImageView) findViewById(R.id.img_back);
        back.setOnClickListener(this);
        toolbar = (LinearLayout) findViewById(R.id.toolbar);
    }

    private void playVideo() {
        String videoUrl = null;
        if (link2 == null){
             videoUrl = link;
        }
        else {
             videoUrl = link2;
        }

        video.setMediaController(controller);
        video.setVideoURI(Uri.parse(videoUrl));
        video.requestFocus();

        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                process.setVisibility(View.GONE);
                video.start();
            }
        });


    }

    private void adControll() {
        viewPager = (ViewPager) findViewById(R.id.vp_play_video);
        tabLayout = (TabLayout) findViewById(R.id.tab_play_video);
        fragmentManager = getSupportFragmentManager();
        pageAdapter = new PageAdapter(fragmentManager);
        viewPager.setAdapter(pageAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setTabsFromPagerAdapter(pageAdapter);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            default:
                break;
        }
    }
}
