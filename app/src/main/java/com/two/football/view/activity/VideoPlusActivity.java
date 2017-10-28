package com.two.football.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.two.football.R;
import com.two.football.adapter.HomeVideoAdapter;
import com.two.football.adapter.VideoPlusAdapter;
import com.two.football.model.Video;
import com.two.football.model.VideoFavorite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TWO on 10/27/2017.
 */

public class VideoPlusActivity extends Activity implements View.OnClickListener {
    private ListView listView;
    private List<Video> list;
    private VideoPlusAdapter adapter;
    private DatabaseReference reference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reference = FirebaseDatabase.getInstance().getReference();
        setContentView(R.layout.activity_video_favorite);
        initView();
        initVideo();
    }

    private void initVideo() {
        list = new ArrayList<>();
        reference.child("Video").child("High Light").child("Ngoại Hạng Anh").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Video video = dataSnapshot.getValue(Video.class);
                list.add(video);
                adapter = new VideoPlusAdapter(VideoPlusActivity.this, list);

                listView.setAdapter(adapter);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void initView() {
        findViewById(R.id.btn_video_favorite_back).setOnClickListener(this);
        listView = (ListView) findViewById(R.id.lv_video_favorite);
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
