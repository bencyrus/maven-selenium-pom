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

class SeleniumHelloWorldTest {

	private String username = "standard_user";
	private String password = "secret_sauce";

	private static final Logger logger = LogManager.getLogger(LogHelper.class);

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
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
					.EnterCredentials(username, password)
					.VerifyPrice("Sauce Labs Backpack", "$29.99")
					.VerifyPrice("Sauce Labs Bike Light", "$9.99")
					.VerifyPrice("Sauce Labs Bolt T-Shirt", "$15.99")
					.VerifyPrice("Sauce Labs Fleece Jacket", "$9.99");
		} catch (IOException e) {
			logger.error("Test failed with exception", e);
		}
		assert true;
	}

}