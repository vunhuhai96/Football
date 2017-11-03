package com.two.football.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.two.football.R;
import com.two.football.model.LTD;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TWO on 11/3/2017.
 */

public class LTDAdapter extends BaseAdapter {
    private Context context;
    private List<LTD> list;
    private LayoutInflater inflater;

    public LTDAdapter(Context context, List<LTD> list) {
        this.context = context;
        this.list = list;
        inflater =LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null) {
            convertView = inflater.inflate(R.layout.item_ltd, parent, false);
            holder = new ViewHolder();

            holder.tvHome = (TextView) convertView.findViewById(R.id.tv_ltd_home_club);
            holder.tvGuest = (TextView) convertView.findViewById(R.id.tv_ltd_guest_club);
            holder.time = (TextView) convertView.findViewById(R.id.tv_ltd_time);
            holder.date = (TextView) convertView.findViewById(R.id.tv_ltd_date);
            holder.imgHome = (ImageView) convertView.findViewById(R.id.img_ltd_home);
            holder.imgGuest = (ImageView) convertView.findViewById(R.id.img_ltd_guest);

            convertView.setTag(holder);
        } else {
            holder =(ViewHolder) convertView.getTag();
        }

        LTD ltd = list.get(position);

        holder.tvHome.setText(ltd.getHomeClub());
        holder.tvGuest.setText(ltd.getGuestClub());
        holder.time.setText(ltd.getTime());
        holder.date.setText(ltd.getDate());

        Picasso.with(context).load(ltd.getLogoHome()).into(holder.imgHome);
        Picasso.with(context).load(ltd.getLogoGuest()).into(holder.imgGuest);

        return convertView;
    }

    private class ViewHolder{
        TextView tvHome;
        TextView tvGuest;
        TextView time;
        TextView date;
        ImageView imgHome;
        ImageView imgGuest;
    }
}
