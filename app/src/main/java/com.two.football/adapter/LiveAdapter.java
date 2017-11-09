package com.two.football.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.two.football.model.Live;
import com.two.football.R;

import java.util.List;

/**
 * Created by TWO on 10/23/2017.
 */

public class LiveAdapter extends BaseAdapter {
    private List<Live> list;
    private Context context;
    private LayoutInflater inflater;

    public LiveAdapter(Context context, List<Live> list) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Live getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.item_live, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.guestTeam = (TextView) convertView.findViewById(R.id.guestTeam);
            viewHolder.homeTeam = (TextView) convertView.findViewById(R.id.homeTeam);
            viewHolder.videoType = (TextView) convertView.findViewById(R.id.videoType);
            viewHolder.guestImg = (ImageView) convertView.findViewById(R.id.imgGuest);
            viewHolder.homeImg = (ImageView) convertView.findViewById(R.id.imgHome);

            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Live live = list.get(position);

        viewHolder.guestTeam.setText(live.getGuestClub());
        viewHolder.homeTeam.setText(live.getHomeClub());
        viewHolder.videoType.setText(live.getVideoType());
        Picasso.with(context).load(live.getGuestImg()).into(viewHolder.guestImg);
        Picasso.with(context).load(live.getHomeImg()).into(viewHolder.homeImg);

        return convertView;
    }

    private class ViewHolder{
        TextView guestTeam;
        TextView homeTeam;
        TextView videoType;
        ImageView guestImg;
        ImageView homeImg;
    }
}
