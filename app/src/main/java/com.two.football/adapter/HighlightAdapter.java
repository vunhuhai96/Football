package com.two.football.adapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.two.football.model.Highlight;
import com.two.football.R;

import java.util.List;



public class HighlightAdapter extends BaseAdapter {
    private List<Highlight> list;
    private LayoutInflater inflater;
    private Context context;
    private boolean check = false;

    public HighlightAdapter(Context context, List<Highlight> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Highlight getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView==null){
            convertView = inflater.inflate(R.layout.item_higlight, parent, false);
            holder = new ViewHolder();

            holder.imageView = (ImageView) convertView.findViewById(R.id.img_highlight);
            holder.imgStar = (ImageView) convertView.findViewById(R.id.btn_star);
            holder.imgShare = (ImageView) convertView.findViewById(R.id.btn_share);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title_highlight);

            DisplayMetrics metrics = context.getResources().getDisplayMetrics();
            int width = metrics.widthPixels;

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, width/2);
            holder.imageView.setLayoutParams(layoutParams);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Highlight highlight = list.get(position);

        Picasso.with(context).load(highlight.getUrlThumbnail()).into(holder.imageView);
        holder.tvTitle.setText(highlight.getTitle());

        return convertView;
    }


    private class ViewHolder{
        ImageView imageView;
        ImageView imgStar;
        ImageView imgShare;
        TextView tvTitle;
    }
}
