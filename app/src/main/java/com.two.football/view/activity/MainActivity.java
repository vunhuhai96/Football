package com.two.football.view.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.util.Base64;
import android.util.Log;

import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import android.widget.ImageView;
import android.widget.TextView;


import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.two.football.R;
import com.two.football.adapter.MainAdapter;
import com.two.football.model.User;


import com.two.football.R;
import com.two.football.adapter.MainAdapter;


import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private DrawerLayout drawerLayout;
    private ImageView btnNavigation;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Button btnMenuHome, btnMenuLive, btnMenuHighlight, btnTable, btnAbout, btnLTD, btnVideoFavorite;
    private MainAdapter mainAdapter;
    private CallbackManager callbackManager;
    private TextView tvAccountName;
    private DatabaseReference mDatabaseReference;
    private ImageView imageAccount;
    private boolean isLogin = false;
    private String FILE_NAME = "user.txt";

    private AccessToken accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        accessToken = AccessToken.getCurrentAccessToken();
        getSupportActionBar().hide();
        callbackManager = CallbackManager.Factory.create();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                isLogin = true;
                getProfileUser();
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
        initView();
        initPager();
        User user = restoringPreferences();
        if (user != null && user.getUrlAvatar() != null && user.getName() != null) {
            Picasso.with(getApplicationContext()).load(user.getUrlAvatar())
                    .resize(200, 200)
                    .into(imageAccount, new Callback() {
                        @Override
                        public void onSuccess() {
                            Bitmap imageBitmap = ((BitmapDrawable) imageAccount.getDrawable()).getBitmap();
                            RoundedBitmapDrawable imageDrawable = RoundedBitmapDrawableFactory.create(getResources(), imageBitmap);
                            imageDrawable.setCircular(true);
                            imageDrawable.setCornerRadius(Math.max(imageBitmap.getWidth(), imageBitmap.getHeight()) / 2.0f);
                            imageAccount.setImageDrawable(imageDrawable);
                        }

                        @Override
                        public void onError() {
                            imageAccount.setImageResource(R.drawable.ic_account);
                        }
                    });
            tvAccountName.setText(user.getName());
            isLogin = true;
        }



    }

    public User restoringPreferences() {
        SharedPreferences preferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        String userName = preferences.getString("name", "");
        String id = preferences.getString("id", "");
        String avatar = preferences.getString("avatar", "");
        User user = null;
        if (userName.equals("") || id.equals("") || avatar.equals("")) {
        } else {
            user = new User(userName, id, avatar);
            return user;
        }
        return user;
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

    @Override
    protected void onPause() {
        super.onPause();

    }

    private void getProfileUser() {
        final GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    String id = object.getString("id");
                    String name = object.getString("name");
                    String urlAvatar = "https://graph.facebook.com/" + id + "/picture?type=large";
                    savingPreferences(name, urlAvatar, id, false);
                    User user = new User(name, id, urlAvatar);
                    tvAccountName.setText(name);
                    mDatabaseReference.child("User").child(id).setValue(user);

                    Picasso.with(getApplicationContext()).load(urlAvatar)
                            .resize(200, 200)
                            .into(imageAccount, new Callback() {
                                @Override
                                public void onSuccess() {
                                    Bitmap imageBitmap = ((BitmapDrawable) imageAccount.getDrawable()).getBitmap();
                                    RoundedBitmapDrawable imageDrawable = RoundedBitmapDrawableFactory.create(getResources(), imageBitmap);
                                    imageDrawable.setCircular(true);
                                    imageDrawable.setCornerRadius(Math.max(imageBitmap.getWidth(), imageBitmap.getHeight()) / 2.0f);
                                    imageAccount.setImageDrawable(imageDrawable);
                                }

                                @Override
                                public void onError() {
                                    imageAccount.setImageResource(R.drawable.ic_account);
                                }
                            });


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,gender,birthday");
        graphRequest.setParameters(parameters);
        graphRequest.executeAsync();

    }

    private void initPager() {
        viewPager = (ViewPager) findViewById(R.id.main_viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        FragmentManager manager = getSupportFragmentManager();
        mainAdapter = new MainAdapter(manager);
        viewPager.setAdapter(mainAdapter);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setTabsFromPagerAdapter(mainAdapter);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_live);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_highlight);
    }


   /* private void getKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.two.football", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = null;
                try {
                    md = MessageDigest.getInstance("SHA");
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                md.update(signature.toByteArray());
                Log.e("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }*/

    private void initView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        btnNavigation = (ImageView) findViewById(R.id.btn_navigation);
        btnNavigation.setOnClickListener(this);

        btnMenuHome = (Button) findViewById(R.id.btn_menu_home);
        btnMenuLive = (Button) findViewById(R.id.btn_menu_live);
        btnMenuHighlight = (Button) findViewById(R.id.btn_menu_higlight);
        btnLTD = (Button) findViewById(R.id.btn_menu_ltd);
        btnTable = (Button) findViewById(R.id.btn_menu_table);
        btnAbout = (Button) findViewById(R.id.btn_menu_about);
        btnVideoFavorite = (Button) findViewById(R.id.btn_video_favorite);


        tvAccountName = (TextView) findViewById(R.id.tv_name_account);
        imageAccount = (ImageView) findViewById(R.id.img_account);

        btnMenuHome.setOnClickListener(this);
        btnMenuLive.setOnClickListener(this);
        btnMenuHighlight.setOnClickListener(this);
        btnTable.setOnClickListener(this);
        btnAbout.setOnClickListener(this);
        btnVideoFavorite.setOnClickListener(this);
        btnLTD.setOnClickListener(this);
        imageAccount.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_navigation:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
            case R.id.btn_menu_home:
                viewPager.setCurrentItem(0);
                drawerLayout.closeDrawers();
                break;
            case R.id.btn_menu_live:
                viewPager.setCurrentItem(1);
                drawerLayout.closeDrawers();
                break;
            case R.id.btn_menu_higlight:
                viewPager.setCurrentItem(2);
                drawerLayout.closeDrawers();
                break;
            case R.id.btn_menu_table:
                Intent inResults = new Intent(this, RankingsActivity.class);
                startActivity(inResults);
                break;
            case R.id.btn_menu_about:
                Intent inAbout = new Intent(this, AboutActivity.class);
                startActivity(inAbout);
                break;
            case R.id.btn_video_favorite:
                Intent intent = new Intent(this, VideoFavoriteActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_menu_ltd:
                Intent intentLTD = new Intent(this, LTDActivity.class);
                startActivity(intentLTD);
                break;

            case R.id.img_account:
                if (isLogin) logOut();
                else
                    LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
                break;
            default:
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    private void logOut() {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(MainActivity.this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(MainActivity.this);
        }
        builder.setTitle("Log Out")
                .setMessage("Bạn có muốn đăng xuất không ?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        LoginManager.getInstance().logOut();
                        tvAccountName.setText("Đăng Nhập");
                        imageAccount.setImageResource(R.drawable.ic_account);
                        savingPreferences(null, null, null, true);
                        isLogin = false;
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert).show();

    }
}
