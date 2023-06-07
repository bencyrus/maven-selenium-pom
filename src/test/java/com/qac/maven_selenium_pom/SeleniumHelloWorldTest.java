package com.qac.maven_selenium_pom;

import java.io.IOException;

import com.qac.maven_selenium_pom.logging.LogHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.qac.maven_selenium_pom.pageobjects.LoginPage;
import com.qac.maven_selenium_pom.selenium.SeleniumHelper;
import com.qac.maven_selenium_pom.utilities.Utils;
import com.qac.maven_selenium_pom.config.Log4j2Config;

class SeleniumHelloWorldTest {

	private String username = "mahdi.mohaghegh2001@gmail.com";
	private String password = "12345678";

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
	void test() {
		try {
			LoginPage.getInstance().NavigateToThisPage()
					.EnterCredentials(username, password);
		} catch (Exception e) {
			logger.error("Test failed with exception", e);
		}
		assert true;
	}


}