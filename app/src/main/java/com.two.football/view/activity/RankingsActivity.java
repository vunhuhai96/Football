package com.two.football.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.two.football.R;
import com.two.football.adapter.RankingsAdapter;
import com.two.football.model.Rankings;

import java.util.ArrayList;
import java.util.List;

public class RankingsActivity extends AppCompatActivity {

    private ImageView backRe;
    private TextView nameToolbar;
    private Spinner spinRo, spinTable;
    private ListView lvRankings;
    private ArrayList<Rankings> listRa;
    private RankingsAdapter adapter;
    private DatabaseReference mReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rankings);
        init();
        getSupportActionBar().hide();
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mReference = FirebaseDatabase.getInstance().getReference();

        backRe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getGiaiDau();

    }

    private void init(){
        nameToolbar = (TextView) findViewById(R.id.tv_name_toolbar);
        nameToolbar.setText("Bảng xếp hạng");

        backRe = (ImageView) findViewById(R.id.img_back);

        spinRo = (Spinner) findViewById(R.id.spinRound);
        spinTable = (Spinner) findViewById(R.id.spinTabble);

        lvRankings = (ListView) findViewById(R.id.listRankings);
    }

    private void initRanking(final String listGiai, final String table){
        listRa = new ArrayList<>();
        listRa.clear();

        mReference.child("Tournament").child(listGiai).child("rankings").child(table).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Rankings rankings = dataSnapshot.getValue(Rankings.class);
                listRa.add(rankings);
                adapter = new RankingsAdapter(listRa, getApplicationContext());
                lvRankings.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                adapter.notifyDataSetChanged();
                initRanking(listGiai, table);
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

    private void getGiaiDau(){
        final List<String> list = new ArrayList<>();
        mReference.child("Tournament").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String a = dataSnapshot.getKey();
                list.add(a);

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(RankingsActivity.this, android.R.layout.simple_spinner_dropdown_item, list);
                spinRo.setAdapter(adapter);
                spinRo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        initSpinTable(list.get(i));
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

    public void initSpinTable(final String listGiai){
        final List<String> listTable = new ArrayList<>();
        listTable.clear();
        mReference.child("Tournament").child(listGiai).child("rankings").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                final String table = dataSnapshot.getKey();
                listTable.add("Bảng " +table);

                ArrayAdapter<String> adapterTable = new ArrayAdapter<String>(RankingsActivity.this, android.R.layout.simple_spinner_dropdown_item, listTable);
                spinTable.setAdapter(adapterTable);
                spinTable.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        String tvTable = listTable.get(i);
                        tvTable = tvTable.substring(5);
                        initRanking(listGiai, tvTable + "");

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
