package com.two.football.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.two.football.R;
import com.two.football.model.Rankings;

import java.util.ArrayList;
/**
 * Created by ADMIN on 10/25/2017.
 */

public class ListRankingsAdapter extends BaseAdapter {

    private ArrayList<Rankings> ralist;
    private LayoutInflater inflater;
    private Context context;

    public ListRankingsAdapter(ArrayList<Rankings> ralist, Context context) {
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
    public View getView(int position, View view, ViewGroup parent) {

        ViewHolder viewHolder;

        if(view == null){
            view = inflater.inflate(R.layout.item_rankings, null, false);

            viewHolder = new ViewHolder();
//            viewHolder.teamImg = (ImageView) view.findViewById(R.id.teamImg);
            viewHolder.stt = (TextView) view.findViewById(R.id.stt);
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

        Rankings rankings = ralist.get(position);

        viewHolder.stt.setText(rankings.getStt());
        viewHolder.teamName.setText(rankings.getTeamName());
        viewHolder.battle.setText(rankings.getBattle());
        viewHolder.win.setText(rankings.getWin());
        viewHolder.draw.setText(rankings.getDraw());
        viewHolder.lose.setText(rankings.getLose());
        viewHolder.offset.setText(rankings.getOffset());
        viewHolder.totalpoint.setText(rankings.getTotalPoints());

        return view;
    }


    private class ViewHolder{
        TextView stt;
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
