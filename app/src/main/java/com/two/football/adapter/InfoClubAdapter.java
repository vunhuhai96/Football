package com.two.football.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.two.football.view.fragment.FragmentClubInfo;
import com.two.football.view.fragment.FragmentHighlight;
import com.two.football.view.fragment.FragmentLive;

/**
 * Created by TWO on 10/23/2017.
 */

public class InfoClubAdapter extends FragmentStatePagerAdapter {

    public InfoClubAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag=null;
        switch (position){
            case 0:
                frag = new FragmentClubInfo();
                break;
            case 1:
                frag = new FragmentLive();
                break;

            case 2:
                frag = new FragmentHighlight();
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "Thông tin";
                break;
            case 1:
                title = "Lịch thi đấu";
                break;
            case 2:
                title = "Video liên quan";
                break;

        }
        return title;
    }

}
