package com.two.football.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.two.football.R;


public class FragmentTT extends Fragment implements View.OnClickListener {
    private TextView tvDetail;
    private Bundle getBundle = null;
    private String title, title2;
    private Intent intent;

    public FragmentTT(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_video,container,false);

        tvDetail = (TextView) view.findViewById(R.id.tv_detail);

        getBundle = getActivity().getIntent().getExtras();
        title = getBundle.getString("title");

        intent = getActivity().getIntent();
        title2 = intent.getStringExtra("title");

        if (title2 == null){
            tvDetail.setText(title);
        }else {
            tvDetail.setText(title2);
        }

        return view;
    }


    @Override
    public void onClick(View view) {

    }
}
