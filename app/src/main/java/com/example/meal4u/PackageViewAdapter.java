package com.example.meal4u;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PackageViewAdapter extends RecyclerView.Adapter<PackageViewAdapter.PackageViewHolder> {

    public static class PackageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cardView;
        TextView tvPackageName;
        TextView tvPackageCost;
        ListView listView;
        Context context;
        List<VendorPackage> Packages;
        List<String> Keys;
        String key;

        public PackageViewHolder(View itemView, Context context, List<VendorPackage> vendorPackages, List<String> keys) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cv_packages);
            tvPackageName = (TextView) itemView.findViewById(R.id.cv_tv_packageName);
            tvPackageCost = (TextView) itemView.findViewById(R.id.cv_tv_packageCost);
            listView = (ListView) itemView.findViewById(R.id.lv_package_days);
            Packages = vendorPackages;
            Keys = keys;
            this.context = context;

            itemView.setOnClickListener(this);
        }

        @SuppressLint("SetTextI18n")
        public void bind(VendorPackage vendorPackage, String key){
            tvPackageName.setText(vendorPackage.getPackageName());
            tvPackageCost.setText(vendorPackage.getPackageCost());
            Map menu = vendorPackage.getPackageMenu();
            ListAdapter listAdapter = new ListAdapter(menu);
            listView.setAdapter(listAdapter);
            this.key = key;
        }

        @Override
        public void onClick(View view) {
//            Intent intent = new Intent(context, OrderDetails.class);
//            intent.putExtra("Package_Key", key);
//            context.startActivity(intent);
        }
    }

    private List<VendorPackage> Packages;
    private List<String> Keys;
    private Context context;

    public PackageViewAdapter(List<VendorPackage> packages, List<String> keys, Context context) {
        Packages = packages;
        Keys = keys;
        this.context = context;
    }

    @NonNull
    @Override
    public PackageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_packages, parent,false);
        return new PackageViewAdapter.PackageViewHolder(view, context, Packages, Keys);
    }


    @Override
    public void onBindViewHolder(PackageViewHolder holder, int position) {
        holder.bind(Packages.get(position), Keys.get(position));
    }

    @Override
    public int getItemCount() {
        return Packages == null ? 0 : Packages.size();
    }
}
