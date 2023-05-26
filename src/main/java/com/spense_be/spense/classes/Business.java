package com.spense_be.spense.classes;

import java.util.Map;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Business {
    // autogen
    @Id
    private String id;
    // ETC: Best Denki PTE LTD
    private String name;
    // GST Reg No
    private String gstRegNo;
    // Ngee Ann City
    private Outlets outlets;

    private Misc misc;

    public Business(Outlets outlets) {
        this.outlets = outlets;
    }

    public Business(String name, Outlets outlets) {
        this.name = name;
        this.outlets = outlets;
    }

    public String getName() {
        return this.name;
    }

    @JsonProperty("outlets")
    public String unpackNameFromNestedObject() {
        return this.outlets.toString();
    }

    /*
     * @Override
     * public String toString() {
     * return this.outlets.toString();
     * }
     */
}
