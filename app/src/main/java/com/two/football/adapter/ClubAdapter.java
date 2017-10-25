package com.two.football.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

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

        convertView = inflater.inflate(R.layout.item_club_activity, parent, false);

        return convertView;
    }
}
