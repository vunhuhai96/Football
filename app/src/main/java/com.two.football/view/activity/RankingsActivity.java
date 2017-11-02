package com.two.football.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.two.football.R;
import com.two.football.adapter.RankingsAdapter;
import com.two.football.adapter.SpinnerRankingsAdapter;
import com.two.football.model.Rankings;

import java.util.ArrayList;
import java.util.List;

public class RankingsActivity extends AppCompatActivity {

    private ImageView backRe;
    private TextView nameToolbar;
    private Spinner spinRo;
    private ListView lvRankings;
    private ArrayList<Rankings> listRa;
    private static RankingsAdapter adapterRa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rankings);
        init();
        getSupportActionBar().hide();
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        backRe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        SpinRo();

        listRa = new ArrayList<>();

        listRa.add(new Rankings("Bảng A"));
        listRa.add(new Rankings("Bảng B"));
        listRa.add(new Rankings("Bảng C"));
        listRa.add(new Rankings("Bảng D"));

        adapterRa = new RankingsAdapter(listRa, getApplicationContext());
        lvRankings.setAdapter(adapterRa);
    }

    private void init(){
        nameToolbar = (TextView) findViewById(R.id.tv_name_toolbar);
        nameToolbar.setText("Bảng xếp hạng");

        backRe = (ImageView) findViewById(R.id.img_back);

        spinRo = (Spinner) findViewById(R.id.spinRound);

        lvRankings = (ListView) findViewById(R.id.listRankings);
    }

    private void SpinRo(){
        List<String> listRo = new ArrayList<>();
        listRo.add("Champion League");
        listRo.add("EURO 2017");
        listRo.add("World Cup");

        ArrayAdapter<String> adapterRe = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listRo);
        spinRo.setAdapter(adapterRe);
    }
}
