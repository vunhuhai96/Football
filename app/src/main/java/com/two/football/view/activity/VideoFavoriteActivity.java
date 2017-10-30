package com.two.football.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;

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

        findViewById(R.id.btn_video_favorite_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_video_favorite_back:
                onBackPressed();
                break;
            default:
                break;
        }
    }
}
