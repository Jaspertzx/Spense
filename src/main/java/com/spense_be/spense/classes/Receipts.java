package com.spense_be.spense.classes;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Receipts {

    public Receipts(@JsonProperty("id") String id, @JsonProperty("date") int date, @JsonProperty("items") Items[] items, 
            @JsonProperty("price") double price, @JsonProperty("discount") double discount, @JsonProperty("paymentMethod") String paymentMethod, 
            @JsonProperty("business") Business business, @JsonProperty("staffName") String staffName, @JsonProperty("warranty") Warranty warranty) {
        this.id = id;
        this.date = date;
        this.items = items;
        this.price = price;
        this.discount = discount;
        this.paymentMethod = paymentMethod;
        this.business = business;
        this.staffName = staffName;
        this.warranty = warranty;
    }

    public Receipts(String id, int date, double price, String paymentMethod, Business business) {
        this.id = id;
        this.date = date;
        this.price = price;
        this.paymentMethod = paymentMethod;
        this.business = business;
    }

    private String id;

    private int date;

    public Items[] items;

    private double price;

    private double discount;

    private String paymentMethod;

    private Business business;

    private String staffName;

    private Warranty warranty;

    public String getId() {
        return this.id;
    }
    
    public int getDate() {
        return this.date;
    }

    public Items[] getItems() {
        return this.items;
    }

    public double getPrice() {
        return this.price;
    }
    
    public double getDiscount() {
        return this.discount;
    }

    public String getPaymentMethod() {
        return this.paymentMethod;
    }
    
    public String getStaffName() {
        return this.staffName;
    }

    public String getBusinessName() {
        return this.business.getBusinessName();
    }
}
