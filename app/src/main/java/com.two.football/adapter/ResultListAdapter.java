package com.two.football.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.two.football.R;
import com.two.football.model.LTD;
import java.util.ArrayList;

/**
 * Created by Admin on 5/11/2017.
 */

public class ResultListAdapter extends RecyclerView.Adapter<ResultListAdapter.ViewHolder>{
    private ArrayList<LTD> arrResult;
    private Context context;
    private LayoutInflater inflater;

    public ResultListAdapter(Context context, ArrayList<LTD> arrResult) {
        this.context = context;
        this.arrResult = arrResult;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_result, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LTD result = arrResult.get(position);
        holder.tvHome.setText(result.getHomeClub());
        holder.tvGuest.setText(result.getGuestClub());
        holder.tvResult.setText(result.getResult());

        Picasso.with(context).load(result.getLogoHome()).into(holder.imgHome);
        Picasso.with(context).load(result.getLogoGuest()).into(holder.imgGuest);
    }

    @Override
    public int getItemCount() {
        return arrResult.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvHome;
        private TextView tvGuest;
        private ImageView imgHome;
        private ImageView imgGuest;
        private TextView tvResult;

        public ViewHolder(View itemView) {
            super(itemView);

            tvHome = (TextView) itemView.findViewById(R.id.tv_home);
            tvGuest = (TextView) itemView.findViewById(R.id.tv_guest);
            tvResult = (TextView) itemView.findViewById(R.id.tv_result);
            imgHome = (ImageView) itemView.findViewById(R.id.img_home);
            imgGuest = (ImageView) itemView.findViewById(R.id.img_guest);
        }
    }
}
