package com.two.football.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.two.football.R;
import com.two.football.model.MatchHome;

import java.util.ArrayList;

/**
 * Created by TWO on 10/23/2017.
 */

public class MatchAdapter extends PagerAdapter {
    private ArrayList<MatchHome> matches;
    private LayoutInflater inflater;
    private Context context;

    public MatchAdapter(Context context, ArrayList<MatchHome> matches){
        this.matches = matches;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return matches.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = inflater.inflate(R.layout.item_viewpager, container, false);

        ImageView imgLogoHome = (ImageView) itemView.findViewById(R.id.img_home_logo_home_club);
        ImageView imgLogoGuest = (ImageView) itemView.findViewById(R.id.img_home_logo_guest_club);
        TextView tvHome = (TextView) itemView.findViewById(R.id.tv_home_name_home_club);
        TextView tvGuest = (TextView) itemView.findViewById(R.id.tv_home_name_guest_club);
        TextView tvTournament = (TextView) itemView.findViewById(R.id.tv_name_home_tournament);
        TextView tvTime = (TextView) itemView.findViewById(R.id.tv_name_home_time);

        MatchHome match = matches.get(position);

        Picasso.with(context).load(match.getLogoHome()).into(imgLogoHome);
        Picasso.with(context).load(match.getLogoGuest()).into(imgLogoGuest);

        tvHome.setText(match.getHomeClub());
        tvGuest.setText(match.getGuestClub());
        /*tvTournament.setText(match.getTournament());*/
        tvTournament.setText("Ngoại hạng anh");
        tvTime.setText(match.getTime());

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
