package com.two.football.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.two.football.R;
import com.two.football.adapter.ClubLTDAdapter;
import com.two.football.model.LTD;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TWO on 11/1/2017.
 */

public class LTDActivity extends AppCompatActivity implements View.OnClickListener {
    private List<LTD> list;
    private ListView listView;
    private ClubLTDAdapter adapter;
    private ImageView btnBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ltd);
        getSupportActionBar().hide();
        initView();
        initLTD();
    }

    private void initLTD() {
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new LTD());
        }

        adapter = new ClubLTDAdapter(LTDActivity.this, list);
        listView.setAdapter(adapter);
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.lv_ltd);
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
