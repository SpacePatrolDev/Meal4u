package com.example.meal4u;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    public static class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        Context context;
        CardView cardView;
        TextView titleVendor;
        TextView titlePackage;
        TextView pricePackage;
        TextView priceTax;
        TextView priceTotal;
        List<Order> orderData;
        List<String> orderKey;
        String key;

        public OrderViewHolder(View view, Context c, List<Order> al, List<String> k)
        {
            super(view);
            titleVendor = (TextView)view.findViewById(R.id.vendor_title);
            titlePackage = (TextView)view.findViewById(R.id.pkg_title);
            pricePackage = (TextView)view.findViewById(R.id.pkg_price);
            priceTax = (TextView)view.findViewById(R.id.tax_price);
            priceTotal = (TextView)view.findViewById(R.id.total_title);
            cardView = (CardView)view.findViewById(R.id.order_display);
            this.context = c;
            this.orderData = al;
            this.orderKey = k;

            view.setOnClickListener(this);
        }


        public void bind(Order order, String key){
            titleVendor.setText(order.getVendorName());
            titlePackage.setText(order.getPackageName());
            int packagePrice = order.getPackageCost();
            pricePackage.setText(packagePrice);
            double tax = packagePrice + (packagePrice * 0.05);
            priceTax.setText(Double.toString(tax));
            priceTotal.setText(Double.toString(packagePrice + tax));
            this.key = key;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, OrdersFragment.class);
            intent.putExtra("Order_Key", key);
            context.startActivity(intent);
        }
    }

    private List<Order> orderData;
    private List<String> orderKey;
    private Context context;

    public OrderAdapter(List<Order> orders, List<String> keys, Context context){
        this.orderData = orders;
        this.orderKey = keys;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_cards, parent,false);
        return new OrderViewHolder(view, context, orderData, orderKey);
    }

    @Override
    public void onBindViewHolder(@NonNull final OrderViewHolder holder, int position) {
        holder.bind(orderData.get(position), orderKey.get(position));
    }

    @Override
    public int getItemCount() {
        return orderData == null ? 0 : orderData.size();
    }
}




