package com.two.football.view.fragment;

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
import com.two.football.model.Live;
import com.two.football.R;
import com.two.football.adapter.LiveAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TWO on 10/23/2017.
 */

public class FragmentLive extends Fragment implements AdapterView.OnItemClickListener{
    private View view;
    private ListView listView;
    private LiveAdapter adapter;
    private List<Live> list;
    private DatabaseReference mReference;

    public FragmentLive(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.live_layout, container, false);
        mReference = FirebaseDatabase.getInstance().getReference();
        initView();
        initLive();
        return view;
    }

    private void initLive() {
        list = new ArrayList<>();
        for (int i=0;i<15;i++){
            list.add(new Live());
        }
//        initData(tranDau);
        adapter = new LiveAdapter(getContext(), list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);
    }

    public void initData(final String tranDau){
        list.clear();
        mReference.child("Tournament").child(tranDau).child("videos").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Live live = dataSnapshot.getValue(Live.class);
                if(live.getVideoType().equals("Live Stream")){
                    list.add(live);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                initData(tranDau);
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
        listView = (ListView) view.findViewById(R.id.lv_live);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
