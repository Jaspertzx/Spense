package com.spense_be.spense;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.spense_be.spense.classes.UserAcc;

public class UserAccTester {
    @Test
    public void testRetrieveSalt() {
        String expectedSalt = "some_random_salt";
        UserAcc user = new UserAcc(1L, "testUser", "password", "test@example.com", 123456789, 1634629812, expectedSalt);
        String actualSalt = user.retrieveSalt();
        Assert.assertEquals(actualSalt, expectedSalt);
    }

    @Test
    public void testRetrievePassword() {
        String expectedPassword = "hashed_password";
        UserAcc user = new UserAcc(1L, "testUser", expectedPassword, "test@example.com", 123456789, 1634629812, "s");
        String actualPassword = user.retrievePassword();
        Assert.assertEquals(actualPassword, expectedPassword);
    }

    @Test
    public void testCheckPassword() {
        String hashedPassword = "hashed_password";
        UserAcc user = new UserAcc(1L, "testUser", hashedPassword, "test@example.com", 123456789, 1634629812, "s");

        // Correct password
        Assert.assertTrue(user.checkPassword(hashedPassword));

        // Incorrect password
        Assert.assertFalse(user.checkPassword("incorrect_password"));
    }

    @Test
    public void testGetters() {
        Long id = 1L;
        String username = "testUser";
        String password = "password";
        String email = "test@example.com";
        int mobilePhone = 123456789;
        int dateJoined = 1626972678;
        String salt = "some_salt";
        int businessMode = 0;

        UserAcc user = new UserAcc(id, username, password, email, mobilePhone, dateJoined, salt, businessMode);

        Assert.assertEquals(user.getId(), id);
        Assert.assertEquals(user.getUsername(), username);
        Assert.assertEquals(user.getEmail(), email);
        Assert.assertEquals(user.getMobilePhone(), mobilePhone);
        Assert.assertEquals(user.getBusinessMode(), businessMode);
    }

    // Add more test cases as needed to cover other scenarios


}