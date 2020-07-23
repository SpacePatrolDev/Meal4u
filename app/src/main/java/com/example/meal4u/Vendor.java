package com.example.meal4u;

public class Vendor {
    String VendorName;
    String VendorRating;
    String VendorTitle;
    String VendorWD;
    String VendorPR;
    int VendorDP;

    Vendor(String name, String rating, String title, String wd, String pr, int dp)
    {
        this.VendorName = name;
        this.VendorRating = rating;
        this.VendorTitle = title;
        this.VendorWD = wd;
        this.VendorPR= pr;
        this.VendorDP = dp;
    }
}
