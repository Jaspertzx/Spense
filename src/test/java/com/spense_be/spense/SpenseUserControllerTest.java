package com.spense_be.spense;

import com.spense_be.spense.classes.UserAcc;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

public class SpenseUserControllerTest {
 private SpenseUserController controller;

    @BeforeClass
    public void setup() {
        // Initialize any setup required before the tests run
        controller = new SpenseUserController();
    }

    @AfterClass
    public void cleanup() {
        // Perform any cleanup after all tests are executed
    }

    @Test
    public void testSignUpRequest() throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
        UserAcc user = new UserAcc("testUser", "testPassword", "test@example.com", 123456789);
        String result = controller.signUpRequest(user);
        Assert.assertEquals(result, "created");
    }

    @Test
    public void testLoginRequest() throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
        // First, create a test user in the database for login
        UserAcc user = new UserAcc("testUser", "testPassword", "test@example.com", 123456789);
        controller.signUpRequest(user);

        // Then, test the login request
        UserAcc loggedInUser = new UserAcc("testUser", "testPassword", null, 0);
        String result = controller.loginRequest(loggedInUser);

        Assert.assertNotNull(result, "Login successful");
    }
}