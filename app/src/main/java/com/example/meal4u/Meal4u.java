package com.example.meal4u;

import android.app.Application;

import com.firebase.client.Firebase;

public class Meal4u extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
