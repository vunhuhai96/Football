package com.two.football.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.two.football.R;
import com.two.football.model.ClubLTD;

import java.util.List;

/**
 * Created by TWO on 11/1/2017.
 */

public class ClubLTDAdapter extends BaseAdapter {

    private Context context;
    private List<ClubLTD> list;
    private LayoutInflater inflater;

    public ClubLTDAdapter(Context context, List<ClubLTD> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public ClubLTD getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_fragment_club_ltd, parent, false);
            holder = new ViewHolder();

            holder.imgHome = (ImageView) convertView.findViewById(R.id.img_home_ltd_club);
            holder.imgGuest = (ImageView) convertView.findViewById(R.id.img_guest_ltd_club);
            holder.tvHome = (TextView) convertView.findViewById(R.id.tv_home_ltd_club_fragment);
            holder.tvGuest = (TextView) convertView.findViewById(R.id.tv_guest_ltd_club_fragment);
            holder.tvGiaiDau = (TextView) convertView.findViewById(R.id.tv_giai_dau);
            holder.tvVong = (TextView) convertView.findViewById(R.id.tv_ltd_club_vong);
            holder.tvTime = (TextView) convertView.findViewById(R.id.tv_ltd_club_time);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ClubLTD ltd = list.get(position);

        Picasso.with(context).load(ltd.getLogoHome()).into(holder.imgHome);
        Picasso.with(context).load(ltd.getLogoGuest()).into(holder.imgGuest);

        holder.tvHome.setText(ltd.getHomeClub());
        holder.tvGuest.setText(ltd.getGuestClub());
        holder.tvGiaiDau.setText(ltd.getTournament());
        holder.tvVong.setText(ltd.getRound());
        holder.tvTime.setText(ltd.getTime());

        return convertView;
    }

    private class ViewHolder {
        ImageView imgHome, imgGuest;
        TextView tvHome, tvGuest, tvGiaiDau, tvVong, tvTime;
    }
}
