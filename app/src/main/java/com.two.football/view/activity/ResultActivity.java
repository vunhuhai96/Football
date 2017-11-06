package com.two.football.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.two.football.R;
import com.two.football.adapter.ResultAdapter;
import com.two.football.adapter.ResultListAdapter;
import com.two.football.adapter.SpinnerHighlightAdapter;
import com.two.football.model.LTD;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 5/11/2017.
 */

public class ResultActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView imgBack;
    private TextView tvNameTollbar;
    private Spinner spinner;
    private RecyclerView rcvResult;
    private ArrayList<LTD> arrResult;
    private ResultAdapter resultAdapter;
    private DatabaseReference reference;
    private ResultListAdapter resultListAdapter;
    private String round;
    private ArrayList<String> strings;
    private String giai;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        reference = FirebaseDatabase.getInstance().getReference();
        getSupportActionBar().hide();

        init();
    //    initRound();
        initSpinner();
    }

    private void initRound(  ) {
        strings = new ArrayList<>();
        strings.clear();

        reference.child("Tournament").child("Bundesliga").child("ltd").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                round = dataSnapshot.getKey();
                strings.add(round);
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

        initRound();

        reference.child("Tournament").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                listText.add(dataSnapshot.getKey());

                String a = dataSnapshot.getKey();

                SpinnerHighlightAdapter adapterSpinner = new SpinnerHighlightAdapter(ResultActivity.this, listText);
                spinner.setAdapter(adapterSpinner);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        //initRound();
                        initResult(listText.get(position));

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                initSpinner();
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

    private void initResult(final String giaiDau) {
        arrResult = new ArrayList<>();

        for (String s : strings){
            reference.child("Tournament").child(giaiDau).child("ltd").child(s).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    LTD ltd = dataSnapshot.getValue(LTD.class);
                    arrResult.add(ltd);

                    resultListAdapter = new ResultListAdapter(ResultActivity.this, arrResult);
                    rcvResult.setLayoutManager(new LinearLayoutManager(ResultActivity.this));
                    rcvResult.setAdapter(resultListAdapter);

                    //resultListAdapter.notifyDataSetChanged();
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    resultListAdapter.notifyDataSetChanged();
                    initResult(giaiDau);
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

    private void recyclerView() {
        resultAdapter = new ResultAdapter(this, arrResult);
        rcvResult.setLayoutManager(new LinearLayoutManager(this));
        rcvResult.setAdapter(resultAdapter);
    }

    private void init() {
        imgBack = (ImageView) findViewById(R.id.img_back);
        imgBack.setOnClickListener(this);

        tvNameTollbar = (TextView) findViewById(R.id.tv_name_toolbar);
        tvNameTollbar.setText("Kết quả");

        spinner = (Spinner) findViewById(R.id.spinner);
        rcvResult = (RecyclerView) findViewById(R.id.rcv_result);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
            default:
                break;
        }
    }
}
