package com.two.football.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
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
    private Spinner spinner;

    public FragmentHighlight() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.highlight_layout, container, false);
        mReference = FirebaseDatabase.getInstance().getReference();
        initView();
        getGiaiDau();
        return view;
    }

    private void initHiglight(String giaiDau) {
        list = new ArrayList<>();
        initData(giaiDau);
        adapter = new HighlightAdapter(getContext(), list);
        listView.setAdapter(adapter);

    }

    public void initData(String giaiDau){
        list.clear();
        mReference.child("Video").child("High Light").child(giaiDau).addChildEventListener(new ChildEventListener() {
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
    }

    private void initView() {
        listView = (ListView) view.findViewById(R.id.lv_highlight);

        spinner = (Spinner) view.findViewById(R.id.spinner);
    }

    private void getGiaiDau(){
        final List<String> listText = new ArrayList<>();
        listText.add("Mới nhất");
        mReference.child("Video").child("High Light").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String a =  dataSnapshot.getKey();
                listText.add(a);
                final ArrayAdapter<String> adapterSpinner = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, listText);
                adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapterSpinner);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        initHiglight(listText.get(i));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });
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
