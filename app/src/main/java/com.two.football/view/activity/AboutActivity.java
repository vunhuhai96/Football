package com.two.football.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.support.v7.widget.Toolbar;

import com.two.football.R;

public class AboutActivity extends AppCompatActivity {

    Toolbar toolAbout;
    ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        init();

        /*setSupportActionBar(toolAbout);
        getSupportActionBar().setDisplayShowTitleEnabled(false);*/

        getSupportActionBar().hide();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void init(){
        toolAbout = (Toolbar) findViewById(R.id.toolAbout);

        imgBack = (ImageView) findViewById(R.id.backAbout);
    }

}
