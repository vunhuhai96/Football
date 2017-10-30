package com.two.football.view.activity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.two.football.R;
import com.two.football.adapter.PageAdapter;

import java.util.ArrayList;

public class PlayVideoActivity extends AppCompatActivity{
    private Bundle getBundle = null;
    private String link;
    private ViewPager viewPager;
    private FragmentManager fragmentManager ;
    private PageAdapter pageAdapter;
    private TabLayout tabLayout;
    private VideoView video;
    private RecyclerView rcvBxh;
    private ProgressBar process;
    private MediaController controller;
    private TextView tvDetail;
    private ToolbarActivity toolbarActivity;
    private Toolbar toolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);

        init();
        adControll();
        result();
        playVideo();
        addRe();
    }

    private void result() {
        getBundle = getIntent().getExtras();
        link = getBundle.getString("link");
    }

    private void init() {
        video = (VideoView) findViewById(R.id.videoView);
        process = (ProgressBar) findViewById(R.id.proBar);
        tvDetail = (TextView) findViewById(R.id.tv_detail);

        controller = new MediaController(this);
        /*toolbarActivity = new ToolbarActivity();
        toolbar = (Toolbar) toolbarActivity.findViewById(R.id.tollbar);
        setSupportActionBar(toolbar);*/
    }

    private void playVideo() {
//        mDialog = new ProgressDialog(MainActivity.this);
//        mDialog.setMessage("Please wait.....");
//        mDialog.setCanceledOnTouchOutside(false);
//        mDialog.show();
//
//        String videoUrl = "http://www.html5videoplayer.net/videos/toystory.mp4";
//
//        try {
//            if(!video.isPlaying()){
//                Uri uri = Uri.parse(videoUrl);
//                video.setVideoURI(uri);
////                video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
////                    @Override
////                    public void onCompletion(MediaPlayer mediaPlayer) {
////                        playPause.setImageResource(R.drawable.ic_play_arrow_black_24dp);
////                    }
////                });
////            }else{
////                video.pause();
////                playPause.setImageResource(R.drawable.ic_play_arrow_black_24dp);
//            }
//        }catch (Exception ex){
//        }
//
//        video.requestFocus();
//        mDialog.dismiss();
//
//        video.start();
//        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                mp.setLooping(true);
//            }
//        });
//
//        MediaController vidControl = new MediaController(this);
//        vidControl.setAnchorView(video);
//        video.setMediaController(vidControl);

        String videoUrl = link;

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
        //getSupportActionBar().hide();
        viewPager = (ViewPager) findViewById(R.id.vp_play_video);
        tabLayout = (TabLayout) findViewById(R.id.tab_play_video);
        fragmentManager = getSupportFragmentManager();
        pageAdapter = new PageAdapter(fragmentManager);
        viewPager.setAdapter(pageAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setTabsFromPagerAdapter(pageAdapter);
    }

    private void addRe() {


        //bxhAdapter = new BxhAdapter(this, arrBxh);
        //rcvBxh.setLayoutManager(new LinearLayoutManager(this));
        // rcvBxh.setAdapter(bxhAdapter);
    }


}
