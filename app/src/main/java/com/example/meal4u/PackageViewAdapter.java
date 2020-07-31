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
        TextView tvMenuMonday;
        TextView tvMenuTuesday;
        TextView tvMenuWednesday;
        TextView tvMenuThursday;
        TextView tvMenuFriday;
        TextView tvMenuSaturday;
        TextView tvMenuSunday;
        Context context;
        List<VendorPackage> Packages;
        List<String> Keys;
        String key;

        public PackageViewHolder(View itemView, Context context, List<VendorPackage> vendorPackages, List<String> keys) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cv_packages);
            tvPackageName = (TextView) itemView.findViewById(R.id.cv_tv_packageName);
            tvPackageCost = (TextView) itemView.findViewById(R.id.cv_tv_packageCost);
            tvMenuMonday = (TextView) itemView.findViewById(R.id.pkg_tv_monday_menu);
            tvMenuTuesday = (TextView) itemView.findViewById(R.id.pkg_tv_tuesday_menu);
            tvMenuWednesday = (TextView) itemView.findViewById(R.id.pkg_tv_wednesday_menu);
            tvMenuThursday = (TextView) itemView.findViewById(R.id.pkg_tv_thursday_menu);
            tvMenuFriday = (TextView) itemView.findViewById(R.id.pkg_tv_friday_menu);
            tvMenuSaturday = (TextView) itemView.findViewById(R.id.pkg_tv_saturday_menu);
            tvMenuSunday = (TextView) itemView.findViewById(R.id.pkg_tv_sunday_menu);
            Packages = vendorPackages;
            Keys = keys;
            this.context = context;

            itemView.setOnClickListener(this);
        }

        @SuppressLint("SetTextI18n")
        public void bind(VendorPackage vendorPackage, String key){
            tvPackageName.setText(vendorPackage.getPackageName());
            tvPackageCost.setText(Integer.toString(vendorPackage.getPackageCost()));
            tvMenuMonday.setText(vendorPackage.getMonday());
            tvMenuTuesday.setText(vendorPackage.getTuesday());
            tvMenuWednesday.setText(vendorPackage.getWednesday());
            tvMenuThursday.setText(vendorPackage.getThursday());
            tvMenuFriday.setText(vendorPackage.getFriday());
            tvMenuSaturday.setText(vendorPackage.getSaturday());
            tvMenuSunday.setText(vendorPackage.getSunday());
            this.key = key;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, OrderDetails.class);
            intent.putExtra("Package_Key", key);
            context.startActivity(intent);
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
