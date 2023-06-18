package com.spense_be.spense.classes;

import java.util.Map;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Business extends UserAcc {
    // GST Reg No
    private String gstRegNo;

    private String businessName;
    // Ngee Ann City
    private Outlets outlets;

    private Misc misc;

    private String api;

    public Business(Long id, String username, String password, String email, int mobilePhone, int dateJoined,
            String salt, int businessMode, Outlets outlets) {
        super(id, username, password, email, mobilePhone, dateJoined, salt, 1);
        this.outlets = outlets;
    }

    public String getBusinessName() {
        return this.businessName;
    }

    @JsonProperty("outlets")
    public String unpackNameFromNestedObject() {
        return this.outlets.toString();
    }

    /*
     * @Override
     * public String toString() {
     * return this.outlets.toString();
     * }
     */
}
