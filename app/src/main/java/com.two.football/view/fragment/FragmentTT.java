package com.two.football.view.fragment;


import android.content.Intent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.two.football.R;
import com.two.football.adapter.BlAdapter;
import com.two.football.model.BLuan;
import com.two.football.model.User;

import com.two.football.view.activity.PlayVideoActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class FragmentTT extends Fragment implements View.OnClickListener {
    private TextView tvDetail;
    private Bundle getBundle = null;
    private String title, title2;
    private Intent intent;

    private ImageView imageAccountComment, imageSendComment;
    private EditText edtCommentContent;
    private RecyclerView recyclerViewComment;
    private ArrayList<BLuan> bLuans;
    private BlAdapter blAdapter;
    private DatabaseReference mDatabaseReference;
    private User user;

    public FragmentTT() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_video, container, false);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        tvDetail = (TextView) view.findViewById(R.id.tv_detail);
        getBundle = getActivity().getIntent().getExtras();
        title = getBundle.getString("title");


        intent = getActivity().getIntent();
        title2 = intent.getStringExtra("title");

        if (title2 == null){
            tvDetail.setText(title);
        }else {
            tvDetail.setText(title2);
        }


        tvDetail.setText(title);

        imageAccountComment = (ImageView) view.findViewById(R.id.img_account_comment);
        imageSendComment = (ImageView) view.findViewById(R.id.image_send_comment);
        edtCommentContent = (EditText) view.findViewById(R.id.edt_comment_content);

        PlayVideoActivity activity = (PlayVideoActivity) getActivity();
        user = activity.restoringPreferences();
        circleView(imageAccountComment);
        recyclerViewComment = (RecyclerView) view.findViewById(R.id.rcv_bl);
        recyclerViewComment.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewComment.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), layoutManager.getOrientation());
        recyclerViewComment.addItemDecoration(dividerItemDecoration);

        bLuans = new ArrayList<>();
        blAdapter = new BlAdapter(getContext(), bLuans);
        recyclerViewComment.setAdapter(blAdapter);
        try {
            bLuans.clear();
            mDatabaseReference.child("Comments").child(title).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    BLuan bLuan = dataSnapshot.getValue(BLuan.class);
                    bLuans.add(0,bLuan);
                    blAdapter.notifyDataSetChanged();
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

        } catch (Exception e) {
        }

        imageSendComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user == null || user.getName().equals("") || user.getName().equals("Đăng Nhập")) {
                    Toast.makeText(getContext(), "Mời bạn đăng nhập để bình luận ", Toast.LENGTH_SHORT).show();
                } else {
                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    String formattedDate = df.format(c.getTime());
                    final BLuan bLuan = new BLuan(user.getId(), user.getName(), user.getUrlAvatar(), edtCommentContent.getText().toString(), formattedDate, title);
                    mDatabaseReference.child("Comments").child(title).push().setValue(bLuan);
                    edtCommentContent.setText("");
                    InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);

                }
            }
        });


        return view;
    }

    @Override
    public void onClick(View view) {

    }

    private void circleView(final ImageView imageView) {
        if (user != null && user.getUrlAvatar() != null) {
            Picasso.with(getContext()).load(user.getUrlAvatar())
                    .resize(50, 50)
                    .into(imageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            Bitmap imageBitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                            RoundedBitmapDrawable imageDrawable = RoundedBitmapDrawableFactory.create(getResources(), imageBitmap);
                            imageDrawable.setCircular(true);
                            imageDrawable.setCornerRadius(Math.max(imageBitmap.getWidth(), imageBitmap.getHeight()) / 2.0f);
                            imageView.setImageDrawable(imageDrawable);
                        }

                        @Override
                        public void onError() {
                            imageView.setImageResource(R.drawable.ic_account);
                        }});
        }
    }
}
