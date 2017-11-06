package com.two.football.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.two.football.model.Highlight;
import com.two.football.R;
import com.two.football.model.User;
import com.two.football.view.activity.MainActivity;
import com.two.football.view.activity.VideoFavoriteActivity;

import java.util.ArrayList;
import java.util.List;


public class HighlightAdapter extends BaseAdapter {
    private List<Highlight> list;
    private LayoutInflater inflater;
    private Context context;
    private DatabaseReference reference;
    private String FILE_NAME = "user.txt";
    SharedPreferences preferences;
    String userName, idCurrentUser, avatar;
    ArrayList<Highlight> arrHighlight = new ArrayList<>();
    Boolean isLove = false;
    private int a = 0;

    public HighlightAdapter(Context context, List<Highlight> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
        reference = FirebaseDatabase.getInstance().getReference();
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
            holder.imgShare = (ImageView) convertView.findViewById(R.id.btn_share);
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
        holder.imgStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String id = MainActivity.ID;
                if (userName.equals("") || idCurrentUser.equals("") || avatar.equals("")) {
                    Toast.makeText(context, "Bạn cần đăng nhập", Toast.LENGTH_SHORT).show();
                } else {
                    reference.child("User").child(id).child("Video").addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            Highlight highlight1 = dataSnapshot.getValue(Highlight.class);
                            highlight1.setKey(dataSnapshot.getKey());
                            arrHighlight.add(highlight1);
                        }

                        @Override
                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onChildRemoved(DataSnapshot dataSnapshot) {
                            Highlight highlight1 = dataSnapshot.getValue(Highlight.class);
                            highlight1.setKey(dataSnapshot.getKey());
                            arrHighlight.add(highlight1);
                        }

                        @Override
                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                    for (int i = 0; i < arrHighlight.size(); i++) {
                        if ((highlight.getTitle()).equals((arrHighlight.get(i)).getTitle())) {
                            a++;
                        }
                    }

                    if (a == 0) {
                        reference.child("User").child(id).child("Video").push().setValue(highlight);
                        Toast.makeText(context, "Đã lưu vào mục video yêu thích", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        return convertView;
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

    public void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Bạn phải đăng nhập !");
        builder.setMessage("Bạn có muốn đăng nhập không ?");
        builder.setCancelable(false);
        builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    private class ViewHolder {
        ImageView imageView;
        ImageView imgStar;
        ImageView imgShare;
        TextView tvTitle;
    }
}
