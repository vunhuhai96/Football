package com.two.football.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.two.football.R;
import com.two.football.model.Reserves;

import java.util.List;

/**
 * Created by ADMIN on 11/1/2017.
 */

public class ReservesAdapter extends BaseAdapter{
    private List<Reserves> list;
    private Context context;
    private int layout;

    public ReservesAdapter(List<Reserves> list, Context context, int layout) {
        this.list = list;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=layoutInflater.inflate(layout,null);


            viewHolder.link = (TextView) view.findViewById(R.id.link);

            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Reserves reserves = list.get(i);

        viewHolder.link.setText(reserves.getLink());
        return view;
    }

    private class ViewHolder{
        TextView link;
    }
}
