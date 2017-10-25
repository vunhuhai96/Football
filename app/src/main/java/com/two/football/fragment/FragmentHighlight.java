package com.two.football.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.two.football.Item.Highlight;
import com.two.football.R;
import com.two.football.adapter.HighlightAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TWO on 10/23/2017.
 */

public class FragmentHighlight extends Fragment {
    private View view;
    private List<Highlight> list;
    private ListView listView;
    private HighlightAdapter adapter;
    private ImageView imgStar;

    public FragmentHighlight() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.highlight_layout, container, false);
        initView();
        initHiglight();
        return view;
    }

    private void initHiglight() {
        list = new ArrayList<>();

        list.add(new Highlight("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcThe-G0rm3kCgKN9c7bnyEiFZGNS5Mn-SNkhQMV3qhuwofggw6o"));
        list.add(new Highlight("http://www.extralucha.com/wwe-fotos-images-smackdown-raw/2014/03/Real-Madrid-vs-Barcelona-Clasico-de-Espa%C3%B1a.jpg"));
        list.add(new Highlight("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSDFFjMYuipDW368ltyCBTkC4faK221MvXMuy1keGh6xZMqcucW"));
        list.add(new Highlight("http://www.biztek.vn/images/Inter%20Milan%20vs%20AC%20Milan.jpg"));
        adapter = new HighlightAdapter(getContext(), list);
        listView.setAdapter(adapter);
    }

    private void initView() {
        listView = (ListView) view.findViewById(R.id.lv_highlight);
    }
}
