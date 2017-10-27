package com.two.football.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.two.football.R;

/**
 * Created by TWO on 10/27/2017.
 */

public class VideoPlusActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_favorite);
        initView();
    }

    private void initView() {
        findViewById(R.id.btn_video_favorite_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_video_favorite_back:
                onBackPressed();
                break;
            default:
                break;
        }
    }
}
