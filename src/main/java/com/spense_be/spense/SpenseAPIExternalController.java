package com.spense_be.spense;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spense_be.spense.classes.Business;

@RestController
public class SpenseAPIExternalController {

    @RequestMapping("/pingSpense")
    public String pingSpense() {
        return "I am awake";
    }

    @RequestMapping("/createBusinessAcc")
    @ResponseBody
    public String createBusinessAcc(@RequestBody Business ua) throws SQLException {
        return "Creating: " + Business.getName();
    }

}
