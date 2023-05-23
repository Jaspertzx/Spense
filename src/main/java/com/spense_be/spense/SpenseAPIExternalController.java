package com.spense_be.spense;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpenseAPIExternalController {

    @RequestMapping("/pingSpense")
    public String pingSpense() {
        return "I am awake";
    }
}
