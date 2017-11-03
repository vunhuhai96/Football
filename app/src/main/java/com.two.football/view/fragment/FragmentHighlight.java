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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.two.football.adapter.SpinnerHighlightAdapter;
import com.two.football.model.Highlight;
import com.two.football.R;
import com.two.football.adapter.HighlightAdapter;
import com.two.football.view.activity.PlayVideoActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TWO on 10/23/2017.
 */

public class FragmentHighlight extends Fragment implements AdapterView.OnItemClickListener {
    private View view;
    private List<Highlight> list;
    private ListView listView;
    private HighlightAdapter adapter;
    private ImageView imgStar;
    private DatabaseReference mReference;
    private Spinner spinner;
    private Intent intent;


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

        listView.setOnItemClickListener(this);
    }

    public void initData(final String giaiDau) {
        list.clear();
        mReference.child("Tournament").child(giaiDau).child("videos").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Highlight highlight = dataSnapshot.getValue(Highlight.class);
                if (highlight.getVideoType().equals("Highlight")) {
                    list.add(highlight);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                initData(giaiDau);
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

    private void getGiaiDau() {
        final List<String> listText = new ArrayList<>();
        //listText.add("Mới nhất");
        mReference.child("Tournament").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String a = dataSnapshot.getKey();
                listText.add(a);
                SpinnerHighlightAdapter adapterSpinner = new SpinnerHighlightAdapter(getContext(), listText);

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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        intent = new Intent(getContext(), PlayVideoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("title", list.get(position).getTitle());
        bundle.putString("link", list.get(position).getUrlVideo());
        bundle.putString("tournaments",list.get(position).getTournaments());
        bundle.putString("id", list.get(position).getId());

        Log.d("thanh", "onItemClick: " +list.get(position).getTournaments());


        intent.putExtras(bundle);
        startActivity(intent);
    }
}
