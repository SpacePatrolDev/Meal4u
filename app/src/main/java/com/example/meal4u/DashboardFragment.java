package com.example.meal4u;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DashboardFragment extends Fragment {

    private List<Vendor> vendors = new ArrayList<>();
    private List<String> vendorKeys;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private Firebase dbRootRef;
    private Context context;
    private String customerEmail;
    private String customerID;
    private RecyclerView.LayoutManager layoutManager;

    Toolbar toolbar;
    Spinner spinner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        if(getArguments() != null)
            customerEmail = getArguments().getString("CustomerEmail");
        context = this.getActivity();
        toolbar = (Toolbar) view.findViewById(R.id.tb_dashboard);
        spinner = (Spinner) view.findViewById(R.id.sp_location);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Objects.requireNonNull(getActivity()),
                R.layout.support_simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.locations));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               Toast.makeText(getActivity(), "Location : "+spinner.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
                getVendors(spinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        Firebase customerRef = dbRootRef.child("Customer");
        Query customerQ = customerRef.orderByChild("EmailId").equalTo(customerEmail);

        customerQ.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot cKeyNode: dataSnapshot.getChildren())
                {
                    customerID = cKeyNode.getKey();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_dashboard);
        recyclerView.hasFixedSize();

        layoutManager = new LinearLayoutManager(getActivity());

        return view;
    }

    public DashboardFragment(){
        dbRootRef = new Firebase("https://meal4u-69675.firebaseio.com/");
    }

    public void getVendors(String currentLocation) {
        Firebase vendorRef = dbRootRef.child("Vendor");
        Query locationQuery = vendorRef.orderByChild("VendorAOS").equalTo(currentLocation);

        locationQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                vendors.clear();
                vendorKeys = new ArrayList<>();

                for (DataSnapshot vKeyNode : dataSnapshot.getChildren())
                {
                    vendorKeys.add(vKeyNode.getKey());
                    Vendor vendor = vKeyNode.getValue(Vendor.class);
                    vendors.add(vendor);
                }
                adapter = new VendorViewAdapter(vendors, vendorKeys, customerID, context);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
