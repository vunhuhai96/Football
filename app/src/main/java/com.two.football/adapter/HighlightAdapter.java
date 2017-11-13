package com.two.football.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.two.football.Utils;
import com.two.football.model.Highlight;
import com.two.football.R;
import com.two.football.model.User;
import com.two.football.view.activity.MainActivity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class HighlightAdapter extends BaseAdapter implements Utils{
    private List<Highlight> list;
    private LayoutInflater inflater;
    private Context context;
    private DatabaseReference reference;
    private String FILE_NAME = "user.txt";
    SharedPreferences preferences;
    String userName, idCurrentUser, avatar;
    private boolean love;
    private View view;

    public HighlightAdapter(Context context, List<Highlight> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
        reference = FirebaseDatabase.getInstance().getReference();

    }

    public HighlightAdapter() {
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Highlight getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_higlight, parent, false);
            holder = new ViewHolder();

            holder.imageView = (ImageView) convertView.findViewById(R.id.img_highlight);
            holder.imgStar = (ImageView) convertView.findViewById(R.id.btn_star);
            //holder.imgShare = (ImageView) convertView.findViewById(R.id.btn_share);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title_highlight);

            DisplayMetrics metrics = context.getResources().getDisplayMetrics();
            int width = metrics.widthPixels;

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, width / 2);
            holder.imageView.setLayoutParams(layoutParams);
            User user = restoringPreferences();
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Highlight highlight = list.get(position);

        Picasso.with(context).load(highlight.getUrlThumbnail()).into(holder.imageView);
        holder.tvTitle.setText(highlight.getTitle());

        final ViewHolder finalHolder = holder;

        holder.imgStar.setImageResource(R.drawable.ic_star_1);

        final String id = MainActivity.ID;
        final SharedPreferences sharedPreferences = context.getSharedPreferences("LOVE", Context.MODE_PRIVATE);
        love = sharedPreferences.getBoolean(id+"/"+highlight.getTitle(),false);
        if (love){
            holder.imgStar.setImageResource(R.drawable.ic_star_2);
        } else {
            holder.imgStar.setImageResource(R.drawable.ic_star_1);
        }

        holder.imgStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (userName.equals("") || idCurrentUser.equals("") || avatar.equals("")) {
                    showDialogLogin();
                    Toast.makeText(context, "Bạn cần đăng nhập", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences sharedPreferences = context.getSharedPreferences("LOVE", Context.MODE_PRIVATE);
                    boolean lo = sharedPreferences.getBoolean(id+"/"+highlight.getTitle(), false);

                    Log.d("love", lo+"");

                    if (lo==false) {
                        finalHolder.imgStar.setImageResource(R.drawable.ic_star_2);
                        reference.child("User").child(id).child("Video").child(highlight.getTitle()).setValue(highlight);
                        Toast.makeText(context, "Đã lưu vào mục video yêu thích", Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
                    } else {
                        finalHolder.imgStar.setImageResource(R.drawable.ic_star_1);
                        Toast.makeText(context, "Đã xóa khỏi mục video yêu thích", Toast.LENGTH_SHORT).show();
                        reference.child("User").child(id).child("Video").child(highlight.getTitle()).removeValue();
                        notifyDataSetChanged();
                    }

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(id+"/"+highlight.getTitle(), !lo);
                    editor.commit();
                }


            }
        });
        view = convertView;
        return convertView;
    }

    @Override
    public void checkLove(boolean love) {
        ViewHolder holder = new ViewHolder();
        holder.imgStar = (ImageView) view.findViewById(R.id.btn_star);
        this.love = love;
        if (love==false){
            holder.imgStar.setImageResource(R.drawable.ic_star_1);
        }
    }

    public void showDialogLogin() {
        android.support.v7.app.AlertDialog.Builder builder;
        builder = new android.support.v7.app.AlertDialog.Builder(context);
        builder.setMessage("Bạn cần đăng nhập để thực hiện chức năng !")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        LoginManager.getInstance().logInWithReadPermissions((Activity) context, Arrays.asList("public_profile"));
                        getProfileUser();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
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
                    reference.child("User").child(id).setValue(user);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,gender,birthday");
        graphRequest.setParameters(parameters);
        graphRequest.executeAsync();

        notifyDataSetChanged();

    }

    private void savingPreferences(String name, String avatar, String id, boolean chk) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, context.MODE_PRIVATE);
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
        preferences = context.getSharedPreferences(FILE_NAME, context.MODE_PRIVATE);
        userName = preferences.getString("name", "");
        idCurrentUser = preferences.getString("id", "");
        avatar = preferences.getString("avatar", "");
        User user = null;
        if (userName.equals("") || idCurrentUser.equals("") || avatar.equals("")) {
        } else {
            user = new User(userName, idCurrentUser, avatar);
            return user;
        }
        return user;
    }

    private class ViewHolder {
        ImageView imageView;
        ImageView imgStar;
        ImageView imgShare;
        TextView tvTitle;
    }
}
