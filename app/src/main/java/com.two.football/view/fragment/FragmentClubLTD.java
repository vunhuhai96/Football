package com.two.football.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.two.football.R;
import com.two.football.adapter.ClubLTDAdapter;
import com.two.football.model.LTD;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TWO on 10/27/2017.
 */

public class FragmentClubLTD extends Fragment {
    private View view;
    private List<LTD> list;
    private ListView listView;
    private ClubLTDAdapter adapter;

    public FragmentClubLTD(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_club_ltd, container, false);
        initView();
        return view;
    }

    private void initView() {
        listView = (ListView) view.findViewById(R.id.lv_club_ltd);


        list = new ArrayList<>();
        for (int i= 0;i<10;i++){
            list.add(new LTD());
        }

        adapter = new ClubLTDAdapter(getContext(), list);
        listView.setAdapter(adapter);
    }
}
