package com.two.football.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.two.football.R;
import com.two.football.model.Club;
import com.two.football.model.Video;
import com.two.football.view.activity.InfoClubActivity;

import java.util.List;

/**
 * Created by TWO on 10/27/2017.
 */

public class ClubRecycleAdapter extends RecyclerView.Adapter<ClubRecycleAdapter.ViewHolder> {
    private List<Club> list;
    private Context context;
    private Intent intent;

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
        final Club club = list.get(position);

        Picasso.with(context).load(club.getClubLogo()).into(holder.imageView);
        holder.textView.setText(club.getClubName());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, InfoClubActivity.class);
                intent.putExtra("ID_CLUB", club.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        LinearLayout layout;

        public ViewHolder(View v) {
            super(v);
            textView = (TextView) v.findViewById(R.id.tv_name_club);
            imageView = (ImageView) v.findViewById(R.id.img_logo_club);
            layout = (LinearLayout) v.findViewById(R.id.linear_club);
        }
    }
}
