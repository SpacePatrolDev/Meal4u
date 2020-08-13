package com.example.meal4u;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;

public class OrderDetails extends AppCompatActivity {

    private String VendorKey;
    private String VendorName;
    private String CustomerKey;
    private String CustomerName;
    private String CustomerAdd1;
    private String CustomerAdd2;
    private String PackageKey;
    private String PackageName;
    private String PackageCost;
    private Firebase dbRootRef;
    private EditText edStartDate;
    private EditText edEndDate;
    private RadioGroup rgPayPlan;
    private RadioButton rbPayPlan;
    private RadioGroup rgPayMethod;
    private RadioButton rbPayMethod;
    private TextView tvVendorName;
    private TextView tvPackageName;
    private SimpleDateFormat sdf;
    private Calendar calendar;
    private Button btnPlaceOrder;
    private ProgressDialog progressDialog;

    public OrderDetails() {
        dbRootRef = new Firebase("https://meal4u-69675.firebaseio.com/");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        VendorKey = getIntent().getStringExtra("Vendor_Key");
        CustomerKey = getIntent().getStringExtra("Customer_Key");
        PackageKey = getIntent().getStringExtra("Package_Key");
        edStartDate = (EditText) findViewById(R.id.et_od_startDate);
        edEndDate = (EditText) findViewById(R.id.et_od_endDate);
        tvVendorName = (TextView) findViewById(R.id.tv_od_vendorName);
        tvPackageName = (TextView) findViewById(R.id.tv_od_packageName);
        rgPayPlan = (RadioGroup) findViewById(R.id.rg_paymentPlan);
        rgPayMethod = (RadioGroup) findViewById(R.id.rg_paymentMethod);
        btnPlaceOrder = (Button) findViewById(R.id.btn_place_order);

        progressDialog = new ProgressDialog(OrderDetails.this);

        String dateFormat = "dd-MM-yyyy";
        sdf = new SimpleDateFormat(dateFormat, Locale.CANADA);

        getOrderDetails();
        calendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener startDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);
                edStartDate.setText(sdf.format(calendar.getTime()));
            }
        };

        final DatePickerDialog.OnDateSetListener endDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);
                edEndDate.setText(sdf.format(calendar.getTime()));
            }
        };

        edStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(OrderDetails.this, startDate, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        edEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(OrderDetails.this, endDate, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rbPayPlan = (RadioButton) findViewById(rgPayPlan.getCheckedRadioButtonId());
                rbPayMethod = (RadioButton) findViewById(rgPayMethod.getCheckedRadioButtonId());

                progressDialog.setMessage("Placing Order");
                progressDialog.show();

                Order order = new Order();
                order.setPayPlan(rbPayPlan.getText().toString());
                order.setPayMethod(rbPayMethod.getText().toString());
                order.setStartDate(edStartDate.getText().toString());
                order.setEndDate(edEndDate.getText().toString());

                CustomerOrder customerOrder = new CustomerOrder();
                customerOrder.setCustomerID(CustomerKey);
                customerOrder.setCustomerName(CustomerName);
                customerOrder.setCustomerAddress1(CustomerAdd1);
                customerOrder.setCustomerAddress2(CustomerAdd2);
                customerOrder.setVendorName(VendorName);
                customerOrder.setPackageName(PackageName);
                customerOrder.setPackageCost(PackageCost);
                customerOrder.setPayPlan(rbPayPlan.getText().toString());
                customerOrder.setPayMethod(rbPayMethod.getText().toString());
                customerOrder.setStartDate(edStartDate.getText().toString());
                customerOrder.setEndDate(edEndDate.getText().toString());

                Firebase dbReference = dbRootRef.child("Order").push();
                dbReference.setValue(order);

                Firebase CustOrdRef = dbRootRef.child("CustomerOrder").push();
                CustOrdRef.setValue(customerOrder);

                Toast.makeText(OrderDetails.this,"Order Placed!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(OrderDetails.this, LoginActivity.class));

                progressDialog.dismiss();
            }
        });
    }

    public void getOrderDetails(){
        Firebase vendorChildRef = dbRootRef.child("Vendor").child(VendorKey);
        vendorChildRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, String> mapVendor = dataSnapshot.getValue(Map.class);
                VendorName = mapVendor.get("VendorName");
                tvVendorName.setText(VendorName);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        Firebase packageChildRef = dbRootRef.child("Package").child(PackageKey);
        packageChildRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, String> mapPackage = dataSnapshot.getValue(Map.class);
                PackageName = mapPackage.get("PackageName");
                PackageCost = mapPackage.get("PackageCost");
                tvPackageName.setText(PackageName);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        Firebase customerChildRef = dbRootRef.child("Customer").child(CustomerKey);
        customerChildRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, String> mapPackage = dataSnapshot.getValue(Map.class);
                String Fname = mapPackage.get("Fname");
                String Lname = mapPackage.get("Lname");
                CustomerName = Fname+" "+Lname;
                CustomerAdd1 = mapPackage.get("StName");
                String city = mapPackage.get("CityName");
                String pincode = mapPackage.get("PinCode");
                CustomerAdd2 = city+" "+pincode;
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}