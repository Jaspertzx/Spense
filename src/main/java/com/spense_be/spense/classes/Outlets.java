package com.spense_be.spense.classes;

import org.springframework.data.annotation.Id;

public class Outlets {
    @Id
    private int id;
    // 391 Ochard Road
    private String address;
    // outlet phonenumber
    private int phoneNumber;
    // 10am
    private String openingHours;
    // 10pm
    private String closeHours;

    public Outlets(String address) {
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }
    /*
     * @Override
     * public String toString() {
     * return this.address.toString();
     * }
     */
}
