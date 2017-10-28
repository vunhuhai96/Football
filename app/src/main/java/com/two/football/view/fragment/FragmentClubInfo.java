package com.two.football.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.two.football.R;
import com.two.football.model.Club;
import com.two.football.view.activity.InfoClubActivity;

/**
 * Created by TWO on 10/25/2017.
 */

public class FragmentClubInfo extends Fragment {
    private View view;
    private ImageView imgLogo;
    private TextView tvName;
    private DatabaseReference mReference;

    public FragmentClubInfo() {
        mReference = FirebaseDatabase.getInstance().getReference();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_club_info, container, false);
        initView();
        initData();
        return view;
    }

    private void initData() {
        final String id = ((InfoClubActivity) getActivity()).getKey();
        mReference.child("Club").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                if (id.equals(dataSnapshot.getKey())){
                    Club club = dataSnapshot.getValue(Club.class);
                    Picasso.with(getContext()).load(club.getClubLogo()).into(imgLogo);
                    tvName.setText(club.getClubName());
                }
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
        imgLogo = (ImageView) view.findViewById(R.id.img_logo_info_club);
        tvName = (TextView) view.findViewById(R.id.tv_name_club_info);
    }
}
