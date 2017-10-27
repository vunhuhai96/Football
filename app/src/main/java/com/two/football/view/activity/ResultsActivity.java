package com.two.football.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.two.football.R;
import com.two.football.adapter.ResultsAdapter;
import com.two.football.adapter.SpinnerResultsAdapter;
import com.two.football.model.Results;

import java.util.ArrayList;
import java.util.List;

public class ResultsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Spinner spinRe, spinRo;
    private ListView lvResults;
    ArrayList<Results> listRe;
    private static ResultsAdapter adapterRe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        init();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        SpinRe();
        SpinRo();

        listRe = new ArrayList<>();

        listRe.add(new Results("Bảng A"));
        listRe.add(new Results("Bảng B"));
        listRe.add(new Results("Bảng C"));
        listRe.add(new Results("Bảng D"));

        adapterRe = new ResultsAdapter(listRe, getApplicationContext());
        lvResults.setAdapter(adapterRe);
    }

    private void init(){
        toolbar = (Toolbar) findViewById(R.id.toolResults);

        spinRe = (Spinner) findViewById(R.id.spinResults);
        spinRo = (Spinner) findViewById(R.id.spinRound);

        lvResults = (ListView) findViewById(R.id.listResults);
    }

    private void SpinRe(){
        ArrayList<String> list = new ArrayList<>();
        list.add("Champion League");
        list.add("EURO 2017");
        list.add("World Cup");

        SpinnerResultsAdapter spinnerRe = new SpinnerResultsAdapter(getApplicationContext(), list);
        spinRe.setAdapter(spinnerRe);
    }

    private void SpinRo(){
        List<String> listRo = new ArrayList<>();
        listRo.add("Vòng 1");
        listRo.add("Vòng 2");
        listRo.add("Vòng 3");

        ArrayAdapter<String> adapterRe = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listRo);
        spinRo.setAdapter(adapterRe);
    }
}
