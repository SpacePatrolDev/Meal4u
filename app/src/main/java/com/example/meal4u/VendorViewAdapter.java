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

import java.util.List;

public class VendorViewAdapter extends RecyclerView.Adapter<VendorViewAdapter.rViewHolder> {

    public static class rViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cv;
        TextView tvVendorName;
        TextView tvVendorRating;
        TextView tvVendorTitle;
        TextView tvVendorWD;
        TextView tvVendorPR;
        ImageView ivVendorDP;
        Context context;
        List<Vendor> vendorData;
        List<String> vendorKeys;
        String key;

        public rViewHolder(View v, Context c, List<Vendor> al, List<String> k){
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
            this.vendorKeys = k;

            v.setOnClickListener(this);
        }

        public void bind(Vendor vendor, String key){
            tvVendorName.setText(vendor.getVendorName());
            tvVendorRating.setText(vendor.getVendorRating());
            tvVendorTitle.setText(vendor.getVendorTitle());
            tvVendorWD.setText(vendor.getVendorWD());
            tvVendorPR.setText(vendor.getVendorPR());
            ivVendorDP.setImageResource(context.getResources().getIdentifier("drawable/"+vendor.getVendorDP(), null, context.getPackageName()));

            this.key = key;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, VendorDetails.class);
            intent.putExtra("Vendor_Key", key);
            context.startActivity(intent);
        }
    }

    private List<Vendor> vendorData;
    private List<String> vendorKey;
    private Context context;

    public VendorViewAdapter(List<Vendor> vendors, List<String> keys, Context context){
        this.vendorData = vendors;
        this.vendorKey = keys;
        this.context = context;
    }

    @NonNull
    @Override
    public rViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_cards, parent,false);
        return new rViewHolder(view, context, vendorData, vendorKey);
    }

    @Override
    public void onBindViewHolder(@NonNull final rViewHolder holder, final int position) {
        holder.bind(vendorData.get(position), vendorKey.get(position));
    }

    @Override
    public int getItemCount() {
        return vendorData == null ? 0 : vendorData.size();
    }
}
