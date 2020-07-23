package com.example.meal4u;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabase {
    private static Firebase dbRootRef;
    private List<Vendor> vendors = new ArrayList<>();

    public interface DataStatus{
        void isLoaded(List<Vendor> vendors, List<String> vendorKeys);
        void isInserted();
        void isUpdated();
        void isDeleted();
    }

    public FirebaseDatabase() {
        dbRootRef = new Firebase("https://meal4u-69675.firebaseio.com/");
    }

    public void getVendors(final DataStatus dataStatus) {
        dbRootRef.child("Vendor").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                vendors.clear();
                List<String> vendorKeys = new ArrayList<>();

                for (DataSnapshot vKeyNode : dataSnapshot.getChildren())
                {
                    vendorKeys.add(vKeyNode.getKey());
                    Vendor vendor = vKeyNode.getValue(Vendor.class);
                    vendors.add(vendor);
                }
                dataStatus.isLoaded(vendors, vendorKeys);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
