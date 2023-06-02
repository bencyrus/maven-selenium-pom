package com.qac.maven_selenium_pom.selenium;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.qac.maven_selenium_pom.logging.StepLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.qac.maven_selenium_pom.utilities.Utils;

import org.apache.commons.io.FileUtils;

public class SeleniumHelper {

	static WebDriver m_driver = null;
	
	public static WebDriver getDriver() {
		
		if (m_driver == null) {
			m_driver = new ChromeDriver();
		}
		
		return m_driver;
	}
	
	public static void quitDriver(){
		assert m_driver != null;
		m_driver.quit();
		m_driver = null;
	}
	
	public static void highlightAndScreenshot(WebElement _element, boolean _wholeScreen) throws IOException {
		
		//bring the element into focus
		SeleniumHelper.ScrollElementIntoView(_element);
		
		//highlight the element
		JavascriptExecutor jse = (JavascriptExecutor) m_driver;
		jse.executeScript("arguments[0].style.outline = '#f00 solid 2px';", _element);
		
		//take the screenshot
		TakesScreenshot screenshotter;
		
		if (_wholeScreen) {
			screenshotter =((TakesScreenshot)m_driver);
		}
		else{
			screenshotter =((TakesScreenshot)_element);
		}
		
		File screenshot=screenshotter.getScreenshotAs(OutputType.FILE);
		String runTimeStamp = Utils.getRunTimeStamp();
		String ssTimeStamp = Utils.getTimestamp();
		File screenshotFile=new File("./screenshots/" + runTimeStamp + "/" + ssTimeStamp + ".png");
		FileUtils.copyFile(screenshot, screenshotFile);
		StepLogger.logStep("Screenshot with error saved to " + screenshotFile.getAbsoluteFile());
	}
	
	public static WebElement findElement(By _locator)
	{
		return m_driver.findElement(_locator);
	}

	public static List<WebElement> findElements(By _locator)
	{
		return m_driver.findElements(_locator);

	}
	
	public static void click(By _locator) {
		StepLogger.logStep("Clicking element by locator: " + _locator);
		findElement(_locator).click();
		StepLogger.logStep("finished clicking");
	}
	
	public static void click(WebElement _element) {
		StepLogger.logStep("Clicking element: " + formatElementString(_element.toString()));
		_element.click();
		StepLogger.logStep("finished clicking");
	}
	
	public static void sendKeys(By _locator, String _keys) {
		StepLogger.logStep("Sending keys: '" + _keys + "' to element with locator: " + _locator);
		findElement(_locator).sendKeys(_keys);
		StepLogger.logStep("finished sending keys");
	}
	
	public static void sendKeys(WebElement _element, String _keys) {
		StepLogger.logStep("Sending keys: '" + _keys + "' to element: " + formatElementString(_element.toString()));
		_element.sendKeys(_keys);
		StepLogger.logStep("finished sending keys");
	}
	
	private static String formatElementString(String _elementString) {
		String delimiter = "-> ";
		String output = _elementString.substring(_elementString.indexOf(delimiter) + delimiter.length());
		return output;
	}

	public static String GetTitle()
	{
		return m_driver.getTitle();
	}

	public static String GetPageSource()
	{
		return m_driver.getPageSource();
	}

	public static void Get(String _url)
	{
		m_driver.get(_url);
	}

	public static boolean isMaven()
	{
		return System.getProperty("java.class.path").contains("pom-framework-");
	}

	public static void ScrollToBottomOfPageWithJS()
	{
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) m_driver;
		javascriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}

	public static void ScrollToTopOfPageWithJS()
	{
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) m_driver;
		javascriptExecutor.executeScript("window.scrollTo(0, 0);");
	}

	public static void ScrollDownToElement(By locator)
	{
		WebElement element = findElement(locator);
		Actions actions = new Actions(m_driver);
		actions.moveToElement(element);
		actions.perform();
	}

	public static void ScrollDownToElementWithJS(By locator, int increment)
	{
		int currentY = 0;
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) m_driver;

		boolean found = false;

		while (!found)
		{
			try
			{
				WebElement element = findElement(locator);
				found = element.isDisplayed();
			} catch (Exception ex)
			{
				javascriptExecutor.executeScript("window.scrollTo(" + 0 + ", " + currentY + ");");
				currentY += increment;
			}
		}
	}

	public static void ScrollElementIntoView(WebElement element)
	{
		((JavascriptExecutor) m_driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public static void Seconds(int timeOutSeconds)
	{
		try
		{
			Thread.sleep(timeOutSeconds * 1000);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	public static void Seconds(double timeOutSeconds)
	{
		// new WebDriverWait(Selenium.GetDriver(WebDriverType.FIREFOX),
		// (int)(timeOutSeconds * 1000));
		try
		{
			Thread.sleep((int) (timeOutSeconds * 1000));
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	public static void MouseHoverByJavaScript(WebElement element)
	{
		String javaScript = "var evObj = document.createEvent('MouseEvents');"
				+ "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
				+ "arguments[0].dispatchEvent(evObj);";

		JavascriptExecutor js = (JavascriptExecutor) m_driver;
		js.executeScript(javaScript, element);

		// no way around this one. JavaScript executes asynchronously
		try
		{
			Thread.sleep(500);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}

	}

	public static By GetCSSSelectorFromAttribute(String attribute, String value, String tagName)
	{
		if (tagName.isEmpty())
		{
			tagName = "*";
		}
		// "tagName[attributeName='value']"
		String selectorString = tagName + "[" + attribute + "='" + value + "']";

		return By.cssSelector(selectorString);
	}

	public static By GeXPathSelectorFromAttribute(String attribute, String value, String tagName)
	{
		if (tagName.isEmpty())
		{
			tagName = "*";
		}
		// "//element[@attribute='value']"
		String selectorString = "//" + tagName + "[@" + attribute + "='" + value + "']";

		return By.xpath(selectorString);
	}

	public static By GetXpathFromElementContainingText(String text, String tagName)
	{
		if (tagName.isEmpty())
		{
			tagName = "*";
		}

		//// tagName[contains(text(), 'textInElement')]
		String selectorString = "//" + tagName + "[contains(text(), '" + text + "')]";

		return By.xpath(selectorString);
	}

	public static boolean VerifyTextPresentOnPage(String _expectedText)
	{
		String pageInformation = m_driver.getPageSource();
		if (pageInformation.contains(_expectedText))
		{
			return true;
		} else
		{
			return false;
		}
	}

	public static boolean VerifyPageTitle(String _expTitle)
	{
		String pageTitle;
		pageTitle = m_driver.getTitle();
		if (pageTitle.contains(_expTitle))
		{
			return true;
		} else
		{
			return false;
		}
	}

	public static boolean VerifyItemExists(WebElement _element)
	{
		if (_element.isDisplayed())
		{
			return true;
		} else
		{
			return false;
		}
	}
}
