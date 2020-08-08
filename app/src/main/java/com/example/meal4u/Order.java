package com.example.meal4u;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Order {

    public String VendorName;
    public String PackageName;
    public int PackageCost;

    public Order() {
    }

    public Order(String vendorName, String packageName, int packagePrice) {
        this.VendorName = vendorName;
        this.PackageName = packageName;
        this.PackageCost = packagePrice;

    }

    public String getVendorName() {
        return VendorName;
    }

    @JsonProperty("VendorName")
    public void setVendorName(String vendorName) {
        VendorName = vendorName;
    }

    public String getPackageName() {
        return PackageName;
    }

    @JsonProperty("PackageName")
    public void setPackageName(String packageName) {
        PackageName = packageName;
    }

    public int getPackageCost() {
        return PackageCost;
    }

    @JsonProperty("PackagePrice")
    public void setPackageCost(int packageCost) {
        PackageCost = packageCost;
    }
}
