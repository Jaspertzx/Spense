package com.spense_be.spense;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spense_be.spense.classes.Business;
import com.spense_be.spense.classes.Outlets;
import com.spense_be.spense.classes.Server;

@RestController
public class SpenseAPIExternalController {

    Server server = new Server();

    @RequestMapping("/pingSpense")
    public String pingSpense() {
        Outlets outlets = new Outlets("testsws");
        return "I am awake";
    }

    @RequestMapping("/createBusinessAcc")
    @ResponseBody
    public String createBusinessAcc(@RequestBody Business ua) throws SQLException, JsonProcessingException {
        return "Creating: " + ua.getBusinessName();
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "HELPSERROR")
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public String handleException(HttpMessageNotReadableException ex) {
        System.out.print("ERROR");
        return "HEY WHAT ARE YOU DOING!";
    }

}
