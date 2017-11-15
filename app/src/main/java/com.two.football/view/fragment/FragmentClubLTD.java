package com.two.football.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.two.football.R;
import com.two.football.adapter.ClubLTDAdapter;
import com.two.football.model.Club;
import com.two.football.model.ClubLTD;
import com.two.football.model.LTD;
import com.two.football.view.activity.InfoClubActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TWO on 10/27/2017.
 */

public class FragmentClubLTD extends Fragment {
    private View view;
    private List<ClubLTD> list;
    private ListView listView;
    private ClubLTDAdapter adapter;
    private DatabaseReference reference;
    private String key;

    public FragmentClubLTD(){
        reference = FirebaseDatabase.getInstance().getReference();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_club_ltd, container, false);
        key = ((InfoClubActivity) getActivity()).getKey();
        initView();
        return view;
    }

    private void initView() {
        listView = (ListView) view.findViewById(R.id.lv_club_ltd);


        list = new ArrayList<>();

        reference.child("Club").child(key).child("ltd").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ClubLTD ltd = dataSnapshot.getValue(ClubLTD.class);
                list.add(ltd);
                adapter = new ClubLTDAdapter(getContext(), list);
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
}
