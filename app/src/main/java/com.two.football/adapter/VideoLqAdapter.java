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
import com.two.football.model.VideoLq;
import com.two.football.view.activity.ScreenActivity;

import java.util.ArrayList;

public class VideoLqAdapter extends RecyclerView.Adapter<VideoLqAdapter.ViewHolder>{
    private ArrayList<VideoLq> videos;
    private Context context;
    private LayoutInflater inflater;
    private Picasso picasso;
    private Intent intent;

    public VideoLqAdapter( Context context, ArrayList<VideoLq> videos) {
        this.context = context;
        this.videos = videos;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_video_lq,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final VideoLq video = videos.get(position);

        Picasso.with(context).load(video.getUrlThumbnail()).into(holder.imgVideoLq);
        holder.tvTitleVideoLq.setText(video.getTitle());

        holder.llVideoLq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(v.getContext(),ScreenActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", videos.get(position).getId());
                bundle.putString("title", videos.get(position).getTitle());
                bundle.putString("link", videos.get(position).getUrlVideo());
                bundle.putString("tournaments",videos.get(position).getTournaments());
                intent.putExtras(bundle);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitleVideoLq;
        private ImageView imgVideoLq;
        private LinearLayout llVideoLq;

        public ViewHolder(View itemView) {
            super(itemView);
            imgVideoLq = (ImageView) itemView.findViewById(R.id.img_video_lq);
            tvTitleVideoLq = (TextView) itemView.findViewById(R.id.tv_title_video_lq);
            llVideoLq = (LinearLayout) itemView.findViewById(R.id.ll_video_lq);
        }
    }
}
