package com.two.football.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
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

        ViewHolder viewHolder;

        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_rankings, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.stt = (TextView) convertView.findViewById(R.id.stt);
            viewHolder.teamName = (TextView) convertView.findViewById(R.id.teamName);
            viewHolder.battle = (TextView) convertView.findViewById(R.id.battle);
            viewHolder.win = (TextView) convertView.findViewById(R.id.win);
            viewHolder.draw = (TextView) convertView.findViewById(R.id.draw);
            viewHolder.lose = (TextView) convertView.findViewById(R.id.lose);
            viewHolder.offset = (TextView) convertView.findViewById(R.id.offset);
            viewHolder.totalpoint = (TextView) convertView.findViewById(R.id.totalpoint);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
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


        return convertView;
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
    }
}
