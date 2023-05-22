package com.spense_be.spense.classes;

import org.springframework.data.annotation.Id;

public class Business {
    // autogen
    @Id
    private String id;
    // ETC: Best Denki PTE LTD
    private String name;
    // GST Reg No
    private String gstRegNo;
    // Ngee Ann City
    private Outlets[] outlets;

    private Staff[] staff;

    private Misc misc;
}
