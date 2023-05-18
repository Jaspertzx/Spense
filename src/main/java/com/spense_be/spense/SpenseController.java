package com.spense_be.spense;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpenseController {
    
    @RequestMapping("/hello")
    public String hello() {
        return "Hello Qin Yong!!!";
    }
}
