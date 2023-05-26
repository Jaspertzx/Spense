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
}
