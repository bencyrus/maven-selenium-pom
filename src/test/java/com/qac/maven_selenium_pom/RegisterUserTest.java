package com.qac.maven_selenium_pom;

import com.qac.maven_selenium_pom.logging.LogHelper;
import com.qac.maven_selenium_pom.pageobjects.HomePage;
import com.qac.maven_selenium_pom.pageobjects.RegisterPage;
import com.qac.maven_selenium_pom.selenium.SeleniumHelper;
import com.qac.maven_selenium_pom.utilities.Utils;
import com.qac.maven_selenium_pom.config.Log4j2Config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RegisterUserTest {

    private String firstName = "Tom";
    private String lastName = "Wambsgans";
    private String email = "twambsgans" + System.currentTimeMillis() + "@atn.com";
    private String password = "neroandsporus";

    private static Logger logger;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        Log4j2Config.configure();
        logger = LogManager.getLogger(LogHelper.class); // Initialized here so it includes the configs
        Utils.getRunTimeStamp();
        logger.info("Starting test execution");
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        SeleniumHelper.quitDriver();
        logger.info("Execution finished");
    }

    @BeforeEach
    void setUp() throws Exception {
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void register_new_user() {
        try {
            logger.info("Navigating to Register Page and trying to register with firstName: " + firstName + ", lastName: " + lastName + ", email: " + email);
            RegisterPage.getInstance()
                    .NavigateToThisPage()
                    .Register(firstName, lastName, email, password);

            HomePage homePage = HomePage.getInstance();

            logger.info("Registered. Verifying that the correct user's email is displayed in the header.");
            // Verify that the correct user's email is displayed in the header
            String accountEmail = homePage.getAccountEmail();
            assertEquals(accountEmail, email, "Account email does not match the registered user email");

            logger.info("User's email verified successfully");

        } catch (Exception e) {
            logger.error("Test failed with exception", e);
        }
    }
}
