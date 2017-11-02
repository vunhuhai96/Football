package com.two.football.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.two.football.R;
import com.two.football.model.Rankings;

import java.util.ArrayList;

/**
 * Created by ADMIN on 10/26/2017.
 */

public class RankingsAdapter extends BaseAdapter {

    private ArrayList<Rankings> ralist;
    private LayoutInflater inflater;
    private Context context;

    public RankingsAdapter(ArrayList<Rankings> ralist, Context context) {
        this.ralist = ralist;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return ralist.size();
    }

    @Override
    public Object getItem(int position) {
        return ralist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder view;

        if(convertView == null){
            convertView = inflater.inflate(R.layout.activity_list_rankings, parent, false);

            view = new ViewHolder();

            view.table = (TextView) convertView.findViewById(R.id.table);
            view.tBatle = (TextView) convertView.findViewById(R.id.titleBattle);
            view.tWin = (TextView) convertView.findViewById(R.id.titleWin);
            view.tDraw = (TextView) convertView.findViewById(R.id.titleDraw);
            view.tLose = (TextView) convertView.findViewById(R.id.titleLose);
            view.tOffset = (TextView) convertView.findViewById(R.id.titleOff);
            view.tTotal = (TextView) convertView.findViewById(R.id.titleTotal);
            view.listView = (ListView) convertView.findViewById(R.id.listRa);

            convertView.setTag(view);
        }else{
            view = (ViewHolder) convertView.getTag();
        }

        Rankings rankings = ralist.get(position);

        view.table.setText(rankings.getTable());

        ArrayList<Rankings> list = new ArrayList<>();

        list.add(new Rankings("Chelsea", "28", "7", "13","5", "23/55", "31"));
        list.add(new Rankings("Chelsea", "27", "6", "13","5", "23/55", "31"));
        list.add(new Rankings("Chelsea", "26", "6", "13","5", "23/55", "31"));
        list.add(new Rankings("Chelsea", "21", "6", "13","5", "23/55", "31"));

        ListRankingsAdapter adapterRe = new ListRankingsAdapter(list, context.getApplicationContext());
        view.listView.setAdapter(adapterRe);


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
        ListView listView;
    }
}
