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
import com.two.football.model.Video;

import java.util.List;

/**
 * Created by TWO on 10/27/2017.
 */

public class VideoPlusAdapter extends BaseAdapter {
    private List<Video> list;
    private LayoutInflater inflater;
    private Context context;

    public VideoPlusAdapter(Context context, List<Video> list) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Video getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView==null){
            convertView = inflater.inflate(R.layout.item_higlight, parent, false);
            holder = new ViewHolder();

            holder.imageView = (ImageView) convertView.findViewById(R.id.img_highlight);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title_highlight);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Video video = list.get(position);

        Picasso.with(context).load(video.getUrlThumbnail()).into(holder.imageView);
        holder.tvTitle.setText(video.getTitle());

        return convertView;
    }


    private class ViewHolder{
        ImageView imageView;
        TextView tvTitle;
    }
}
