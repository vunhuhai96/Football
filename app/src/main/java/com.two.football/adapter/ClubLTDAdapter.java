package com.two.football.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.two.football.R;
import com.two.football.model.LTD;
import com.two.football.model.Video;

import java.util.List;

/**
 * Created by TWO on 11/1/2017.
 */

public class ClubLTDAdapter extends BaseAdapter {

    private Context context;
    private List<LTD> list;
    private LayoutInflater inflater;

    public ClubLTDAdapter(Context context, List<LTD> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public LTD getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /*ViewHolder holder;
        if (convertView==null){*/
            convertView = inflater.inflate(R.layout.item_fragment_club_ltd, parent, false);
            /*holder = new ViewHolder();

            holder.imageView = (ImageView) convertView.findViewById(R.id.img_highlight);
            holder.textView = (TextView) convertView.findViewById(R.id.tv_title_highlight);

            convertView.setTag(holder);
        }
        */
        return convertView;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
}
