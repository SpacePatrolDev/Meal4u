package com.example.meal4u;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.GenericTypeIndicator;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;

public class VendorDetails extends AppCompatActivity {

    private Context context;
    private Firebase dbRootRef;
    private ImageView imageView;
    private TextView textView_vn;
    private TextView textView_vr;
    private TextView textView_vt;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<VendorPackage> vendorPackages = new ArrayList<>();
    private List<String> packageKeys;

    public VendorDetails(){
        dbRootRef = new Firebase("https://meal4u-69675.firebaseio.com/");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_details);
        context = this;
        imageView = (ImageView) findViewById(R.id.iv_vendorDetails);
        textView_vn = (TextView) findViewById(R.id.tv_vn_vendorDetails);
        textView_vr = (TextView) findViewById(R.id.tv_vr_vendorDetails);
        textView_vt = (TextView) findViewById(R.id.tv_vt_vendorDetails);
        recyclerView = (RecyclerView) findViewById(R.id.rv_packages);
        recyclerView.hasFixedSize();
        layoutManager = new LinearLayoutManager(getBaseContext());
        getVendorPackage();
    }

    private void getVendorPackage() {

        final String VendorKey = getIntent().getStringExtra("Vendor_Key");
        Firebase vendorChildRef = dbRootRef.child("Vendor").child(VendorKey);

        vendorChildRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, String> mapVendor = dataSnapshot.getValue(Map.class);
                imageView.setImageResource(getApplicationContext().getResources()
                        .getIdentifier("drawable/"+ mapVendor.get("VendorDP"), null, getApplicationContext().getPackageName()));
                textView_vn.setText(mapVendor.get("VendorName"));
                textView_vr.setText(mapVendor.get("VendorRating"));
                textView_vt.setText(mapVendor.get("VendorTitle"));
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        Firebase packageRef = dbRootRef.child("Package");
        Query pkgQuery = packageRef.orderByChild("VendorID").equalTo(VendorKey);

        pkgQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                vendorPackages.clear();
                packageKeys = new ArrayList<>();

                for (DataSnapshot vKeyNode : dataSnapshot.getChildren())
                {
                    packageKeys.add(vKeyNode.getKey());
                    GenericTypeIndicator<HashMap<String,Object>> typeIndicator = new GenericTypeIndicator<HashMap<String, Object>>() {};
                    Map<String, Object> objectMap = vKeyNode.getValue(typeIndicator);
                    ObjectMapper objectMapper = new ObjectMapper();
                    VendorPackage vPackages = new VendorPackage();
                    vPackages.setPackageName(Objects.requireNonNull(objectMap.get("PackageName")).toString());
                    vPackages.setPackageCost(Objects.requireNonNull(objectMap.get("PackageCost")).toString());
                    vPackages.setVendorID(objectMap.get("VendorID").toString());
                    vPackages.setPackageMenu(objectMapper.convertValue(objectMap.get("PackageMenu"), Map.class));
                    vendorPackages.add(vPackages);
                }
                adapter = new PackageViewAdapter(vendorPackages, packageKeys, context);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}