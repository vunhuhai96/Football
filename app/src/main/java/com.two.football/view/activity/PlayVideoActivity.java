package com.two.football.view.activity;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.two.football.R;
import com.two.football.adapter.PageAdapter;
import com.two.football.model.User;

import java.util.ArrayList;

public class PlayVideoActivity extends AppCompatActivity implements View.OnClickListener {
    private Bundle getBundle = null;
    private String link, title;
    private ViewPager viewPager;
    private FragmentManager fragmentManager;
    private PageAdapter pageAdapter;
    private TabLayout tabLayout;
    private VideoView video;
    private RecyclerView rcvBxh;
    private ImageView imgLikeVideo, imgShareVideo;
    private ProgressBar process;
    private MediaController controller;
    private TextView tvDetail, tvLikeNumber, tvShareNumber;
    private LinearLayout toolbar;

    private RelativeLayout rvDetails;
    private ImageView back;
    private String FILE_NAME = "user.txt";
    private DatabaseReference mDatabaseReference;
    private String idCurrentUser;
    private ShareDialog shareDialog;
    private ShareLinkContent shareLinkContent;
    private ArrayList<String> arrUserLiked;
    private boolean isLike = false;
    private int currentLike;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);
        getSupportActionBar().hide();
        restoringPreferences();
        init();
        adControll();
        result();
        playVideo();
        likeVideo();
    }

    public User restoringPreferences() {
        SharedPreferences preferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        String userName = preferences.getString("name", "");
        idCurrentUser = preferences.getString("id", "");
        String avatar = preferences.getString("avatar", "");
        User user = null;
        if (userName.equals("") || idCurrentUser.equals("") || avatar.equals("")) {
        } else {
            user = new User(userName, idCurrentUser, avatar);
            return user;
        }
        return user;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            rvDetails.setVisibility(View.VISIBLE);
            toolbar.setVisibility(View.VISIBLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            rvDetails.setVisibility(View.GONE);
            toolbar.setVisibility(View.GONE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getSupportActionBar().hide();
        }
    }
    private void result() {
        getBundle = getIntent().getExtras();
        link = getBundle.getString("link");
    }

    private void init() {
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        arrUserLiked = new ArrayList<>();
        getBundle = getIntent().getExtras();
        link = getBundle.getString("link");
        title = getBundle.getString("title");
        video = (VideoView) findViewById(R.id.videoView);
        process = (ProgressBar) findViewById(R.id.proBar);
        tvDetail = (TextView) findViewById(R.id.tv_detail);
        rvDetails = (RelativeLayout) findViewById(R.id.rv_detail);
        controller = new MediaController(this);
        back = (ImageView) findViewById(R.id.img_back);
        back.setOnClickListener(this);
        toolbar = (LinearLayout) findViewById(R.id.toolbar);

        shareDialog = new ShareDialog(PlayVideoActivity.this);

        imgShareVideo = (ImageView) findViewById(R.id.img_share_video);
        imgLikeVideo = (ImageView) findViewById(R.id.img_like_video);
        tvLikeNumber = (TextView) findViewById(R.id.tv_like_number);


        isCurrentLiked();
        imgLikeVideo.setOnClickListener(this);
        imgShareVideo.setOnClickListener(this);
    }

    private void playVideo() {
        String videoUrl = link;
        video.setMediaController(controller);
        video.setVideoURI(Uri.parse(videoUrl));
        video.requestFocus();
        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                process.setVisibility(View.GONE);
                video.start();
            }
        });


    }

    public void playlink(String link) {
        // play link

    }

    private void adControll() {
        viewPager = (ViewPager) findViewById(R.id.vp_play_video);
        tabLayout = (TabLayout) findViewById(R.id.tab_play_video);
        fragmentManager = getSupportFragmentManager();
        pageAdapter = new PageAdapter(fragmentManager);
        viewPager.setAdapter(pageAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setTabsFromPagerAdapter(pageAdapter);
    }

    private void likeVideo() {
        mDatabaseReference.child("Other").child(title).child("like").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
              currentLike = dataSnapshot.getValue(int.class);
              tvLikeNumber.setText(currentLike + "");
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
    private void isCurrentLiked(){
        arrUserLiked.clear();
        mDatabaseReference.child("Other").child(title).child("idUserLiked").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String idUser = dataSnapshot.getValue(String.class);
                arrUserLiked.add(idUser);
                Log.e("user", idUser );
                if (arrUserLiked.size()>0){
                    for (int i=0;i<arrUserLiked.size();i++){
                        if (idCurrentUser.equals(arrUserLiked.get(i))){
                            imgLikeVideo.setImageResource(R.drawable.icon_liked_video);
                            isLike = true;
                            Log.e("isLike", isLike+"");

                        }
                    }
                }


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    private void shareVideo() {
        if (ShareDialog.canShow(ShareLinkContent.class)) {
            title = getBundle.getString("title");
            shareLinkContent = new ShareLinkContent.Builder().setContentTitle(title).setContentDescription(title).setContentUrl(Uri.parse(link)).build();
        }
        shareDialog.show(shareLinkContent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.img_like_video:
                likeVideo();
                if (isLike) removeUserLike();
                else addUserLike();
                break;
            case R.id.img_share_video:
                shareVideo();
                break;
            default:
                break;
        }
    }

    private void addUserLike() {
        currentLike=currentLike+1;
        mDatabaseReference.child("Other").child(title).child("like").setValue(currentLike);
        imgLikeVideo.setImageResource(R.drawable.icon_liked_video);
        mDatabaseReference.child("Other").child(title).child("idUserLiked").child(idCurrentUser).setValue(idCurrentUser);
        isLike = true;
    }

    private void removeUserLike() {
        currentLike = currentLike-1;
        mDatabaseReference.child("Other").child(title).child("like").setValue(currentLike);
        imgLikeVideo.setImageResource(R.drawable.icon_like_video);
        mDatabaseReference.child("Other").child(title).child("idUserLiked").child(idCurrentUser).removeValue();
        isLike = false;
    }
}
