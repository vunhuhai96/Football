package com.two.football.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.two.football.R;
import com.two.football.adapter.HighlightAdapter;
import com.two.football.adapter.VideoFavoriteAdapter;
import com.two.football.model.Highlight;
import com.two.football.model.VideoFavorite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TWO on 10/27/2017.
 */

public class VideoFavoriteActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ListView listView;
    private List<Highlight> list;
    private VideoFavoriteAdapter adapter;
    private TextView tvNameToolbar;
    private ImageView imgBack;
    private DatabaseReference reference;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_favorite);
        reference = FirebaseDatabase.getInstance().getReference();
        initView();
        initVideoFavorite();
    }

    private void initVideoFavorite() {
        list = new ArrayList<>();
        String idUser = MainActivity.ID;
        reference.child("User").child(idUser).child("Video").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Highlight highlight = dataSnapshot.getValue(Highlight.class);
                highlight.setKey(dataSnapshot.getKey());
                list.add(highlight);
                adapter = new VideoFavoriteAdapter(VideoFavoriteActivity.this, list);
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
        listView = (ListView) findViewById(R.id.lv_video_favorite);
        tvNameToolbar = (TextView) findViewById(R.id.tv_name_toolbar);
        tvNameToolbar.setText("Video yêu thích");
        imgBack = (ImageView) findViewById( R.id.img_back);
        imgBack.setOnClickListener(this);

        listView.setOnItemClickListener(this);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        intent = new Intent(VideoFavoriteActivity.this, PlayVideoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("title", list.get(position).getTitle());
        bundle.putString("link", list.get(position).getUrlVideo());
        bundle.putString("tournaments",list.get(position).getTournaments());
        bundle.putString("id", list.get(position).getId());

        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(VideoFavoriteActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
