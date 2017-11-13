package com.two.football.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.two.football.R;
import com.two.football.adapter.HighlightAdapter;
import com.two.football.model.Highlight;
import com.two.football.view.activity.PlayVideoActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TWO on 10/27/2017.
 */

public class FragmentClubVideo extends Fragment implements AdapterView.OnItemClickListener {
    private View view;
    private ListView listView;
    private List<Highlight> list;
    private HighlightAdapter adapter;
    private DatabaseReference reference;
    private Intent intent;

    public FragmentClubVideo() {
        reference = FirebaseDatabase.getInstance().getReference();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_club_video, container, false);
        initView();
        initVideo();
        return view;
    }

    private void initVideo() {
        list = new ArrayList<>();
        reference.child("Tournament").child("Premier League").child("videos").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Highlight highlight = dataSnapshot.getValue(Highlight.class);
                list.add(highlight);

                adapter = new HighlightAdapter(getContext(), list);

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
        listView = (ListView) view.findViewById(R.id.lv_club_video);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        intent = new Intent(getContext(), PlayVideoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("title", list.get(position).getTitle());
        bundle.putString("link", list.get(position).getUrlVideo());
        bundle.putString("tournaments",list.get(position).getTournaments());
        bundle.putString("id",list.get(position).getId());
        intent.putExtras(bundle);

        intent.putExtras(bundle);

        startActivity(intent);
    }
}
