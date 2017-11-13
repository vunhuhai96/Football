package com.two.football.view.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.two.football.R;
import com.two.football.adapter.MainAdapter;
import com.two.football.model.LogoutFacebookListener;
import com.two.football.model.User;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private DrawerLayout drawerLayout;
    private ImageView btnNavigation;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Button btnMenuHome, btnMenuLive, btnMenuHighlight, btnTable, btnAbout, btnLTD, btnVideoFavorite, btnMEnuResult;
    private MainAdapter mainAdapter;
    private CallbackManager callbackManager;
    private TextView tvAccountName;
    private DatabaseReference mDatabaseReference;
    private ImageView imageAccount;
    private boolean isLogin = false;
    private String FILE_NAME = "user.txt";
    public static String ID;
    private LinearLayout drawer;
    private AccessToken accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        callbackManager = CallbackManager.Factory.create();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        accessToken = AccessToken.getCurrentAccessToken();

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
     loadUser();
    }

    private void loadUser(){
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
    public void logoutFromFacebook(final LogoutFacebookListener listener) {

        if (AccessToken.getCurrentAccessToken() == null) {
            // already logged out
            listener.onLoggedOutFromFacebook();
            return;
        }

        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE, new GraphRequest
                .Callback() {

            @Override
            public void onCompleted(GraphResponse graphResponse) {
                LoginManager.getInstance().logOut();
                listener.onLoggedOutFromFacebook();
            }
        }).executeAsync();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
    public User restoringPreferences() {
        SharedPreferences preferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        String userName = preferences.getString("name", "");
        String id = preferences.getString("id", "");
        String avatar = preferences.getString("avatar", "");
        ID = id;
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
            ID = id;
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

    private void initView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        btnNavigation = (ImageView) findViewById(R.id.btn_navigation);
        btnMenuHome = (Button) findViewById(R.id.btn_menu_home);
        btnMenuLive = (Button) findViewById(R.id.btn_menu_live);
        btnMenuHighlight = (Button) findViewById(R.id.btn_menu_higlight);
        btnLTD = (Button) findViewById(R.id.btn_menu_ltd);
        btnTable = (Button) findViewById(R.id.btn_menu_table);
        btnAbout = (Button) findViewById(R.id.btn_menu_about);
        btnVideoFavorite = (Button) findViewById(R.id.btn_video_favorite);
        tvAccountName = (TextView) findViewById(R.id.tv_name_account);
        imageAccount = (ImageView) findViewById(R.id.img_account);
        btnMEnuResult = (Button) findViewById(R.id.btn_menu_results);
        btnMEnuResult.setOnClickListener(this);
        btnMenuHome.setOnClickListener(this);
        btnMenuLive.setOnClickListener(this);
        btnMenuHighlight.setOnClickListener(this);
        btnTable.setOnClickListener(this);
        btnAbout.setOnClickListener(this);
        btnVideoFavorite.setOnClickListener(this);
        btnLTD.setOnClickListener(this);
        imageAccount.setOnClickListener(this);
        btnNavigation.setOnClickListener(this);

        drawer = (LinearLayout) findViewById(R.id.drawer);

        int width = Resources.getSystem().getDisplayMetrics().widthPixels;
        int height = Resources.getSystem().getDisplayMetrics().heightPixels;

        if (width > 1199){
            DrawerLayout.LayoutParams params = (DrawerLayout.LayoutParams)drawer.getLayoutParams();
            params.width = (int) (width*(0.6));
            drawer.setLayoutParams(params);
        }
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
                finish();
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
            case R.id.btn_menu_results:
                intent = new Intent(MainActivity.this, ResultActivity.class);
                startActivity(intent);
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
        builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Bạn có muốn đăng xuất không ?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        logoutFromFacebook(new LogoutFacebookListener() {
                            @Override
                            public void onLoggedOutFromFacebook() {

                            }
                        });
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
    @Override
    protected void onResume() {
        loadUser();
        super.onResume();
    }
}
