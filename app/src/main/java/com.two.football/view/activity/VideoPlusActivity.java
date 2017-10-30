package com.two.football.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.two.football.R;
import com.two.football.adapter.VideoPlusAdapter;
import com.two.football.model.Video;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TWO on 10/27/2017.
 */

public class VideoPlusActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ListView listView;
    private List<Video> list;
    private VideoPlusAdapter adapter;
    private DatabaseReference reference;
    private Intent intent;

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
        reference.child("Tournament").child("Premier League").child("videos").addChildEventListener(new ChildEventListener() {
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
        listView.setOnItemClickListener(this);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        intent = new Intent(VideoPlusActivity.this, PlayVideoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("title",list.get(position).getTitle());
        bundle.putString("link",list.get(position).getUrlVideo());
        intent.putExtras(bundle);

        startActivity(intent);
    }
}
