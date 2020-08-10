package com.example.meal4u;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.Map;


public class ProfileFragment extends Fragment {

    private Firebase dbRootRef;
    private FirebaseAuth firebaseAuth;
    private Context context;
    private String customerEmail;
    private String customerID;

    TextView tvFirstName;
    TextView tvLastName;
    TextView tvEmail;
    TextView tvMobile;
    TextView tvAddress;
    Button logoutButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        if(getArguments() != null)
            customerEmail = getArguments().getString("CustomerEmail");
        context = this.getActivity();

        tvFirstName = (TextView) view.findViewById(R.id.tv_profile_fname);
        tvLastName = (TextView) view.findViewById(R.id.tv_profile_lname);
        tvEmail = (TextView) view.findViewById(R.id.tv_profile_email);
        tvMobile = (TextView) view.findViewById(R.id.tv_profile_mobile);
        tvAddress = (TextView) view.findViewById(R.id.tv_profile_address);
        logoutButton = (Button) view.findViewById(R.id.btn_profile_logout);

        Firebase customerRef = dbRootRef.child("Customer");
        Query customerQ = customerRef.orderByChild("EmailId").equalTo(customerEmail);

        customerQ.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot cKeyNode: dataSnapshot.getChildren())
                {
                    customerID = cKeyNode.getKey();
                    getCustomerDetails(customerID);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        logoutButton = (Button) view.findViewById(R.id.btn_profile_logout);
        firebaseAuth = FirebaseAuth.getInstance();
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                startActivity(new Intent(context, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });


        return view;
    }

    public ProfileFragment() {
        dbRootRef = new Firebase("https://meal4u-69675.firebaseio.com/");
    }

    public void getCustomerDetails(String custID)
    {
        Firebase customerChildRef = dbRootRef.child("Customer").child(custID);

        customerChildRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, String> mapVendor = dataSnapshot.getValue(Map.class);
                tvFirstName.setText(mapVendor.get("Fname"));
                tvLastName.setText(mapVendor.get("Lname"));
                tvEmail.setText(mapVendor.get("EmailId"));
                String mobile = "+1 "+mapVendor.get("Mobile");
                tvMobile.setText(mobile);
                String address = mapVendor.get("StName")+", "+mapVendor.get("CityName")+" "+mapVendor.get("PinCode");
                tvAddress.setText(address);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }
}

