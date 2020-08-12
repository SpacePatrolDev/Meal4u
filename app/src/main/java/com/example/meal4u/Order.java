package com.example.meal4u;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Order {
    public String CustomerID;
    public String PackageName;
    public String VendorName;
    public String StartDate;
    public String EndDate;
    public String PayMethod;
    public String PayPlan;
    public String PackageCost;

    public Order() {
    }

    public Order(String customerID, String packageName, String vendorName, String startDate, String endDate, String payMethod, String payPlan, String packageCost) {
        CustomerID = customerID;
        PackageName = packageName;
        VendorName = vendorName;
        StartDate = startDate;
        EndDate = endDate;
        PayMethod = payMethod;
        PayPlan = payPlan;
        PackageCost = packageCost;
    }

    public String getPackageCost() {
        return PackageCost;
    }

    @JsonProperty("PackageCost")
    public void setPackageCost(String packageCost) {
        PackageCost = packageCost;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    @JsonProperty("CustomerID")
    public void setCustomerID(String customerID) {
        CustomerID = customerID;
    }

    public String getPackageName() {
        return PackageName;
    }

    @JsonProperty("PackageID")
    public void setPackageName(String packageName) {
        PackageName = packageName;
    }

    public String getVendorName() {
        return VendorName;
    }

    @JsonProperty("VendorID")
    public void setVendorName(String vendorName) {
        VendorName = vendorName;
    }

    public String getStartDate() {
        return StartDate;
    }

    @JsonProperty("StartDate")
    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    @JsonProperty("EndDate")
    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public String getPayMethod() {
        return PayMethod;
    }

    @JsonProperty("PayMethod")
    public void setPayMethod(String payMethod) {
        PayMethod = payMethod;
    }

    public String getPayPlan() {
        return PayPlan;
    }

    @JsonProperty("PayPlan")
    public void setPayPlan(String payPlan) {
        PayPlan = payPlan;
    }
}
