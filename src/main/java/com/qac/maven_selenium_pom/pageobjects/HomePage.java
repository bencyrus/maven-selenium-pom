package com.qac.maven_selenium_pom.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.qac.maven_selenium_pom.selenium.SeleniumHelper;

public class HomePage {

    private String url = "https://demowebshop.tricentis.com/";

    // static instances
    private static HomePage m_instance = null;
    private static WebDriver m_driver = null;

    @FindBy(css = ".header-links-wrapper .account")
    WebElement accountEmailElement;

    @FindBy(id = "small-searchterms")
    WebElement searchInputField;

    @FindBy(css = "input[value='Search']")
    WebElement searchButton;

    // Constructor and Singleton implementation
    private HomePage() {
        m_driver = SeleniumHelper.getDriver();
        PageFactory.initElements(m_driver, this);
    }

    public static HomePage getInstance() {
        if(m_instance == null) {
            m_instance = new HomePage();
        }
        return m_instance;
    }

    public String getAccountEmail() {
        return accountEmailElement.getText();
    }

    public HomePage NavigateToThisPage() {
        m_driver.get(url);
        return HomePage.getInstance();
    }

    public HomePage SearchProduct(String _productName) {
        searchInputField.clear();
        SeleniumHelper.sendKeys(searchInputField, _productName);
        SeleniumHelper.click(searchButton);
        SeleniumHelper.Seconds(3);

        return this;
    }
}
