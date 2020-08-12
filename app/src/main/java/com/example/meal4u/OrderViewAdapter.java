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
        List<Order> orderData;
        List<String> orderKeys;
        String orderKey;

        public oViewHolder(@NonNull View v, Context c, List<Order> ol, List<String > kl) {
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
            orderData = ol;
            orderKeys = kl;
            v.setOnClickListener(this);
        }

        public void bind(Order order, String oKey){
            tvVendorName.setText(order.getVendorName());
            tvPackageName.setText(order.getPackageName());
            tvStartDate.setText(order.getStartDate());
            tvEndDate.setText(order.getEndDate());
            tvPayPlan.setText(order.getPayPlan());
            tvPayMethod.setText(order.getPayMethod());
            double pCost = Integer.parseInt(order.getPackageCost());
            double tax = pCost *0.05;
            double total = pCost + tax;
            tvPackageCost.setText("$"+String.format(Locale.CANADA, "%.2f", pCost));
            tvTaxPrice.setText("$"+tax);
            tvTotalCost.setText("$"+total);
            orderKey = oKey;
        }

        @Override
        public void onClick(View view) {

        }
    }

    private List<Order> orders;
    private List<String> orderKeys;
    private Context context;

    public OrderViewAdapter(List<Order> orders, List<String> orderKeys, Context context) {
        this.orders = orders;
        this.orderKeys = orderKeys;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderViewAdapter.oViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_cards, parent, false);
        return new oViewHolder(view, context, orders, orderKeys);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewAdapter.oViewHolder holder, int position) {
        holder.bind(orders.get(position), orderKeys.get(position));
    }

    @Override
    public int getItemCount() {
        return orders == null ? 0 : orders.size();
    }
}
