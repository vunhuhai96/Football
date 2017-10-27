package com.two.football.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.two.football.R;
import com.two.football.model.Results;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ADMIN on 10/25/2017.
 */

public class ListResultsAdapter extends BaseAdapter {

    private ArrayList<Results> reslist;
    private LayoutInflater inflater;
    private Context context;

    public ListResultsAdapter(ArrayList<Results> reslist, Context context) {
        this.reslist = reslist;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return reslist.size();
    }

    @Override
    public Object getItem(int position) {
        return reslist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        ViewHolder viewHolder;

        if(view == null){
            view = inflater.inflate(R.layout.item_results, parent, false);

            viewHolder = new ViewHolder();
//            viewHolder.teamImg = (ImageView) view.findViewById(R.id.teamImg);
            viewHolder.teamName = (TextView) view.findViewById(R.id.teamName);
            viewHolder.battle = (TextView) view.findViewById(R.id.battle);
            viewHolder.win = (TextView) view.findViewById(R.id.win);
            viewHolder.draw = (TextView) view.findViewById(R.id.draw);
            viewHolder.lose = (TextView) view.findViewById(R.id.lose);
            viewHolder.offset = (TextView) view.findViewById(R.id.offset);
            viewHolder.totalpoint = (TextView) view.findViewById(R.id.totalpoint);

            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Results results = reslist.get(position);

        viewHolder.teamName.setText(results.getNameTeam());
        viewHolder.battle.setText(results.getBattle());
        viewHolder.win.setText(results.getWin());
        viewHolder.draw.setText(results.getDraw());
        viewHolder.lose.setText(results.getLose());
        viewHolder.offset.setText(results.getOffset());
        viewHolder.totalpoint.setText(results.getTotalPoints());

        return view;
    }

    private class ViewHolder{
        TextView battle;
        TextView teamName;
        TextView win;
        TextView draw;
        TextView lose;
        TextView offset;
        TextView totalpoint;
//        ImageView teamImg;
    }
}
