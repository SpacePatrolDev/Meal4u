package com.example.meal4u;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class OrderViewAdapter extends RecyclerView.Adapter<OrderViewAdapter.oViewHolder> {

    public static class oViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        CardView cardView;
        TextView tvVendorName;
        TextView tvPackageName;
        TextView tvStartDate;
        TextView tvEndDate;
        TextView tvPayPlan;
        TextView tvPayMethod;
        TextView tvPackageCost;
        TextView tvTaxPrice;
        TextView tvTotalCost;
        Context context;

        public oViewHolder(@NonNull View v, Context c) {
            super(v);
            cardView = (CardView) v.findViewById(R.id.cv_order_display);
            tvVendorName = (TextView) v.findViewById(R.id.tv_order_vendorName);
            tvPackageName = (TextView) v.findViewById(R.id.tv_order_packageName);
            tvStartDate = (TextView) v.findViewById(R.id.tv_order_startDate);
            tvEndDate = (TextView) v.findViewById(R.id.tv_order_endDate);
            tvPayPlan = (TextView) v.findViewById(R.id.tv_order_payPlan);
            tvPayMethod = (TextView) v.findViewById(R.id.tv_order_payMethod);
            tvPackageCost = (TextView) v.findViewById(R.id.tv_order_package_cost);
            tvTaxPrice = (TextView) v.findViewById(R.id.tv_order_tax_price);
            tvTotalCost = (TextView) v.findViewById(R.id.tv_order_total_cost);
            context = c;
            v.setOnClickListener(this);
        }

        public void bind(CustomerOrder customerOrder, String COkey){
            tvVendorName.setText(customerOrder.getVendorName());
            tvPackageName.setText(customerOrder.getPackageName());
            tvStartDate.setText(customerOrder.getStartDate());
            tvEndDate.setText(customerOrder.getEndDate());
            tvPayPlan.setText(customerOrder.getPayPlan());
            tvPayMethod.setText(customerOrder.getPayMethod());
            double pCost = Integer.parseInt(customerOrder.getPackageCost());
            double tax = pCost *0.05;
            double total = pCost + tax;
            tvPackageCost.setText("$"+String.format(Locale.CANADA, "%.2f", pCost));
            tvTaxPrice.setText("$"+String.format(Locale.CANADA,"%.2f",tax));
            tvTotalCost.setText("$"+String.format(Locale.CANADA,"%.2f",total));
        }

        @Override
        public void onClick(View view) {

        }
    }

    private List<CustomerOrder> customerOrders;
    private List<String> cokeys;
    private Context context;

    public OrderViewAdapter(List<String> cokeys, List<CustomerOrder> customerOrders, Context context) {
        this.cokeys = cokeys;
        this.customerOrders = customerOrders;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderViewAdapter.oViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_cards, parent, false);
        return new oViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewAdapter.oViewHolder holder, int position) {
        holder.bind(customerOrders.get(position), cokeys.get(position));
    }

    @Override
    public int getItemCount() {
        return cokeys == null ? 0 : cokeys.size();
    }
}
