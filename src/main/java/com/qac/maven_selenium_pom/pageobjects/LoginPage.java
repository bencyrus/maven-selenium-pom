package com.qac.maven_selenium_pom.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.qac.maven_selenium_pom.selenium.SeleniumHelper;


public class LoginPage {

	private String url = "https://www.saucedemo.com/";
	
	//static instances
	private static LoginPage m_instance = null;
	private static WebDriver m_driver = null;
	
	@FindBy(id = "user-name")
	WebElement usernameField;
	
	@FindBy(id = "password")
	WebElement passwordField;
	
	@FindBy(id = "login-button")
	WebElement loginButton;
	
	public LoginPage NavigateToThisPage() {
		m_driver.get(url);
		return LoginPage.getInstance();
	}
	
	public LandingPage EnterCredentials(String _username, String _password) {
		SeleniumHelper.sendKeys(usernameField, _username);
		SeleniumHelper.sendKeys(passwordField, _password);
		SeleniumHelper.click(loginButton);
		SeleniumHelper.Seconds(3);
		return LandingPage.getInstance();
	}
	
	
	//Constructor and Singleton implementation
	
	private LoginPage() {
		m_driver = SeleniumHelper.getDriver();
		PageFactory.initElements(m_driver, this);
	}
	
	public static LoginPage getInstance() {
		
		if(m_instance == null)
		{
			m_instance = new LoginPage();
		}
		
		return m_instance;
	}
}
