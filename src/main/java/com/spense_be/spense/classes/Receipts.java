package com.spense_be.spense.classes;

public class Receipts {

    public Receipts(String id, int date, Items[] items, double price, double discount, String paymentMethod, Business business, String staffName, Warranty warranty) {
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

    private String id;

    private int date;

    public Items[] items;

    private double price;

    private double discount;

    private String paymentMethod;

    private Business business;

    private String staffName;

    private Warranty warranty;

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
}
