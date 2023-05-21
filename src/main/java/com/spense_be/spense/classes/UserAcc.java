package com.spense_be.spense.classes;

import org.springframework.data.annotation.Id;

public class UserAcc {

    public UserAcc(Long id, String username, String password, String email, int mobilePhone, int dateJoined,
            String salt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.mobilePhone = mobilePhone;
        this.dateJoined = dateJoined;
        this.salt = salt;
    }

    @Id
    private Long id;

    private String username;

    private String password;

    private String email;

    private int mobilePhone;

    private int dateJoined;

    private String salt;

    public String retrieveSalt() {
        return this.salt;
    }

    public String retrievePassword() {
        return this.password;
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

    public boolean checkPassword(String hashed) {
        if (this.password.equals(hashed)) {
            return true;
        }
        return false;
    }
}