package com.spense_be.spense.classes;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Items {

    public Items(@JsonProperty("businessID") String businessID, @JsonProperty("ID") int ID, 
            @JsonProperty("name") String name, @JsonProperty("iprice") Double iprice, 
            @JsonProperty("quantity") int quantity) {
        this.businessID = businessID;
        this.ID = ID;
        this.name = name;
        this.iprice = iprice;
        this.quantity = quantity;
    }

    private String businessID;
    private int ID;
    private String name;
    private Double iprice;
    private int quantity;

    public String getName() {
        return this.name;
    }

    public Double getPrice() {
        return this.iprice;
    }

    public int getQuantity() {
        return this.quantity;
    }
}
