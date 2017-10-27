package com.two.football.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.two.football.model.Club;
import com.two.football.R;
import com.two.football.adapter.ClubAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TWO on 10/24/2017.
 */

public class ClubActivity extends Activity implements View.OnClickListener {

    private ListView listView;
    private List<Club> list;
    private ClubAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club);
        initView();
        initClub();
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.lv_club);
        findViewById(R.id.btn_club_back).setOnClickListener(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ClubActivity.this, InfoClubActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initClub() {
        list = new ArrayList<>();

        for (int i=0;i<20;i++){
            list.add(new Club());
        }

        adapter = new ClubAdapter(ClubActivity.this, list);
        listView.setAdapter(adapter);
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
