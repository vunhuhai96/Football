package com.two.football.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.two.football.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ADMIN on 10/26/2017.
 */

public class SpinnerHighlightAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<String> data;
    public Resources res;
    private LayoutInflater inflater;

    public SpinnerHighlightAdapter(Context context, List<String> objects) {
        super(context, R.layout.item_spinner_highlight, objects);

        this.context = context;
        data = objects;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    // This funtion called for each row ( Called data.size() times )
    public View getCustomView(int position, View convertView, ViewGroup parent) {

        View row = inflater.inflate(R.layout.item_spinner_highlight, parent, false);

        TextView text = (TextView) row.findViewById(R.id.tv_spinner_highlight);

        text.setText(data.get(position).toString());

        return row;
    }
}
