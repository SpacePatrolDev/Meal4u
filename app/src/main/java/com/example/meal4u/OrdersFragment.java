package com.example.meal4u;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrdersFragment extends Fragment{

    private List<Order> orders = new ArrayList<>();
    private List<String> orderKeys;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Firebase dbRootRef;
    private Context context;
    private String customerEmail;
    private String customerID;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orders, container, false);
        if(getArguments() != null)
            customerEmail = getArguments().getString("CustomerEmail");
        context = this.getActivity();

        recyclerView = (RecyclerView) view.findViewById(R.id.order_page);
        recyclerView.hasFixedSize();
        layoutManager = new LinearLayoutManager(getActivity());

        Firebase customerRef = dbRootRef.child("Customer");
        Query customerQ = customerRef.orderByChild("EmailId").equalTo(customerEmail);

        customerQ.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot cKeyNode: dataSnapshot.getChildren())
                {
                    customerID = cKeyNode.getKey();
                    getOrders();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        return view;
    }

    public OrdersFragment() {
        dbRootRef = new Firebase("https://meal4u-69675.firebaseio.com/");
    }

    public void getOrders(){
        Firebase orderRef = dbRootRef.child("Order");
        Query customerOrder = orderRef.orderByChild("CustomerID").equalTo(customerID);

        customerOrder.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                orders.clear();
                orderKeys = new ArrayList<>();

                for(DataSnapshot vKeyNode : dataSnapshot.getChildren())
                {
                    orderKeys.add(vKeyNode.getKey());
                    Order order = vKeyNode.getValue(Order.class);
                    orders.add(order);
                }
                adapter = new OrderViewAdapter(orders, orderKeys, context);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
