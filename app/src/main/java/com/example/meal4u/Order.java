package com.example.meal4u;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Order {
    public String CustomerID;
    public String PackageID;
    public String VendorID;
    public String StartDate;
    public String EndDate;
    public String PayMethod;
    public String PayPlan;

    public Order() {
    }

    public Order(String customerID, String packageID, String vendorID, String startDate, String endDate, String payMethod, String payPlan) {
        CustomerID = customerID;
        PackageID = packageID;
        VendorID = vendorID;
        StartDate = startDate;
        EndDate = endDate;
        PayMethod = payMethod;
        PayPlan = payPlan;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    @JsonProperty("CustomerID")
    public void setCustomerID(String customerID) {
        CustomerID = customerID;
    }

    public String getPackageID() {
        return PackageID;
    }

    @JsonProperty("PackageID")
    public void setPackageID(String packageID) {
        PackageID = packageID;
    }

    public String getVendorID() {
        return VendorID;
    }

    @JsonProperty("VendorID")
    public void setVendorID(String vendorID) {
        VendorID = vendorID;
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
