package com.qac.maven_selenium_pom.pageobjects;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import com.qac.maven_selenium_pom.logging.StepLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import com.qac.maven_selenium_pom.selenium.SeleniumHelper;


public class LandingPage {

	private String url = "https://demowebshop.tricentis.com/";
	
	//static instances
	private static LandingPage m_instance = null;
	private static WebDriver m_driver = null;
	
	private String productPricePartOne = "//div[contains(text(), '";
	private String productPricePartTwo = "')]//ancestor::div[@class='inventory_item_description']//descendant::div[@class = 'inventory_item_price']";;

	public LandingPage VerifyPrice(String _product, String _expectedPrice) throws IOException {
		StepLogger.logStep("Verifying " + _product + " has price: " + _expectedPrice);
		
		String xpath = productPricePartOne + _product + productPricePartTwo;
		String loc_productLabel = productPricePartOne + _product + "')]";
		
		m_driver.findElement(By.xpath(loc_productLabel));
		WebElement priceLabel = m_driver.findElement(By.xpath(xpath));
		String actualPrice = priceLabel.getText();
		
		//error handling
		if (!_expectedPrice.equals(actualPrice)) {
			SeleniumHelper.highlightAndScreenshot(priceLabel, true);
		}
		
		String message = "Price for product: " + _product + " was incorrect. Expected: " + _expectedPrice + " and found: " + actualPrice;
		assertEquals(_expectedPrice, actualPrice, message);

		StepLogger.logStep("Finished verifying Price");
		return LandingPage.getInstance();
	}
	
	//Constructor and Singleton implementation
	
	private LandingPage() {
		m_driver = SeleniumHelper.getDriver();
		PageFactory.initElements(m_driver, this);
	}
	
	public static LandingPage getInstance() {
		
		if(m_instance == null)
		{
			m_instance = new LandingPage();
		}
		
		return m_instance;
	}
}
