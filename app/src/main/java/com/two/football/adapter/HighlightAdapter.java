package com.two.football.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.two.football.Item.Highlight;
import com.two.football.R;

import java.util.List;

/**
 * Created by TWO on 10/23/2017.
 */

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

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Highlight highlight = list.get(position);

        Picasso.with(context).load(highlight.getLink()).into(holder.imageView);

        final ViewHolder finalHolder = holder;

        holder.imgStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check) {
                    finalHolder.imgStar.setImageResource(R.drawable.ic_star_2);
                    Toast.makeText(context, "Đã lưu vào mục yêu thích", Toast.LENGTH_SHORT).show();
                } else {
                    finalHolder.imgStar.setImageResource(R.drawable.ic_star_1);
                }

                check = !check;
            }
        });

        return convertView;
    }


    private class ViewHolder{
        ImageView imageView;
        ImageView imgStar;
        ImageView imgShare;
    }

}
