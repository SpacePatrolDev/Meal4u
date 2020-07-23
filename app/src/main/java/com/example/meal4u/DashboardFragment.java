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

import java.util.ArrayList;
import java.util.Objects;

public class DashboardFragment extends Fragment {

    private static ArrayList<Vendor> vendors;
    private static RecyclerView recyclerView;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    Toolbar toolbar;
    Spinner spinner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        initialize();

        toolbar = (Toolbar) view.findViewById(R.id.tb_dashboard);
        spinner = (Spinner) view.findViewById(R.id.sp_location);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Objects.requireNonNull(getActivity()),
                R.layout.support_simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.locations));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), "Location : "+spinner.getSelectedItem().toString(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_dashboard);
        recyclerView.hasFixedSize();

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecycleViewAdapter(vendors, this.getActivity());
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void initialize(){
        vendors = new ArrayList<Vendor>();
        vendors.add(new Vendor("Royal Foods","4.8","Authentic Indian Cuisine","Mon to Fri","$35 to $65", R.drawable.sample_vendor_img1));
        vendors.add(new Vendor("Taste of Mediterranean","4.5","Delicious Mediterranean Foods","Mon to Thur","$30 to $50", R.drawable.sample_vendor_img2));
        vendors.add(new Vendor("Waterloo Tiffin Service","4.1","Hot & Fresh Lunch, Everyday","Mon to Sat","$40 to $75", R.drawable.sample_vendor_img3));
    }
}
