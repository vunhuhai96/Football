package com.two.football.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.two.football.model.Club;
import com.two.football.R;

import java.util.List;

/**
 * Created by TWO on 10/24/2017.
 */

public class ClubAdapter extends BaseAdapter {
    private Context context;
    private List<Club> list;
    private LayoutInflater inflater;

    public ClubAdapter(Context context, List<Club> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Club getItem(int position) {
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
            convertView = inflater.inflate(R.layout.item_club_activity, parent, false);
            holder = new ViewHolder();

            holder.imageView = (ImageView) convertView.findViewById(R.id.img_logo_club_activity);
            holder.textView = (TextView) convertView.findViewById(R.id.tv_name_club_activity);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Club club = list.get(position);

        Picasso.with(context).load(club.getUrlLogo()).into(holder.imageView);
        holder.textView.setText(club.getName());


        return convertView;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
}
