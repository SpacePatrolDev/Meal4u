package com.example.meal4u;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerOrder {
    public String CustomerID;
    public String CustomerName;
    public String CustomerAddress1;
    public String CustomerAddress2;
    public String CustomerMobile;
    public String VendorID;
    public String VendorName;
    public String PackageName;
    public String PackageCost;
    public String StartDate;
    public String EndDate;
    public String PayMethod;
    public String PayPlan;

    public CustomerOrder() {
    }

    public CustomerOrder(String customerID, String customerName, String customerAddress1, String customerAddress2,
                         String customerMobile, String vendorID, String vendorName, String packageName, String packageCost,
                         String startDate, String endDate, String payMethod, String payPlan) {
        CustomerID = customerID;
        CustomerName = customerName;
        CustomerAddress1 = customerAddress1;
        CustomerAddress2 = customerAddress2;
        CustomerMobile = customerMobile;
        VendorID = vendorID;
        VendorName = vendorName;
        PackageName = packageName;
        PackageCost = packageCost;
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

    public String getCustomerName() {
        return CustomerName;
    }
    @JsonProperty("CustomerName")
    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getCustomerAddress1() {
        return CustomerAddress1;
    }
    @JsonProperty("CustomerAddress1")
    public void setCustomerAddress1(String customerAddress1) {
        CustomerAddress1 = customerAddress1;
    }

    public String getCustomerAddress2() {
        return CustomerAddress2;
    }
    @JsonProperty("CustomerAddress2")
    public void setCustomerAddress2(String customerAddress2) {
        CustomerAddress2 = customerAddress2;
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

    public String getPackageCost() {
        return PackageCost;
    }
    @JsonProperty("PackageCost")
    public void setPackageCost(String packageCost) {
        PackageCost = packageCost;
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

    public String getCustomerMobile() {
        return CustomerMobile;
    }
    @JsonProperty("CustomerMobile")
    public void setCustomerMobile(String customerMobile) {
        CustomerMobile = customerMobile;
    }

    public String getVendorID() {
        return VendorID;
    }
    @JsonProperty("VendorID")
    public void setVendorID(String vendorID) {
        VendorID = vendorID;
    }
}

