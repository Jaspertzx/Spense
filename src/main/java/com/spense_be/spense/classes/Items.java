package com.spense_be.spense.classes;

public class Items {

    public Items(String businessID, String ID, String name, Double price) {
        this.businessID = businessID;
        this.ID = ID;
        this.name = name;
        this.price = price;
    }

    private String businessID;
    private String ID;
    private String name;
    private Double price;

    public String getName() {
        return this.name;
    }

    public Double getPrice() {
        return this.price;
    }
}
