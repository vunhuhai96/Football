package com.two.football.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.two.football.R;
import com.two.football.model.Match;

import java.util.ArrayList;

/**
 * Created by TWO on 10/23/2017.
 */

public class MatchAdapter extends PagerAdapter {
    private ArrayList<Match> matches;
    private LayoutInflater inflater;

    public MatchAdapter(Context context, ArrayList<Match> matches){
        this.matches = matches;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return matches.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = inflater.inflate(R.layout.item_viewpager, container, false);


        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return object.equals(view);
    }
}
