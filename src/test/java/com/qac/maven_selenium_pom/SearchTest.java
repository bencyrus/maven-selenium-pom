package com.qac.maven_selenium_pom;

import com.qac.maven_selenium_pom.logging.LogHelper;
import com.qac.maven_selenium_pom.pageobjects.HomePage;
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
import static org.junit.jupiter.api.Assertions.assertTrue;

class SearchTest {

    private String query = "laptop";
    private String expectedURL = "https://demowebshop.tricentis.com/search?q=" + query;

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
    void search_product() {
        try {
            logger.info("Navigating to Home Page and searching for product: " + query);
            HomePage.getInstance()
                    .NavigateToThisPage()
                    .SearchProduct(query);

            logger.info("Search performed. Verifying that the correct search results page is displayed.");
            // Verify that the correct search results page is displayed
            String currentURL = SeleniumHelper.getDriver().getCurrentUrl();
            assertTrue(currentURL.contains(expectedURL), "Current URLdoes not contain the expected URL");

            logger.info("Search results page verified successfully");

        } catch (Exception e) {
            logger.error("Test failed with exception", e);
        }
    }
}
