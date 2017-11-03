package com.two.football.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.two.football.R;
import com.two.football.adapter.VideoFavoriteAdapter;
import com.two.football.model.VideoFavorite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TWO on 10/27/2017.
 */

public class VideoFavoriteActivity extends Activity implements View.OnClickListener {
    private ListView listView;
    private List<VideoFavorite> list;
    private VideoFavoriteAdapter adapter;
    private TextView tvNameToolbar;
    private ImageView imgBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_favorite);
        //getSupportActionBar().hide();

        initView();
        initVideoFavorite();
    }

    private void initVideoFavorite() {
        list = new ArrayList<>();

        for (int i=0;i<10;i++){
            list.add(new VideoFavorite());
        }

        adapter = new VideoFavoriteAdapter(VideoFavoriteActivity.this, list);
        listView.setAdapter(adapter);
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.lv_video_favorite);
        tvNameToolbar = (TextView) findViewById(R.id.tv_name_toolbar);
        tvNameToolbar.setText("Video yêu thích");
        imgBack = (ImageView) findViewById( R.id.img_back);
        imgBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                onBackPressed();
                break;
            default:
                break;
        }
    }
}
