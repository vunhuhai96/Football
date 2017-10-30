package com.two.football.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.two.football.R;
import com.two.football.adapter.ListResultsAdapter;
import com.two.football.model.Results;

import java.util.ArrayList;
import java.util.List;

public class ListResultsActivity extends AppCompatActivity {

    TextView table;
    ListView listResults;
    ArrayList<Results> list;
    private static ListResultsAdapter adapterRe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_results);
        getSupportActionBar().hide();

        init();


    }

    private void init(){

        table = (TextView) findViewById(R.id.table);

        listResults = (ListView) findViewById(R.id.listRe);
    }
}
