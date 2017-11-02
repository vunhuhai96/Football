package com.two.football.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.two.football.R;

public class AboutActivity extends AppCompatActivity {

    private TextView tvNameToolBar;
    private ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        init();

        getSupportActionBar().hide();
    }

    private void init(){
        tvNameToolBar = (TextView) findViewById(R.id.tv_name_toolbar);
        imgBack = (ImageView) findViewById(R.id.img_back);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvNameToolBar.setText("Thông tin");
    }

}
