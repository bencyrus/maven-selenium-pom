package com.qac.maven_selenium_pom;

import com.qac.maven_selenium_pom.logging.LogHelper;
import com.qac.maven_selenium_pom.pageobjects.HomePage;
import com.qac.maven_selenium_pom.pageobjects.LoginPage;
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

class ExistingUserLoginTest {

    private String username = "jskuce@test.com";
    private String password = "jskuce123";

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
    void unsuccessful_login_with_incorrect_credentials() {
        try {
            String incorrectUsername = "incorrectUsername";
            String incorrectPassword = "incorrectPassword";

            logger.info("Navigating to Login Page and trying to log in with incorrect credentials");
            LoginPage.getInstance()
                    .NavigateToThisPage()
                    .EnterCredentials(incorrectUsername, incorrectPassword);

            logger.info("Login attempt with incorrect credentials made. Verifying that the correct error message is displayed.");

            // Verify that the correct error message is displayed
            String expectedErrorMessage = "Login was unsuccessful. Please correct the errors and try again.";
            String errorMessage = SeleniumHelper.getDriver().findElement(By.cssSelector(".returning-wrapper .message-error .validation-summary-errors span")).getText();
            assertEquals(errorMessage, expectedErrorMessage, "Error message does not match the expected error message");

            logger.info("Error message verified successfully");

        } catch (Exception e) {
            logger.error("Test failed with exception", e);
        }
    }

    @Test
    void existing_user_login() {
        try {
            logger.info("Navigating to Login Page and trying to log in with username: " + username);
            HomePage homePage = LoginPage.getInstance()
                    .NavigateToThisPage()
                    .EnterCredentials(username, password);

            logger.info("Logged in. Verifying that the correct user's email is displayed in the header.");
            // Verify that the correct user's email is displayed in the header
            String accountEmail = SeleniumHelper.getDriver().findElement(By.cssSelector(".header-links-wrapper .account")).getText();
            assertEquals(accountEmail, username, "Account email does not match the logged in user email");

            logger.info("User's email verified successfully");

        } catch (Exception e) {
            logger.error("Test failed with exception", e);
        }
    }
}
