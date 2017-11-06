package com.two.football.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.two.football.R;
import com.two.football.model.VideoLq;
import java.util.ArrayList;

public class ScreenActivity extends AppCompatActivity {
   private Bundle bundle ;
   private Intent intent;
   private String link;
   private String title;
   private String tournaments;
   private String id;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);
        getSupportActionBar().hide();
        result();
        next();
    }

    private void next() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                intent = new Intent(ScreenActivity.this,PlayVideoActivity.class);
                bundle = new Bundle();
                bundle.putString("link",link);
                bundle.putString("title",title);
                bundle.putString("tournaments",tournaments);
                bundle.putString("id",id);
                intent.putExtras(bundle);

                startActivity(intent);
                finish();
            }
        }).start();
    }

    private void result() {
        bundle = getIntent().getExtras();
        link = bundle.getString("link");
        title = bundle.getString("title");
        tournaments = bundle.getString("tournaments");
        id = bundle.getString("id");
    }
}
