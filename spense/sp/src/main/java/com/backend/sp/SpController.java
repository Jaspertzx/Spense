package com.backend.sp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpController {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello";
    }
}
