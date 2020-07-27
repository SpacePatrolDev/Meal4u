package com.example.meal4u;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Customer {

    public String Address;
    public String EmailId;
    public String Fname;
    public String Lname;
    public String Mobile;

    public Customer() {
    }

    public Customer(String address, String emailId, String fname, String lname, String mobile) {
        Address = address;
        EmailId = emailId;
        Fname = fname;
        Lname = lname;
        Mobile = mobile;
    }

    public String getAddress() {
        return Address;
    }
    @JsonProperty("Address")
    public void setAddress(String address) {
        Address = address;
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



