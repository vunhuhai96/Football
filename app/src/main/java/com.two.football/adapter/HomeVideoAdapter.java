package com.two.football.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.two.football.R;
import com.two.football.model.Video;
import com.two.football.view.activity.PlayVideoActivity;

import java.util.List;

/**
 * Created by TWO on 10/25/2017.
 */

public class HomeVideoAdapter extends RecyclerView.Adapter<HomeVideoAdapter.ViewHolder> {
    private List<Video> list;
    private Context context;

    public HomeVideoAdapter(Context context, List<Video> list){
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Video video = list.get(position);

        Picasso.with(context).load(video.getUrlThumbnail()).into(holder.imageView);
        holder.textView.setText(video.getTitle());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlayVideoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("title",list.get(position).getTitle());
                bundle.putString("link",list.get(position).getUrlVideo());
                intent.putExtras(bundle);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        LinearLayout layout;
        public ViewHolder(View v) {
            super(v);
            textView = (TextView) v.findViewById(R.id.tv_home_title_video);
            imageView = (ImageView) v.findViewById(R.id.img_home_video);
            layout = (LinearLayout) v.findViewById(R.id.linear_video);
        }
    }
}
