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
import com.two.football.model.Club;
import com.two.football.model.Video;

import java.util.List;

/**
 * Created by TWO on 10/27/2017.
 */

public class ClubRecycleAdapter extends RecyclerView.Adapter<ClubRecycleAdapter.ViewHolder> {
    private List<Club> list;
    private Context context;

    public ClubRecycleAdapter(Context context, List<Club> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ClubRecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_club, parent, false);
        ClubRecycleAdapter.ViewHolder holder = new ClubRecycleAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ClubRecycleAdapter.ViewHolder holder, int position) {
        Club club = list.get(position);

       /* Picasso.with(context).load(club.getThumbnail()).into(holder.imageView);
        holder.textView.setText(club.getTitle());*/

        holder.imageView.setImageResource(R.drawable.ic_chel);
        holder.textView.setText("Chelsea");
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public ViewHolder(View v) {
            super(v);
            textView = (TextView) v.findViewById(R.id.tv_name_club);
            imageView = (ImageView) v.findViewById(R.id.img_logo_club);
        }
    }
}
