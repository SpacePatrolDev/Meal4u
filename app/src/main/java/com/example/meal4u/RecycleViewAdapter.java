package com.example.meal4u;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.rViewHolder> {

    public static class rViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cv;
        TextView tvVendorName;
        TextView tvVendorRating;
        TextView tvVendorTitle;
        TextView tvVendorWD;
        TextView tvVendorPR;
        ImageView ivVendorDP;
        Context context;
        ArrayList<Vendor> vendorData;

        public rViewHolder(View v, Context c, ArrayList<Vendor> al){
            super(v);
            cv = (CardView) v.findViewById(R.id.rv_dashboard);
            tvVendorName = (TextView) v.findViewById(R.id.cv_tv_dashboard_vn);
            tvVendorRating = (TextView) v.findViewById(R.id.cv_tv_dashboard_vr);
            tvVendorTitle = (TextView) v.findViewById(R.id.cv_tv_dashboard_vt);
            tvVendorWD = (TextView) v.findViewById(R.id.cv_tv_dashboard_wd);
            tvVendorPR = (TextView) v.findViewById(R.id.cv_tv_dashboard_pr);
            ivVendorDP = (ImageView) v.findViewById(R.id.cv_iv_dashboard_vi);
            this.context = c;
            this.vendorData = al;

            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, VendorDetails.class);
            intent.putExtra("Vendor_Img",vendorData.get(getAdapterPosition()).VendorDP);
            intent.putExtra("Vendor_Name",vendorData.get(getAdapterPosition()).VendorName);
            intent.putExtra("Vendor_Rating",vendorData.get(getAdapterPosition()).VendorRating);

            context.startActivity(intent);
        }
    }

    private ArrayList<Vendor> vendorData;
    private Context context;

    public RecycleViewAdapter(ArrayList<Vendor> vendors, Context context){
        this.vendorData = vendors;
        this.context = context;
    }

    @NonNull
    @Override
    public rViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_cards, parent,false);
        return new rViewHolder(view, context, vendorData);
    }

    @Override
    public void onBindViewHolder(@NonNull final rViewHolder holder, final int position) {
        TextView tvVendorName = holder.tvVendorName;
        TextView tvVendorRating = holder.tvVendorRating;
        TextView tvVendorTitle = holder.tvVendorTitle;
        TextView tvVendorWD = holder.tvVendorWD;
        TextView tvVendorPR = holder.tvVendorPR;
        ImageView ivVendorDP = holder.ivVendorDP;

        tvVendorName.setText(vendorData.get(position).VendorName);
        tvVendorRating.setText(vendorData.get(position).VendorRating);
        tvVendorTitle.setText(vendorData.get(position).VendorTitle);
        tvVendorWD.setText(vendorData.get(position).VendorWD);
        tvVendorPR.setText(vendorData.get(position).VendorPR);
        ivVendorDP.setImageResource(vendorData.get(position).VendorDP);
    }

    @Override
    public int getItemCount() {
        return vendorData == null ? 0 : vendorData.size();
    }
}
