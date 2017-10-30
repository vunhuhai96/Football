package com.two.football.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.two.football.view.fragment.FragmentLinkTd;
import com.two.football.view.fragment.FragmentTT;
import com.two.football.view.fragment.FragmentVideoLq;

public class PageAdapter extends FragmentStatePagerAdapter {
    public PageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frg = null;
        switch (position){
            case 0:
                frg = new FragmentTT();
                break;
            case 1:
                frg = new FragmentVideoLq();
                break;
            case 2:
                frg = new FragmentLinkTd();
                break;
        }
        return frg;
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
                title="Bình luận";
                break;
            case 1:
                title="Video liên quan";
                break;
            case 2:
                title = "Link trận đấu";
                break;
        }
        return title;
    }

}
