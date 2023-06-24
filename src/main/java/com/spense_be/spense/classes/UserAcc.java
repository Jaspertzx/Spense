package com.spense_be.spense.classes;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserAcc {

    // change to the biggest constructors
    // public UserAcc(String username, String password) {
    //     this.username = username;
    //     this.password = password;
    // }
    
    public UserAcc(@JsonProperty("username") String username, @JsonProperty("password") String password, @JsonProperty("email") String email, @JsonProperty("mobilePhone") int mobilePhone) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.mobilePhone = mobilePhone;
    }

    public UserAcc(Long id, String username, String password, String email, int mobilePhone, int dateJoined,
            String salt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.mobilePhone = mobilePhone;
        this.dateJoined = dateJoined;
        this.salt = salt;
        this.businessMode = 0;
    }
    
    public UserAcc(Long id, String username, String password, String email, int mobilePhone, int dateJoined,
            String salt, int businessMode) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.mobilePhone = mobilePhone;
        this.dateJoined = dateJoined;
        this.salt = salt;
        this.businessMode = businessMode;
    }

    private Long id;

    private String username;

    private String password;

    private String email;

    private int mobilePhone;

    private int dateJoined;

    private String salt;
    // 0 = users
    private int businessMode;

    public String retrieveSalt() {
        return this.salt;
    }

    public String retrievePassword() {
        return this.password;
    }
    
    public Long getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }

    public int getMobilePhone() {
        return this.mobilePhone;
    }

    public int getBusinessMode() {
        return this.businessMode;
    }

    public boolean checkPassword(String hashed) {
        if (this.password.equals(hashed)) {
            return true;
        }
        return false;
    }
}