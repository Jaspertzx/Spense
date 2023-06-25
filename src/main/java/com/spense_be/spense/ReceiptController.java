package com.spense_be.spense;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.spense_be.spense.classes.Business;
import com.spense_be.spense.classes.CreateReceiptWrapper;
import com.spense_be.spense.classes.Items;
import com.spense_be.spense.classes.Receipts;
import com.spense_be.spense.classes.Server;
import com.spense_be.spense.classes.Todo;
import com.spense_be.spense.classes.UserAcc;
import com.spense_be.spense.classes.Warranty;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ReceiptController {

    // Get all Users
    @PostMapping("/createReceipt")
    @ResponseBody
    public String createReceipt(@RequestBody CreateReceiptWrapper total) throws SQLException {
        Receipts r = total.receipts;
        Business b = total.business;
        UserAcc u = total.userAcc;
        System.out.println(r.getPaymentMethod());
        UUID uuid1 = UUID.randomUUID();
        String receipt_id = b.getId() + "-" + uuid1.toString();
        long time = Instant.now().getEpochSecond();
        this.updateDatabaseQuery("INSERT INTO receipts (receiptID, dateissued, price, discount, paymentMethod, staffName, UserID, businessId) VALUES ('" + receipt_id + "', " + time + ", " + r.getPrice() + ", " + r.getDiscount() + ", '" + r.getPaymentMethod() + "', '" + r.getStaffName() + "', " + u.getId() + ", " + b.getId() + ");");
        Items[] items = r.getItems();
        String query = "INSERT INTO items (businessid, name, iPrice, quantity, receiptId) VALUES ";
        for (int i = 0; i < items.length; i++) {
            query += "(" + b.getId() + ", '" + items[i].getName() + "', " + items[i].getPrice() + ", " + items[i].getQuantity() + ", '" + receipt_id + "')";
            if (i + 1 != items.length) {
                query += ",";
            }
        }
        this.updateDatabaseQuery(query);
        return "True";
    }

    @PostMapping("/getReceipt")
    @ResponseBody
    public Receipts getReceipt(@RequestBody Receipts r) throws SQLException {
        String query = "SELECT r.*, i.*, b.* FROM Receipts r JOIN Items i ON r.receiptID = i.receiptId Join Business b ON r.businessID = b.businessID WHERE r.receiptID =  '" + r.getId() + "'";
        ResultSet rs = this.runDatabaseQuery(query);        
        List<Items> itemList = new ArrayList<>();
        int date = 0;
        Double price = 0.0;
        Double discount = 0.0;
        String paymentMethod = "";
        Business b = new Business();
        String staffName = "";

        while (rs.next()) {
            date = rs.getInt("dateissued");
            price = rs.getDouble("price");
            discount = rs.getDouble("discount");
            paymentMethod = rs.getString("paymentMethod");
            b = new Business(rs.getLong("businessId"), rs.getString("businessName"));
            staffName = rs.getString("staffName");
            itemList.add(new Items(rs.getString("businessId"), rs.getInt("id"), rs.getString("name"), 
                    rs.getDouble("iprice"), rs.getInt("quantity")));
        }
        Items[] items = new Items[itemList.size()];
        items = itemList.toArray(items);
        Receipts receipts = new Receipts(r.getId(), date, items, price, discount, paymentMethod, b, staffName, new Warranty());
        return receipts;
    }
    
    @PostMapping("/getUserReceipt")
    @ResponseBody
    public List<Receipts> getUserReceipt(@RequestBody UserAcc u) throws SQLException {
        System.out.println("yo");
        String query = "SELECT r.receiptId, r.dateissued, r.price, r.paymentMethod, r.businessId, b.businessName FROM Receipts r JOIN Business b ON r.businessId = b.businessId WHERE r.userId = " + u.getId();
        ResultSet rs = this.runDatabaseQuery(query);
        List<Receipts> receiptList = new ArrayList<>();
        while (rs.next()) {
            String receiptId = rs.getString("receiptId");
            int date = rs.getInt("dateissued");
            Double price = rs.getDouble("price");
            String paymentMethod = rs.getString("paymentMethod");
            Business b = new Business(rs.getLong("businessId"), rs.getString("businessName"));
            System.out.println(rs.getLong("businessId"));
            Receipts r = new Receipts(receiptId, date, price, paymentMethod, b);
            receiptList.add(r);
        }
        return receiptList;
    }    
    
    public ResultSet runDatabaseQuery(String queryString) throws SQLException {
        Properties properties = new Properties();
        try {
            properties.load(SpenseController.class.getClassLoader().getResourceAsStream("app.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties);
        System.out.println("after connect");
        PreparedStatement readStatement = connection.prepareStatement(queryString);
        System.out.println("after read");
        return readStatement.executeQuery();
    }

    public void updateDatabaseQuery(String queryString) throws SQLException {
        Properties properties = new Properties();
        try {
            properties.load(SpenseController.class.getClassLoader().getResourceAsStream("app.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties);
        System.out.println("after connect");
        PreparedStatement readStatement = connection.prepareStatement(queryString);
        System.out.println("after read");
        readStatement.executeUpdate();
    }    

}
