package com.spense_be.spense.classes;

import org.springframework.data.annotation.Id;

public class Outlets {
    @Id
    private int id;
    // 391 Ochard Road
    private String addresser;
    // outlet phonenumber
    private int phoneNumber;
    // 10am
    private String openingHours;
    // 10pm
    private String closeHours;

    public Outlets(String addresser) {
        this.addresser = addresser;
    }

    /*
     * @Override
     * public String toString() {
     * return this.address.toString();
     * }
     */
}
