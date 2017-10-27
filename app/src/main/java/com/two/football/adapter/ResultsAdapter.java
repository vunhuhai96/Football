package com.two.football.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.two.football.R;
import com.two.football.model.Results;

import java.util.ArrayList;

/**
 * Created by ADMIN on 10/26/2017.
 */

public class ResultsAdapter extends BaseAdapter {

    private ArrayList<Results> relist;
    private LayoutInflater inflater;
    private Context context;

    public ResultsAdapter(ArrayList<Results> relist, Context context) {
        this.relist = relist;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return relist.size();
    }

    @Override
    public Object getItem(int position) {
        return relist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder view;

        if(convertView == null){
            convertView = inflater.inflate(R.layout.activity_list_results, parent, false);

            view = new ViewHolder();

            view.table = (TextView) convertView.findViewById(R.id.table);
            view.tBatle = (TextView) convertView.findViewById(R.id.titleBattle);
            view.tWin = (TextView) convertView.findViewById(R.id.titleWin);
            view.tDraw = (TextView) convertView.findViewById(R.id.titleDraw);
            view.tLose = (TextView) convertView.findViewById(R.id.titleLose);
            view.tOffset = (TextView) convertView.findViewById(R.id.titleOff);
            view.tTotal = (TextView) convertView.findViewById(R.id.titleTotal);

            convertView.setTag(view);
        }else{
            view = (ViewHolder) convertView.getTag();
        }

        convertView.setMinimumHeight(800);

        Results re = relist.get(position);

        view.table.setText(re.getTable());

        return convertView;
    }

    private class ViewHolder{
        TextView table;
        TextView tBatle;
        TextView tWin;
        TextView tDraw;
        TextView tLose;
        TextView tOffset;
        TextView tTotal;
    }
}
