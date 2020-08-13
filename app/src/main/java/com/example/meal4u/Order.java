package com.example.meal4u;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Order {
    public String StartDate;
    public String EndDate;
    public String PayMethod;
    public String PayPlan;

    public Order() {
    }

    public Order(String startDate, String endDate, String payMethod, String payPlan) {
        StartDate = startDate;
        EndDate = endDate;
        PayMethod = payMethod;
        PayPlan = payPlan;
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
