package com.two.football.view.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.two.football.adapter.SpinnerHighlightAdapter;
import com.two.football.adapter.SpinnerResultsAdapter;
import com.two.football.model.Club;
import com.two.football.R;
import com.two.football.adapter.ClubAdapter;
import com.two.football.model.Highlight;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TWO on 10/24/2017.
 */

public class ClubActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView listView;
    private List<Club> list;
    private ClubAdapter adapter;
    private DatabaseReference reference;
    private Spinner spinner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reference = FirebaseDatabase.getInstance().getReference();
        setContentView(R.layout.activity_club);
        //getSupportActionBar().hide();
        initView();
        initSpinner();
        initClub();
    }

    private void initSpinner() {
        ArrayList<String> listText = new ArrayList<>();
        listText.add("Bundesliga");
        listText.add("La Liga");
        listText.add("Premier League");

        SpinnerHighlightAdapter adapterSpinner = new SpinnerHighlightAdapter(ClubActivity.this, listText);

        spinner.setAdapter(adapterSpinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.lv_club);
        findViewById(R.id.btn_club_back).setOnClickListener(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ClubActivity.this, InfoClubActivity.class);
                intent.putExtra("ID_CLUB", list.get(position).getId());
                startActivity(intent);
            }
        });

        spinner = (Spinner) findViewById(R.id.spinner_club);
    }

    private void initClub() {
        list = new ArrayList<>();
        reference.child("Club").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Club club = dataSnapshot.getValue(Club.class);
                club.setId(dataSnapshot.getKey());
                list.add(club);
                adapter = new ClubAdapter(ClubActivity.this, list);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_club_back:
                onBackPressed();
                break;
            default:
                break;
        }
    }


}
