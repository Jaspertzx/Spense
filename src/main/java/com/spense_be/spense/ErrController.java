package com.spense_be.spense;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        // do something like logging
        return "error";
    }
}