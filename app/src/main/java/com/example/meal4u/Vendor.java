package com.example.meal4u;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Vendor {
    public String VendorName;
    public String VendorRating;
    public String VendorTitle;
    public String VendorWD;
    public String VendorPR;
    public String VendorDP;
    public String VendorAOS;
    public String VendorAddress;
    public String VendorEmail;
    public String VendorMobile;

    public Vendor() {
    }

    public Vendor(String name, String rating, String title, String wd, String pr, String dp, String aos, String address, String email, String mobile)
    {
        this.VendorName = name;
        this.VendorRating = rating;
        this.VendorTitle = title;
        this.VendorWD = wd;
        this.VendorPR = pr;
        this.VendorDP = dp;
        this.VendorAOS = aos;
        this.VendorAddress = address;
        this.VendorEmail = email;
        this.VendorMobile = mobile;
    }

    public String getVendorName() {
        return VendorName;
    }

    @JsonProperty("VendorName")
    public void setVendorName(String vendorName) {
        VendorName = vendorName;
    }

    public String getVendorRating() {
        return VendorRating;
    }

    @JsonProperty("VendorRating")
    public void setVendorRating(String vendorRating) {
        VendorRating = vendorRating;
    }

    public String getVendorTitle() {
        return VendorTitle;
    }

    @JsonProperty("VendorTitle")
    public void setVendorTitle(String vendorTitle) {
        VendorTitle = vendorTitle;
    }

    public String getVendorWD() {
        return VendorWD;
    }

    @JsonProperty("VendorWD")
    public void setVendorWD(String vendorWD) {
        VendorWD = vendorWD;
    }

    public String getVendorPR() {
        return VendorPR;
    }

    @JsonProperty("VendorPR")
    public void setVendorPR(String vendorPR) {
        VendorPR = vendorPR;
    }

    public String getVendorDP() {
        return VendorDP;
    }

    @JsonProperty("VendorDP")
    public void setVendorDP(String vendorDP) {
        VendorDP = vendorDP;
    }

    public String getVendorAOS() {
        return VendorAOS;
    }

    @JsonProperty("VendorAOS")
    public void setVendorAOS(String vendorAOS) {
        VendorAOS = vendorAOS;
    }

    public String getVendorAddress() {
        return VendorAddress;
    }

    @JsonProperty("VendorAddress")
    public void setVendorAddress(String vendorAddress) {
        VendorAddress = vendorAddress;
    }

    public String getVendorEmail() {
        return VendorEmail;
    }

    @JsonProperty("VendorEmail")
    public void setVendorEmail(String vendorEmail) {
        VendorEmail = vendorEmail;
    }

    public String getVendorMobile() {
        return VendorMobile;
    }

    @JsonProperty("VendorMobile")
    public void setVendorMobile(String vendorMobile) {
        VendorMobile = vendorMobile;
    }
}
