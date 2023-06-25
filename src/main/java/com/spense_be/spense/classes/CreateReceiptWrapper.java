package com.spense_be.spense.classes;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateReceiptWrapper {
    public Receipts receipts;
    public Business business;
    public UserAcc userAcc;
    
    public CreateReceiptWrapper(@JsonProperty("receipts") Receipts receipts,
            @JsonProperty("business") Business business, @JsonProperty("userAcc") UserAcc userAcc) {
        this.receipts = receipts;
        this.business = business;
        this.userAcc = userAcc;
    }
}