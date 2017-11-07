package com.two.football.view.activity;


import android.content.DialogInterface;
import android.content.Intent;

import android.content.SharedPreferences;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
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
import android.widget.Toast;
import android.widget.VideoView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.two.football.R;
import com.two.football.adapter.PageAdapter;
import com.two.football.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class PlayVideoActivity extends AppCompatActivity implements View.OnClickListener {
    private Bundle getBundle = null;
    private String title;
    private String link, link2;
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
    private Intent intent;
    private String FILE_NAME = "user.txt";
    private DatabaseReference mDatabaseReference;
    private String idCurrentUser;
    private ShareDialog shareDialog;
    private ShareLinkContent shareLinkContent;
    private ArrayList<String> arrUserLiked;
    private boolean isLike = false;
    private int currentLike;
    private LinearLayout lnLayout;
    private TextView nameToolbar;
    private CallbackManager callbackManager;
    public boolean isLogin = false;
    private boolean isShare = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_play_video);
        getSupportActionBar().hide();
        isShare=false;
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                isLogin = true;
                getProfileUser();

                restoringPreferences();
                if (isShare) {
                    shareVideo();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancel() {
                Log.e("Thong bao", "Cancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.e("Thong bao", "Error");
            }
        });
        restoringPreferences();
        init();
        adControll();
        result();
        playVideo();
        likeVideo();
    }

    @Override
    protected void onResume() {
        restoringPreferences();
        getProfileUser();
        super.onResume();
    }


    public void getProfileUser() {
        final GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    String id = object.getString("id");
                    String name = object.getString("name");
                    String urlAvatar = "https://graph.facebook.com/" + id + "/picture?type=large";
                    savingPreferences(name, urlAvatar, id, false);
                    User user = new User(name, id, urlAvatar);
                    mDatabaseReference.child("User").child(id).setValue(user);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,gender,birthday");
        graphRequest.setParameters(parameters);
        graphRequest.executeAsync();

    }

    private void savingPreferences(String name, String avatar, String id, boolean chk) {
        SharedPreferences sharedPreferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (chk) {
            editor.clear();
        } else {
            editor.putString("name", name);
            editor.putString("avatar", avatar);
            editor.putString("id", id);
        }
        editor.commit();
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
            lnLayout.setVisibility(View.VISIBLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            rvDetails.setVisibility(View.GONE);
            toolbar.setVisibility(View.GONE);
            lnLayout.setVisibility(View.GONE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getSupportActionBar().hide();
        }
    }

    private void result() {
        getBundle = getIntent().getExtras();
        link = getBundle.getString("link");

        intent = getIntent();
        link2 = intent.getStringExtra("link");

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
        nameToolbar = (TextView) findViewById(R.id.tv_name_toolbar);
        nameToolbar.setText("Bóng đá online");
        shareDialog = new ShareDialog(PlayVideoActivity.this);

        imgShareVideo = (ImageView) findViewById(R.id.img_share_video);
        imgLikeVideo = (ImageView) findViewById(R.id.img_like_video);
        tvLikeNumber = (TextView) findViewById(R.id.tv_like_number);
        lnLayout = (LinearLayout) findViewById(R.id.ln_like);

        isCurrentLiked();
        imgLikeVideo.setOnClickListener(this);
        imgShareVideo.setOnClickListener(this);
    }

    private void playVideo() {

        String videoUrl = null;
        if (link2 == null) {
            videoUrl = link;
        } else {
            videoUrl = link2;
        }
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
                try {
                    if (dataSnapshot.getValue(int.class) != null) {
                        currentLike = dataSnapshot.getValue(int.class);
                        tvLikeNumber.setText(currentLike + "");
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }


    private void isCurrentLiked() {
        arrUserLiked.clear();
        mDatabaseReference.child("Other").child(title).child("idUserLiked").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String idUser = dataSnapshot.getValue(String.class);
                arrUserLiked.add(idUser);
                Log.e("user", idUser);
                if (arrUserLiked.size() > 0) {
                    for (int i = 0; i < arrUserLiked.size(); i++) {
                        if (idCurrentUser.equals(arrUserLiked.get(i))) {
                            imgLikeVideo.setImageResource(R.drawable.like2);
                            isLike = true;
                            Log.e("isLike", isLike + "");

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
                isShare=false;
                finish();
                break;
            case R.id.img_like_video:
                isShare=false;
                likeVideo();
                if (isLike) removeUserLike();
                else addUserLike();
                break;
            case R.id.img_share_video:
              User user = restoringPreferences();
                if (user==null){
                LoginManager.getInstance().logInWithReadPermissions(PlayVideoActivity.this, Arrays.asList("public_profile"));
            }else{ shareVideo();}
                isShare = true;
                break;
            default:
                break;
        }
    }

    public void showDialogLogin() {
        AlertDialog.Builder builder;
       builder = new AlertDialog.Builder(PlayVideoActivity.this);
        builder.setMessage("Bạn cần đăng nhập để thực hiện chức năng !")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        LoginManager.getInstance().logInWithReadPermissions(PlayVideoActivity.this, Arrays.asList("public_profile"));
                        getProfileUser();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    protected void onResumeFragments() {
        getProfileUser();
        restoringPreferences();
        super.onResumeFragments();
    }

    private void addUserLike() {
        SharedPreferences preferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        String userName = preferences.getString("name", "");
        idCurrentUser = preferences.getString("id", "");
        String avatar = preferences.getString("avatar", "");
        if (userName.equals("") || idCurrentUser.equals("") || avatar.equals(""))
            showDialogLogin();
        else {
            currentLike = currentLike + 1;
            mDatabaseReference.child("Other").child(title).child("like").setValue(currentLike);
            imgLikeVideo.setImageResource(R.drawable.like1);
            mDatabaseReference.child("Other").child(title).child("idUserLiked").child(idCurrentUser).setValue(idCurrentUser);
            isLike = true;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void removeUserLike() {
        SharedPreferences preferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        String userName = preferences.getString("name", "");
        idCurrentUser = preferences.getString("id", "");
        String avatar = preferences.getString("avatar", "");
        if (userName.equals("") || idCurrentUser.equals("") || avatar.equals(""))
            showDialogLogin();
        else {
            currentLike = currentLike - 1;
            mDatabaseReference.child("Other").child(title).child("like").setValue(currentLike);
            imgLikeVideo.setImageResource(R.drawable.like1);
            mDatabaseReference.child("Other").child(title).child("idUserLiked").child(idCurrentUser).removeValue();
            isLike = false;
        }
    }
}
