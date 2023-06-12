package com.qac.maven_selenium_pom.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.qac.maven_selenium_pom.selenium.SeleniumHelper;

public class RegisterPage {

    private String url = "https://demowebshop.tricentis.com/register";

    // static instances
    private static RegisterPage m_instance = null;
    private static WebDriver m_driver = null;

    @FindBy(id = "FirstName")
    WebElement firstNameField;

    @FindBy(id = "LastName")
    WebElement lastNameField;

    @FindBy(id = "Email")
    WebElement emailField;

    @FindBy(id = "Password")
    WebElement passwordField;

    @FindBy(id = "ConfirmPassword")
    WebElement confirmPasswordField;

    @FindBy(id = "register-button")
    WebElement registerButton;

    // Constructor and Singleton implementation
    private RegisterPage() {
        m_driver = SeleniumHelper.getDriver();
        PageFactory.initElements(m_driver, this);
    }

    public static RegisterPage getInstance() {
        if(m_instance == null) {
            m_instance = new RegisterPage();
        }
        return m_instance;
    }

    public RegisterPage NavigateToThisPage() {
        m_driver.get(url);
        return RegisterPage.getInstance();
    }

    public RegisterPage Register(String _firstName, String _lastName, String _email, String _password) {
        firstNameField.clear();
        SeleniumHelper.sendKeys(firstNameField, _firstName);

        lastNameField.clear();
        SeleniumHelper.sendKeys(lastNameField, _lastName);

        emailField.clear();
        SeleniumHelper.sendKeys(emailField, _email);

        passwordField.clear();
        SeleniumHelper.sendKeys(passwordField, _password);

        confirmPasswordField.clear();
        SeleniumHelper.sendKeys(confirmPasswordField, _password);

        SeleniumHelper.click(registerButton);
        SeleniumHelper.Seconds(3);

        return this;
    }
}
