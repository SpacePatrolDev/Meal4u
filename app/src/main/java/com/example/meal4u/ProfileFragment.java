package com.example.meal4u;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

public class ProfileFragment extends Fragment {

    private TextView fName, lName, email, phone, address;
    private Button logoutButton;
    FirebaseAuth firebase;
    FirebaseDatabase firebaseDatabase;
    private Firebase dbRootRef;
    DatabaseReference reference;
    private String customerID;
    FirebaseUser user;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        getCustomer(customerID);


        fName = view.findViewById(R.id.tv_profile_userFname);
        lName = view.findViewById(R.id.tv_profile_userLname);
        email = view.findViewById(R.id.tv_profile_email);
        phone = view.findViewById(R.id.tv_profile_phoneNum);
        address = view.findViewById(R.id.tv_profile_address);

        context = this.getActivity();


        logoutButton = (Button) view.findViewById(R.id.btn_profile_logout);


        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebase.signOut();
                startActivity(new Intent(context, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        return view;
    }

    public ProfileFragment(String customerID) {
        dbRootRef = new Firebase("https://meal4u-69675.firebaseio.com/");
        this.customerID = customerID;
    }


    public void getCustomer(String Customer) {

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getInstance().getReference();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        customerID = user.getUid();

        reference.child(Customer).child(customerID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                System.out.println("Check" + snapshot.getKey() + "Value" +snapshot.getValue());
                String fname = snapshot.child("Customer").getValue(String.class);
                String lname = snapshot.child("Customer").getValue(String.class);
                String emailId = snapshot.child("Customer").getValue(String.class);
                String mobile = snapshot.child("Customer").getValue(String.class);
                String faddress = snapshot.child("Customer").getValue(String.class);

                fName.setText(fname);
                lName.setText(lname);
                email.setText(emailId);
                phone.setText(mobile);
                address.setText(faddress);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}

