package com.two.football.adapter;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.two.football.R;
import com.two.football.model.BLuan;

import java.util.ArrayList;


public class BlAdapter extends RecyclerView.Adapter<BlAdapter.ViewHolder> implements View.OnClickListener {
    private Context context;
    private ArrayList<BLuan> bLuans;
    private LayoutInflater inflater;

    public BlAdapter(Context context, ArrayList<BLuan> bLuans) {
        this.context = context;
        this.bLuans = bLuans;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_bl, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        BLuan bLuan = bLuans.get(position);

        Picasso.with(context).load(bLuan.getAvatar())
                .resize(50, 50)
                .into(holder.imgAvater, new Callback() {
                    @Override
                    public void onSuccess() {
                        Bitmap imageBitmap = ((BitmapDrawable) holder.imgAvater.getDrawable()).getBitmap();
                        RoundedBitmapDrawable imageDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), imageBitmap);
                        imageDrawable.setCircular(true);
                        imageDrawable.setCornerRadius(Math.max(imageBitmap.getWidth(), imageBitmap.getHeight()) / 2.0f);
                        holder.imgAvater.setImageDrawable(imageDrawable);
                    }

                    @Override
                    public void onError() {
                        holder.imgAvater.setImageResource(R.drawable.ic_account);
                    }
                });
        holder.tvName1.setText(bLuan.getName());
        holder.tvContent.setText(bLuan.getContent());
        holder.tvTime.setText(bLuan.getTime());




    }

    @Override
    public int getItemCount() {
        return bLuans.size();
    }

    @Override
    public void onClick(View view) {
        ViewHolder holder = null;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgAvater;
        private TextView tvName1;
        private TextView tvContent, tvTime;

        public ViewHolder(View itemView) {
            super(itemView);
            imgAvater = (ImageView) itemView.findViewById(R.id.image_account_comment);
            tvName1 = (TextView) itemView.findViewById(R.id.tv_name1);
            tvContent = (TextView) itemView.findViewById(R.id.tv_content);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);


        }
    }
}
