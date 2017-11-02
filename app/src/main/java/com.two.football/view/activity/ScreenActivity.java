package com.two.football.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.two.football.R;
import com.two.football.model.VideoLq;

import java.util.ArrayList;


public class ScreenActivity extends AppCompatActivity {
   private Bundle bundle ;
   private Intent intent;
   private String link;
   private String title;
   private ArrayList<VideoLq> videos;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_screen);

        result();
        next();
    }

    private void next() {
        final Handler handler = new Handler();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                intent = new Intent(ScreenActivity.this,PlayVideoActivity.class);
                intent.putExtra("link",link);
                intent.putExtra("title",title);

                startActivity(intent);
                //handler.postDelayed(this,1000);

            }
        };
        handler.postDelayed(r,1000);



//        bundle = new Bundle();

//        bundle.putString("title", videos.get(position).getTitle());
//        bundle.putString("link", videos.get(position).getUrlVideo());
//        intent.putExtras(bundle);

    }



    private void result() {
        bundle = getIntent().getExtras();
        link = bundle.getString("link");
        title = bundle.getString("title");

        Log.d("asdasd", "result: " + link);
        Log.d("asdasd", "result: " + title);

    }


}
