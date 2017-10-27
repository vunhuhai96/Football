//package com.two.football.adapter;
//
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import com.nccsoft.ntt.football.R;
//import com.nccsoft.ntt.football.model.Bxh;
//
//import java.util.ArrayList;
//
//public class BxhAdapter extends RecyclerView.Adapter<BxhAdapter.Viewholder>{
//    private Context context;
//    private ArrayList<Bxh> bxhs;
//    private LayoutInflater inflater;
//
//    public BxhAdapter(Context context, ArrayList<Bxh> bxhs) {
//        this.context = context;
//        this.bxhs = bxhs;
//        inflater = LayoutInflater.from(context);
//    }
//
//    public BxhAdapter() {
//    }
//
//    @Override
//    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View itemView = inflater.inflate(R.layout.item_bxh,parent,false);
//        return new Viewholder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(Viewholder holder, int position) {
//        Bxh bxh = bxhs.get(position);
//
//        holder.tvStt.setText(bxh.getStt());
//        holder.tvName.setText(bxh.getName());
//        holder.tvSoTran.setText(bxh.getSotran());
//        holder.tvHieuSo.setText(bxh.getHieuSo());
//        holder.tvSoDiem.setText(bxh.getSodiem());
//    }
//
//    @Override
//    public int getItemCount() {
//        return bxhs.size();
//    }
//
//    class Viewholder extends RecyclerView.ViewHolder {
//        TextView tvStt;
//        TextView tvName;
//        TextView tvSoTran;
//        TextView tvHieuSo;
//        TextView tvSoDiem;
//
//        public Viewholder(View itemView) {
//            super(itemView);
//
//            tvStt = itemView.findViewById(R.id.tv_stt);
//            tvName = itemView.findViewById(R.id.tv_name);
//            tvHieuSo = itemView.findViewById(R.id.tv_hieu_so);
//            tvSoTran = itemView.findViewById(R.id.tv_so_tran);
//            tvSoDiem = itemView.findViewById(R.id.tv_do_diem);
//        }
//    }
//}
