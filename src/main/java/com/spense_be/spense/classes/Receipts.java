package com.spense_be.spense.classes;

import org.springframework.data.annotation.Id;

public class Receipts {
    @Id
    private String id;

    private int date;

    private Items[] items;

    private Business business;

    private Staff staff;
}
