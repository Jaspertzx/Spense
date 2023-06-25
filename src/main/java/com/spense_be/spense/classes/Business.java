package com.spense_be.spense.classes;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Business extends UserAcc {
    // GST Reg No
    private String gstRegNo;

    private String businessName;
    // Ngee Ann City
    private Outlets outlets;

    private Misc misc;

    private String api;

    public Business(@JsonProperty("id") Long id, @JsonProperty("username") String username, @JsonProperty("password") String password, @JsonProperty("email") String email, @JsonProperty("mobilePhone") int mobilePhone, @JsonProperty("dateJoined") int dateJoined,
            @JsonProperty("salt") String salt, @JsonProperty("businessMode") int businessMode, @JsonProperty("outlets") Outlets outlets, @JsonProperty("businessName") String businessName) {
        super(id, username, password, email, mobilePhone, dateJoined, salt, 1);
        this.outlets = outlets;
        this.businessName = businessName;
    }

    public Business() {
        super();
    }

    public Business(Long id, String name) {
        super(id);
        this.businessName = name;
    }

    public String getBusinessName() {
        return this.businessName;
    }

    @JsonProperty("outlets")
    public String unpackNameFromNestedObject() {
        return this.outlets.toString();
    }
    
    
}
