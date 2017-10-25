package com.two.football.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.two.football.Item.Live;
import com.two.football.R;
import com.two.football.adapter.LiveAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TWO on 10/23/2017.
 */

public class FragmentLive extends Fragment {
    private View view;
    private ListView listView;
    private LiveAdapter adapter;
    private List<Live> list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.live_layout, container, false);
        initView();
        initLive();
        return view;
    }

    private void initLive() {
        list = new ArrayList<>();
        for (int i=0;i<15;i++){
            list.add(new Live());
        }

        adapter = new LiveAdapter(getContext(), list);
        listView.setAdapter(adapter);
    }

    private void initView() {
        listView = (ListView) view.findViewById(R.id.lv_live);
    }
}
