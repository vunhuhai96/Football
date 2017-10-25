package com.two.football.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.two.football.Item.Live;
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

        convertView = inflater.inflate(R.layout.item_live, parent, false);

        return convertView;
    }
}
