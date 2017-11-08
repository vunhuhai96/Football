package com.two.football.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.two.football.model.Video;
import com.two.football.view.activity.MainActivity;

import java.util.List;

/**
 * Created by TWO on 10/27/2017.
 */

public class VideoPlusAdapter extends BaseAdapter {
    private List<Highlight> list;
    private LayoutInflater inflater;
    private Context context;
    private DatabaseReference reference;

    public VideoPlusAdapter(Context context, List<Highlight> list) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
        reference = FirebaseDatabase.getInstance().getReference();
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
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title_highlight);
            holder.imgStar = (ImageView) convertView.findViewById(R.id.btn_star);

            DisplayMetrics metrics = context.getResources().getDisplayMetrics();
            int width = metrics.widthPixels;

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, width/2);
            holder.imageView.setLayoutParams(layoutParams);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Highlight highlight = list.get(position);

        Picasso.with(context).load(highlight.getUrlThumbnail()).into(holder.imageView);
        holder.tvTitle.setText(highlight.getTitle());

        holder.imgStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return convertView;
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Đăng nhập !");
        builder.setMessage("Bạn có muốn đăng nhập không ?");
        builder.setCancelable(false);

        builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    private class ViewHolder{
        ImageView imageView;
        TextView tvTitle;
        ImageView imgStar;
    }

}
