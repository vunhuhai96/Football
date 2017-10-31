package com.two.football.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.two.football.R;
import com.two.football.model.BLuan;

import java.util.ArrayList;

public class BlAdapter extends RecyclerView.Adapter<BlAdapter.ViewHolder> implements View.OnClickListener{
    private Context context;
    private ArrayList<BLuan> bLuans;
    private LayoutInflater inflater;
    private boolean ischecker = false;

    public BlAdapter(Context context, ArrayList<BLuan> bLuans) {
        this.context = context;
        this.bLuans = bLuans;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_bl,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BLuan bLuan = bLuans.get(position);

        holder.imgAvater.setImageResource(bLuan.getIcon());
        holder.tvName1.setText(bLuan.getName1());
        holder.tvContent.setText(bLuan.getContent());

        holder.imgLike.setOnClickListener(this);
        holder.imgHeath.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return bLuans.size();
    }

    @Override
    public void onClick(View view) {
        ViewHolder holder = null;
        switch (view.getId()){
            case R.id.img_like:
                if (ischecker)
                    holder.imgLike.setImageResource(R.drawable.like);
                else if (ischecker = true){
                    holder.imgLike.setImageResource(R.drawable.like2);
                }

                break;
            case R.id.img_heath:

                break;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgAvater;
        private TextView tvName1;
        private TextView tvContent;
        private ImageView imgLike;
        private ImageView imgHeath;

        public ViewHolder(View itemView) {
            super(itemView);

            imgAvater = (ImageView) itemView.findViewById(R.id.img_avatar);
            tvName1 = (TextView) itemView.findViewById(R.id.tv_name1);
            tvContent = (TextView) itemView.findViewById(R.id.tv_content);
            imgLike = (ImageView) itemView.findViewById(R.id.img_like);
            imgHeath = (ImageView) itemView.findViewById(R.id.img_heath);
        }
    }
}
