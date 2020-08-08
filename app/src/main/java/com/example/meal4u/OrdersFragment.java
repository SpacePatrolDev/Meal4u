package com.example.meal4u;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.client.Firebase;
import com.firebase.client.Query;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OrdersFragment extends Fragment{

private RecyclerView recyclerView;
private RecyclerView.LayoutManager layoutManager;
private Firebase dbRootRef;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_orders, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_packages);
        recyclerView.hasFixedSize();
        layoutManager = new LinearLayoutManager(getActivity());
        getCustomer();

        return view;
    }





    public OrdersFragment(){
        dbRootRef = new Firebase("https://meal4u-69675.firebaseio.com/");
    }

    private void getCustomer() {

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseAuth.AuthStateListener authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null)
                {
                    String userId = firebaseUser.getProviderId();
                    String CustId = firebaseUser.getUid();
                }
            }
        };
    }

    private Intent getIntent() {
        return null;
    }

}
