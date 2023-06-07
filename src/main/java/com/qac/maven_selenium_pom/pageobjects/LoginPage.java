package com.qac.maven_selenium_pom.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.qac.maven_selenium_pom.selenium.SeleniumHelper;


public class LoginPage {

	private String url = "https://demowebshop.tricentis.com/login";

	//static instances
	private static LoginPage m_instance = null;
	private static WebDriver m_driver = null;

	@FindBy(id = "Email")
	WebElement usernameField;

	@FindBy(id = "Password")
	WebElement passwordField;

	@FindBy(css = "input.button-1.login-button")
	WebElement loginButton;

	public LoginPage NavigateToThisPage() {
		m_driver.get(url);
		return LoginPage.getInstance();
	}

	public HomePage EnterCredentials(String _username, String _password) {
		usernameField.clear();
		SeleniumHelper.sendKeys(usernameField, _username);

		passwordField.clear();
		SeleniumHelper.sendKeys(passwordField, _password);

		SeleniumHelper.click(loginButton);
		SeleniumHelper.Seconds(3);

		return HomePage.getInstance();
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
