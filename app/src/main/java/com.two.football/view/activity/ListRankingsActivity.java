package com.two.football.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.two.football.R;
import com.two.football.adapter.ListRankingsAdapter;
import com.two.football.model.Rankings;

import java.util.ArrayList;

public class ListRankingsActivity extends AppCompatActivity {

    TextView table;
    ListView listRankings;
    ArrayList<Rankings> list;
    private static ListRankingsAdapter adapterRa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_rankings);
        getSupportActionBar().hide();

        init();


    }

    private void init(){

        table = (TextView) findViewById(R.id.table);

        listRankings = (ListView) findViewById(R.id.listRa);
    }
}
