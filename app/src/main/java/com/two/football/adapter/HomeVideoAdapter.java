package com.two.football.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.two.football.R;
import com.two.football.model.Video;

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
    public void onBindViewHolder(ViewHolder holder, int position) {
        Video video = list.get(position);

        Picasso.with(context).load(video.getThumbnail()).into(holder.imageView);
        holder.textView.setText(video.getTitle().substring(7));
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        public ViewHolder(View v) {
            super(v);
            textView = (TextView) v.findViewById(R.id.tv_home_title_video);
            imageView = (ImageView) v.findViewById(R.id.img_home_video);
        }
    }
}
