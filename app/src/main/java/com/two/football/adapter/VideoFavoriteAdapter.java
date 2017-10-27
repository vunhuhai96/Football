package com.two.football.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.two.football.R;
import com.two.football.model.VideoFavorite;

import java.util.List;

/**
 * Created by TWO on 10/27/2017.
 */

public class VideoFavoriteAdapter extends BaseAdapter {
    private List<VideoFavorite> list;
    private LayoutInflater inflater;
    private Context context;

    public VideoFavoriteAdapter(Context context, List<VideoFavorite> list) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public VideoFavorite getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = inflater.inflate(R.layout.item_higlight, parent, false);

        return convertView;
    }
}
