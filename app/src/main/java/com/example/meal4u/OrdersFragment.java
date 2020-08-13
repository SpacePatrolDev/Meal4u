package com.example.meal4u;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrdersFragment extends Fragment{

    private List<CustomerOrder> customerOrders = new ArrayList<>();
    private List<String> COKeys;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Firebase dbRootRef;
    private Context context;
    private String customerEmail;
    private String customerID;
    private int a=0;

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
                    getCustomerOrders();
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

    public void getCustomerOrders(){
        Firebase custOrderRef = dbRootRef.child("CustomerOrder");
        Query customerOrder = custOrderRef.orderByChild("CustomerID").equalTo(customerID);

        customerOrder.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                COKeys = new ArrayList<>();
                for(DataSnapshot snapshot: dataSnapshot.getChildren())
                {
                    COKeys.add(snapshot.getKey());
                    CustomerOrder co = snapshot.getValue(CustomerOrder.class);
                    customerOrders.add(co);
                }
                adapter = new OrderViewAdapter(COKeys, customerOrders, context);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
