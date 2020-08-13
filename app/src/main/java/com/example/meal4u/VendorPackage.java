package com.example.meal4u;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VendorPackage {
    public String PackageName;
    public String PackageCost;
    public String VendorID;
    public String Monday;
    public String Tuesday;
    public String Wednesday;
    public String Thursday;
    public String Friday;
    public String Saturday;
    public String Sunday;

    public VendorPackage(String packageName, String packageCost, String vendorID, String monday, String tuesday,
                         String wednesday, String thursday, String friday, String saturday, String sunday) {
        PackageName = packageName;
        PackageCost = packageCost;
        VendorID = vendorID;
        Monday = monday;
        Tuesday = tuesday;
        Wednesday = wednesday;
        Thursday = thursday;
        Friday = friday;
        Saturday = saturday;
        Sunday = sunday;
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

    public String getMonday() {
        return Monday;
    }

    @JsonProperty("Monday")
    public void setMonday(String monday) {
        Monday = monday;
    }

    public String getTuesday() {
        return Tuesday;
    }

    @JsonProperty("Tuesday")
    public void setTuesday(String tuesday) {
        Tuesday = tuesday;
    }

    public String getWednesday() {
        return Wednesday;
    }

    @JsonProperty("Wednesday")
    public void setWednesday(String wednesday) {
        Wednesday = wednesday;
    }

    public String getThursday() {
        return Thursday;
    }

    @JsonProperty("Thursday")
    public void setThursday(String thursday) {
        Thursday = thursday;
    }

    public String getFriday() {
        return Friday;
    }

    @JsonProperty("Friday")
    public void setFriday(String friday) {
        Friday = friday;
    }

    public String getSaturday() {
        return Saturday;
    }

    @JsonProperty("Saturday")
    public void setSaturday(String saturday) {
        Saturday = saturday;
    }

    public String getSunday() {
        return Sunday;
    }

    @JsonProperty("Sunday")
    public void setSunday(String sunday) {
        Sunday = sunday;
    }
}
