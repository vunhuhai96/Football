package com.two.football.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.two.football.model.Match;
import com.two.football.R;
import com.two.football.view.activity.ClubActivity;
import com.two.football.adapter.MatchAdapter;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by TWO on 10/23/2017.
 */

public class FragmentHome extends Fragment implements View.OnClickListener {
    private View view;
    private ArrayList<Match> matches;
    private ViewPager viewPager;
    private CircleIndicator indicator;
    private ImageView imgVideo1, imgVideo2, imgVideo3, imgVideo4, imgVideo5;
    private ImageView btnLeft, btnRight;
    private int id;
    private Button btnViewClub;
    private MatchAdapter adapter;
    private Handler handler;
    private int delay = 3000;
    private int page = 0;

    Runnable runnable = new Runnable() {
        public void run() {
            if (adapter.getCount() == page) {
                page = 0;
            } else {
                page++;
            }
            viewPager.setCurrentItem(page, true);
            handler.postDelayed(this, delay);
        }
    };

    public FragmentHome() {
        handler = new Handler();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_layout, container, false);
        initView();
        initMatch();
        return view;

    }

    private void initMatch() {
        matches = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            matches.add(new Match());
        }
        adapter = new MatchAdapter(getContext(), matches);
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);
    }

    private void initView() {
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        indicator = (CircleIndicator) view.findViewById(R.id.idicator);


        int img[] = new int[]{R.id.video_1, R.id.video_2, R.id.video_3, R.id.video_4, R.id.video_5};

        String a[] = new String[]{"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcThe-G0rm3kCgKN9c7bnyEiFZGNS5Mn-SNkhQMV3qhuwofggw6o",
                "http://www.extralucha.com/wwe-fotos-images-smackdown-raw/2014/03/Real-Madrid-vs-Barcelona-Clasico-de-Espa%C3%B1a.jpg",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSDFFjMYuipDW368ltyCBTkC4faK221MvXMuy1keGh6xZMqcucW",
                "http://www.biztek.vn/images/Inter%20Milan%20vs%20AC%20Milan.jpg",
                "http://www.biztek.vn/images/Inter%20Milan%20vs%20AC%20Milan.jpg"};

        btnLeft = (ImageView) view.findViewById(R.id.btn_left);
        btnRight = (ImageView) view.findViewById(R.id.btn_right);

        btnViewClub = (Button) view.findViewById(R.id.btn_view_club);

        btnLeft.setOnClickListener(this);
        btnRight.setOnClickListener(this);
        btnViewClub.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_left:
                id = viewPager.getCurrentItem();
                if (id==0){
                    id = 2;
                } else {
                    id--;
                }
                viewPager.setCurrentItem(id);
                break;
            case R.id.btn_right:
                id = viewPager.getCurrentItem();
                if (id==3){
                    id = 0;
                } else {
                    id++;
                }
                viewPager.setCurrentItem(id);
                break;

            case R.id.btn_view_club:
                Intent intent = new Intent(getContext(), ClubActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable, delay);
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }
}
