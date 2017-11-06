package com.two.football.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.two.football.R;
import com.two.football.model.LTD;

import java.util.ArrayList;

/**
 * Created by Admin on 6/11/2017.
 */

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder>{
    private Context context;
    private ArrayList<LTD> arrayList;
    private LayoutInflater inflater;

    public ResultAdapter(Context context, ArrayList<LTD> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.activity_result_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LTD ltd = arrayList.get(position);

        holder.tvVong.setText(ltd.getRound());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvVong;
        private RecyclerView rcvList;
        public ViewHolder(View itemView) {
            super(itemView);

            tvVong = (TextView) itemView.findViewById(R.id.tv_vong);
            rcvList = (RecyclerView) itemView.findViewById(R.id.rcv_result);
        }
    }
}
