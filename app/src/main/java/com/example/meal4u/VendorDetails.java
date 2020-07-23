package com.example.meal4u;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class VendorDetails extends AppCompatActivity {

    ImageView imageView;
    TextView textView_vn;
    TextView textView_vr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_details);

        imageView = findViewById(R.id.iv_vendorDetails);
        textView_vn = findViewById(R.id.tv_vn_vendorDetails);
        textView_vr = findViewById(R.id.tv_vr_vendorDetails);
        imageView.setImageResource(getIntent().getIntExtra("Vendor_Img",0));
        textView_vn.setText(getIntent().getStringExtra("Vendor_Name"));
        textView_vr.setText(getIntent().getStringExtra("Vendor_Rating"));
    }
}