package com.example.meal4u;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class VendorPackage {
    public String PackageName;
    public String PackageCost;
    public String VendorID;
    public Map<String, String> PackageMenu;

    public VendorPackage(String packageName, String packageCost, String vendorID, Map<String, String> packageMenu) {
        PackageName = packageName;
        PackageCost = packageCost;
        VendorID = vendorID;
        PackageMenu = packageMenu;
    }

    public VendorPackage() {
    }

    public String getVendorID() {
        return VendorID;
    }

    @JsonProperty("VendorID")
    public void setVendorID(String vendorID) {
        VendorID = vendorID;
    }

    public String getPackageName() {
        return PackageName;
    }

    @JsonProperty("PackageName")
    public void setPackageName(String packageName) {
        PackageName = packageName;
    }

    public String getPackageCost() {
        return PackageCost;
    }

    @JsonProperty("PackageCost")
    public void setPackageCost(String packageCost) {
        PackageCost = packageCost;
    }

    public Map<String, String> getPackageMenu() {
        return PackageMenu;
    }

    @JsonProperty("PackageMenu")
    public void setPackageMenu(Map<String, String> packageMenu) {
        PackageMenu = packageMenu;
    }
}
