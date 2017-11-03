package com.two.football.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.two.football.R;
import com.two.football.adapter.ClubLTDAdapter;
import com.two.football.adapter.LTDAdapter;
import com.two.football.adapter.SpinnerHighlightAdapter;
import com.two.football.adapter.SpinnerLTDAdapter;
import com.two.football.model.LTD;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TWO on 11/1/2017.
 */

public class LTDActivity extends AppCompatActivity implements View.OnClickListener {
    private List<LTD> list;
    private ListView listView;
    private LTDAdapter adapter;
    private ImageView btnBack;
    private Spinner spinner, spinnerVong;
    private DatabaseReference reference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ltd);

        reference = FirebaseDatabase.getInstance().getReference();

        getSupportActionBar().hide();
        initView();
        initSpinner();
    }

    private void initSpinnerVong(final String giaiDau) {
        final List<String> listVong = new ArrayList<>();
        listVong.clear();

        reference.child("Tournament").child(giaiDau).child("ltd").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                final String vong  = dataSnapshot.getKey();

                int soVong = Integer.parseInt(vong);

                listVong.add("Vòng "+(soVong+1));
                SpinnerLTDAdapter adapterSpinner = new SpinnerLTDAdapter(LTDActivity.this, listVong);

                spinnerVong.setAdapter(adapterSpinner);

                spinnerVong.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String tvVong = listVong.get(position);
                        tvVong = tvVong.substring(5);
                        int tvSoVong = Integer.parseInt(tvVong) - 1;

                        initLTD(giaiDau, tvSoVong+"");
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

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

    private void initSpinner() {
        final List<String> listText = new ArrayList<>();
        listText.clear();

        reference.child("Tournament").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                listText.add(dataSnapshot.getKey());

                SpinnerHighlightAdapter adapterSpinner = new SpinnerHighlightAdapter(LTDActivity.this, listText);

                spinner.setAdapter(adapterSpinner);

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        initSpinnerVong(listText.get(position));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

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

    private void initLTD(String giaiDau, String vong) {
        list = new ArrayList<>();
        list.clear();

        reference.child("Tournament").child(giaiDau).child("ltd").child(vong).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                LTD ltd = dataSnapshot.getValue(LTD.class);
                list.add(ltd);
                adapter = new LTDAdapter(LTDActivity.this, list);
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
        listView = (ListView) findViewById(R.id.lv_ltd);

        spinner = (Spinner) findViewById(R.id.spinner_ltd);
        spinnerVong = (Spinner) findViewById(R.id.spinner_ltd_vong);

        btnBack = (ImageView) findViewById(R.id.btn_ltd_back);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ltd_back:
                onBackPressed();
                break;
            default:
                break;

        }
    }
}
