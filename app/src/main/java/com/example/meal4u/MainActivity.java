package com.example.meal4u;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNav = (BottomNavigationView) findViewById(R.id.bottom_nav_bar);

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new DashboardFragment()).commit();

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment currentFragment = null;

                switch (menuItem.getItemId()) {
                    case R.id.nb_dashboard:
                        currentFragment = new DashboardFragment();
                        break;
                    case R.id.nb_orders:
                        currentFragment = new OrdersFragment();
                        break;
                    case R.id.nb_profile:
                        currentFragment = new ProfileFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, currentFragment)
                        .commit();

                return true;
            }
        });
    }
}