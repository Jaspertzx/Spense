package com.spense_be.spense;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spense_be.spense.classes.Business;
import com.spense_be.spense.classes.Outlets;
import com.spense_be.spense.classes.Server;

@RestController
public class SpenseAPIExternalController {

    Server server = new Server();

    @RequestMapping("/pingSpense")
    public String pingSpense() {
        Outlets outlets = new Outlets("testsws");
        Business business = new Business(outlets);
        return "I am awake" + business.toString();
    }

    @RequestMapping("/createBusinessAcc")
    @ResponseBody
    public String createBusinessAcc(@RequestBody Business ua) throws SQLException, JsonProcessingException {
        return "Creating: " + ua.getName();
    }

}
