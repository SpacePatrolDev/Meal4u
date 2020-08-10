package com.example.meal4u;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Customer {

    public String StName;
    public String CityName;
    public String PinCode;
    public String EmailId;
    public String Fname;
    public String Lname;
    public String Mobile;

    public Customer() {
    }

    public Customer(String stName, String cityName, String pinCode, String emailId, String fname, String lname, String mobile) {
        StName = stName;
        CityName = cityName;
        PinCode = pinCode;
        EmailId = emailId;
        Fname = fname;
        Lname = lname;
        Mobile = mobile;
    }

    public String getCityName() {
        return CityName;
    }
    @JsonProperty("CityName")
    public void setCityName(String cityName) {
        CityName = cityName;
    }

    public String getPinCode() {
        return PinCode;
    }
    @JsonProperty("PinCode")
    public void setPinCode(String pinCode) {
        PinCode = pinCode;
    }

    public String getStName() {
        return StName;
    }
    @JsonProperty("StName")
    public void setStName(String stName) {
        StName = stName;
    }

    public String getEmailId() {
        return EmailId;
    }
    @JsonProperty("EmailId")
    public void setEmailId(String emailId) {
        EmailId = emailId;
    }

    public String getFname() {
        return Fname;
    }
    @JsonProperty("Fname")
    public void setFname(String fname) {
        Fname = fname;
    }

    public String getLname() {
        return Lname;
    }
    @JsonProperty("Lname")
    public void setLname(String lname) {
        Lname = lname;
    }

    public String getMobile() {
        return Mobile;
    }
    @JsonProperty("Mobile")
    public void setMobile(String mobile) {
        Mobile = mobile;
    }
}



