package com.spense_be.spense;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.spense_be.spense.classes.Business;
import com.spense_be.spense.classes.Items;
import com.spense_be.spense.classes.Receipts;

public class ReceiptTester {
    
    @Test
    public void testGetters() {
        // Create test data
        String id = "R12345";
        int date = 1627012800; // July 23, 2023, 00:00:00 UTC (Unix timestamp)
        Items[] items = new Items[]{new Items("Item1", 2, id, null, date), new Items("Item2", 3, id, null, date)};
        double price = 50.0;
        double discount = 5.0;
        String paymentMethod = "Credit Card";
        Business business = new Business(null, "ABC Store");

        // Create Receipts instance using the comprehensive constructor
        Receipts receipts = new Receipts(id, date, items, price, discount, paymentMethod, business, "John Doe", null, null);

        // Test the getter methods
        Assert.assertEquals(receipts.getId(), id);
        Assert.assertEquals(receipts.getDate(), date);
        Assert.assertEquals(receipts.getItems(), items);
        Assert.assertEquals(receipts.getPrice(), price);
        Assert.assertEquals(receipts.getDiscount(), discount);
        Assert.assertEquals(receipts.getPaymentMethod(), paymentMethod);
        Assert.assertEquals(receipts.getStaffName(), "John Doe");
        Assert.assertEquals(receipts.getBusinessName(), "ABC Store");
    }

    @Test
    public void testPartialConstructor() {
        // Create test data
        String id = "R98765";
        int date = 1627012800; // July 23, 2023, 00:00:00 UTC (Unix timestamp)
        double price = 100.0;
        String paymentMethod = "Cash";
        Business business = new Business(null, "XYZ Mart");

        // Create Receipts instance using the partial constructor
        Receipts receipts = new Receipts(id, date, price, paymentMethod, business, null);

        // Test the getter methods
        Assert.assertEquals(receipts.getId(), id);
        Assert.assertEquals(receipts.getDate(), date);
        Assert.assertNull(receipts.getItems()); // Should be null as it's not provided in the constructor
        Assert.assertEquals(receipts.getPrice(), price);
        Assert.assertEquals(receipts.getDiscount(), 0.0); // Default discount is 0.0
        Assert.assertEquals(receipts.getPaymentMethod(), paymentMethod);
        Assert.assertNull(receipts.getStaffName()); // Should be null as it's not provided in the constructor
        Assert.assertEquals(receipts.getBusinessName(), "XYZ Mart");
    }

}
