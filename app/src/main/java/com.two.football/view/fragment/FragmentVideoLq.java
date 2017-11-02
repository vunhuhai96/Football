package com.two.football.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.two.football.R;
import com.two.football.adapter.VideoLqAdapter;
import com.two.football.model.Video;
import com.two.football.model.VideoLq;

import java.util.ArrayList;

public class FragmentVideoLq extends Fragment {
    private DatabaseReference mReference;
    private RecyclerView rcvVideoLq;
    private View view;
    private ArrayList<VideoLq> arrVideo;

    private VideoLqAdapter videoLqAdapter;


    public FragmentVideoLq() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.video_lq, container, false);
        mReference = FirebaseDatabase.getInstance().getReference();
        init();
        database();
        return view;
    }

    private void database() {
        arrVideo = new ArrayList<>();
        recyclerView();
        Bundle bundle = getActivity().getIntent().getExtras();
        String title = bundle.getString("title");
        String link = bundle.getString("link");
        final String tournaments = bundle.getString("tournaments");
        Log.d("thanh", "database: "  + tournaments);

        mReference.child("Tournament").child(tournaments).child("videos").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                VideoLq  a = dataSnapshot.getValue(VideoLq.class);
                arrVideo.add(a);
                videoLqAdapter.notifyDataSetChanged();
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

    private void init() {
        rcvVideoLq = (RecyclerView) view.findViewById(R.id.rcv_video_lq);
    }

    private void recyclerView() {
        videoLqAdapter = new VideoLqAdapter(getContext(),arrVideo );
        rcvVideoLq.setLayoutManager(new LinearLayoutManager(getContext()));
        rcvVideoLq.setAdapter(videoLqAdapter);

    }
}
