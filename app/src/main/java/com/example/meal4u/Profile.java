package com.example.meal4u;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Profile {

    public String Fname;
    public String Lname;
    public String EmailId;
    public String Mobile;
    public String Address;

    public Profile() {
    }

    public Profile(String fname, String lname, String emailId, String mobile, String address) {
        this.Fname = fname;
        this.Lname = lname;
        this.EmailId = emailId;
        this.Mobile = mobile;
        this.Address = address;
    }
    public String getFname() {
        return Fname;
    }

    @JsonProperty("Fname")
    public void setFname(String fname) {
        this.Fname = fname;
    }

    public String getLname() {
        return Lname;
    }

    @JsonProperty("Lname")
    public void setLname(String lname) {
        this.Lname = lname;
    }

    public String getEmailId() {
        return EmailId;
    }

    @JsonProperty("EmailId")
    public void setEmailId(String emailId) {
        this.EmailId = emailId;
    }

    public String getMobile() {
        return Mobile;
    }

    @JsonProperty("Mobile")
    public void setMobile(String mobile) {
        this.Mobile = mobile;
    }

    public String getAddress() {
        return Address;
    }

    @JsonProperty("Address")
    public void setAddress(String address) {
        this.Address = address;
    }
}