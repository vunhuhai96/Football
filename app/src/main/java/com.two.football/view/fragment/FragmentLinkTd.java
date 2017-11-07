package com.two.football.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import com.two.football.adapter.ReservesAdapter;
import com.two.football.model.Reserves;
import com.two.football.view.activity.PlayVideoActivity;
import com.two.football.view.activity.ScreenActivity;

import java.util.ArrayList;

import static android.media.CamcorderProfile.get;


public class FragmentLinkTd extends Fragment implements AdapterView.OnItemClickListener{

    private ListView lv;
    private ReservesAdapter adapter;
    private ArrayList<Reserves> list;
    private DatabaseReference mReference;
    private String title, id, tournaments;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_link_td,container,false);
        lv = (ListView) view.findViewById(R.id.listReserves);
        list = new ArrayList<>();
        result();
        return view;
    }

    private void result() {
        Bundle bundle = getActivity().getIntent().getExtras();
         id = bundle.getString("id");
         title = bundle.getString("title");
         tournaments = bundle.getString("tournaments");

        mReference = FirebaseDatabase.getInstance().getReference();

        mReference.child("Tournament").child(tournaments).child("videos").child(id).child("urlRe").
                addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Reserves reserves = dataSnapshot.getValue(Reserves.class);
                        list.add(reserves);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                        adapter.notifyDataSetChanged();
                        result();
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
        adapter = new ReservesAdapter(list,getContext(),R.layout.item_reserves);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(getContext(), ScreenActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("title",title);
        bundle.putString("link", list.get(i).getLink());
        bundle.putString("tournaments", tournaments );
        bundle.putString("tournament", list.get(i).getTournaments());
        bundle.putString("id",id);
        bundle.putString("id", list.get(i).getId());
        intent.putExtras(bundle);

        startActivity(intent);
    }
}
