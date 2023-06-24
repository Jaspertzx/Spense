package com.spense_be.spense.classes;

public class CreateReceiptWrapper {
    public Receipts receipts;
    public Business business;
    public UserAcc userAcc;
    
    // Getters and setters for the properties
    
    // Constructor
    public CreateReceiptWrapper(Receipts receipts, Business business, UserAcc userAcc) {
        this.receipts = receipts;
        this.business = business;
        this.userAcc = userAcc;
    }
}