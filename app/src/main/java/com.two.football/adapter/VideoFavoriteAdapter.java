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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.two.football.R;
import com.two.football.model.Highlight;
import com.two.football.model.VideoFavorite;
import com.two.football.view.activity.MainActivity;

import java.util.List;

/**
 * Created by TWO on 10/27/2017.
 */

public class VideoFavoriteAdapter extends BaseAdapter {
    private List<Highlight> list;
    private LayoutInflater inflater;
    private Context context;
    private DatabaseReference reference;

    public VideoFavoriteAdapter(Context context, List<Highlight> list) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
        reference = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public int getCount() {
        if (list.size()==0){
            return 0;
        }
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_higlight, parent, false);
            holder = new ViewHolder();

            holder.imageView = (ImageView) convertView.findViewById(R.id.img_highlight);
            holder.imgStar = (ImageView) convertView.findViewById(R.id.btn_star);
            holder.imgShare = (ImageView) convertView.findViewById(R.id.btn_share);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title_highlight);

            DisplayMetrics metrics = context.getResources().getDisplayMetrics();
            int width = metrics.widthPixels;

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, width / 2);
            holder.imageView.setLayoutParams(layoutParams);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Highlight highlight = list.get(position);

        Picasso.with(context).load(highlight.getUrlThumbnail()).into(holder.imageView);
        holder.tvTitle.setText(highlight.getTitle());

        holder.imgStar.setImageResource(R.drawable.ic_star_2);

        holder.imgStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idUser = MainActivity.ID;
                reference.child("User").child(idUser).child("Video").child(highlight.getKey()).removeValue();
                Toast.makeText(context,"Đã xóa video khỏi mục yêu thích", Toast.LENGTH_SHORT).show();
                list.remove(highlight);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    private class ViewHolder {
        ImageView imageView;
        ImageView imgStar;
        ImageView imgShare;
        TextView tvTitle;
    }
}
