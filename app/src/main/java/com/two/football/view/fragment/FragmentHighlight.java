package com.two.football.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.two.football.model.Highlight;
import com.two.football.R;
import com.two.football.adapter.HighlightAdapter;
import com.two.football.model.Video;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TWO on 10/23/2017.
 */

public class FragmentHighlight extends Fragment {
    private View view;
    private List<Highlight> list;
    private ListView listView;
    private HighlightAdapter adapter;
    private ImageView imgStar;
    private DatabaseReference mReference;

    public FragmentHighlight() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.highlight_layout, container, false);
        initView();
        initHiglight();
        return view;
    }

    private void initHiglight() {
        mReference = FirebaseDatabase.getInstance().getReference();
        list = new ArrayList<>();

        mReference.child("Video").child("High Light").child("Error 2016").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Highlight highlight = dataSnapshot.getValue(Highlight.class);
                list.add(highlight);
                adapter.notifyDataSetChanged();
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

        adapter = new HighlightAdapter(getContext(), list);
        listView.setAdapter(adapter);

    }

    private void initView() {
        listView = (ListView) view.findViewById(R.id.lv_highlight);


    }
}
