package com.qa.utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


public class WaitUtils
{
	
	private WebDriver driver;
	
	ElementUtil elementUtil;
	
	public WaitUtils(WebDriver driver)
	{
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	// Wait for the element to be present inside the DOM
	// The element could be invisible inside the DOM as well
	// Equivalent to driver.findElement(byLocator) but with wait
	public WebElement isPresenceOfElementLocated(By byLocator, int waitForTimeinSeconds)
	{
		// Create object of WebdriverWait
		WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(waitForTimeinSeconds));
		
		// Wait for the specific time to get the element, once the element is located, assign it to webElement
		// No need to write driver.findElement(byLocator);
		// If element not present
			// [Exception] org.openqa.selenium.TimeoutException
			// [Exception] org.openqa.selenium.NoSuchElementException
		WebElement webElement = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
		
		return webElement;
	}
	
	public WebElement isPresenceOfElementLocated(By byLocator, int waitForTimeinSeconds, long pollingTime)
	{
		WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(waitForTimeinSeconds), Duration.ofMillis(pollingTime));		
		WebElement webElement = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
		return webElement;
	}
	
	// Wait for the element to be present inside the DOM
	// The element should be visible in the page as well
	// Equivalent to driver.findElement(byLocator) but with wait
	public WebElement isVisibilityOfElementLocated(By byLocator, int waitForTimeinSeconds)
	{
		WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(waitForTimeinSeconds));
		WebElement webElement = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
		return webElement;
	}
	
	public WebElement isVisibilityOfElementLocated(By byLocator, int waitForTimeinSeconds, long pollingTime)
	{
		WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(waitForTimeinSeconds), Duration.ofMillis(pollingTime));		
		WebElement webElement = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
		return webElement;
	}
	
	public List<WebElement> isVisibilityOfElementsLocated(By byLocator, int waitForTimeinSeconds)
	{
		WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(waitForTimeinSeconds));
		List<WebElement> webElements = webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(byLocator));
		return webElements;
	}
	
	public List<WebElement> isVisibilityOfElementsLocated(By byLocator, int waitForTimeinSeconds, long pollingTime)
	{
		WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(waitForTimeinSeconds), Duration.ofMillis(pollingTime));
		List<WebElement> webElements = webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(byLocator));
		return webElements;
	}
	
	public WebElement waitTillButtonIsClickable(By byLocator, int waitForTimeinSeconds)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitForTimeinSeconds));	
		WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(byLocator));
		return webElement;
	}
	
	public boolean ifUrlContainsText(String urlTextString, int waitForTimeinSeconds)
	{
		WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(waitForTimeinSeconds));		
		boolean ifURLContainsText = webDriverWait.until(ExpectedConditions.urlContains(urlTextString));
		return ifURLContainsText;
	}
	
	public boolean ifUrlIs(String urlString, int waitForTimeinSeconds)
	{
		WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(waitForTimeinSeconds));		
		boolean ifUrlIs = webDriverWait.until(ExpectedConditions.urlToBe(urlString));
		return ifUrlIs;
	}
	
	public boolean ifTitleContainsText(String titleTextString, int waitForTimeinSeconds)
	{
		WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(waitForTimeinSeconds));		
		boolean ifTitleContainsText = webDriverWait.until(ExpectedConditions.titleContains(titleTextString));
		return ifTitleContainsText;
	}
	
	public boolean ifTitleIs(String titleString, int waitForTimeinSeconds)
	{
		WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(waitForTimeinSeconds));		
		boolean ifTitleIs = webDriverWait.until(ExpectedConditions.titleIs(titleString));
		return ifTitleIs;
	}
	
	public Alert isAlertPresent(int waitForTimeinSeconds)
	{
		WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(waitForTimeinSeconds));		
		Alert alert = webDriverWait.until(ExpectedConditions.alertIsPresent());
		return alert;
	}
	
	// Wait for the frame to be present
	// Switch to frame: Similar to "driver.switchTo().frame("");"
	public WebDriver isFrameAvailableAndSwitchToIt(By byLocator, int waitForTimeinSeconds)
	{
		WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(waitForTimeinSeconds));		
		WebDriver driver = webDriverWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(byLocator));
		return driver;
	}
	
	// Check if element is visible and enabled for click
	public WebElement ifElementVisibleAndClickable(By byLocator, int waitForTimeinSeconds)
	{
		WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(waitForTimeinSeconds));		
		WebElement webElement = webDriverWait.until(ExpectedConditions.elementToBeClickable(byLocator));
		return webElement;
	}
	
	// Fluent Wait
	// Create the object of FluentWait class referred by parent interface reference variable
	// Parent interface contains only until() method
	// Wait<WebDriver>: Apply the wait to WebDriver
	// Let the method of FluentWait class "withTimeout()", "pollingEvery()", "ignoring()" and "withMessage" be attached to the object
	// Exceptions are not passed as parameter
		// Every method is returning this keyword - Builder Pattern
	//Wait<WebDriver> wait = new FluentWait<WebDriver>(driver);
	public WebElement isPresenceOfElementLocated_FluentWait(By byLocator, int waitForTimeinSeconds, long pollingTime)
	{
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(waitForTimeinSeconds))
				.pollingEvery(Duration.ofMillis(500))
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class)
				.withMessage(Errors.ELEMENT_NOT_FOUND_EXCEPTION_MESSAGE);

		WebElement webElement = wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
		return webElement;
	}
	
	// WebDriverWait class is a child of FluentWait class
		// Every actions performed with FluentWait class can also be performed with WebDriverWait class
	// Selenium has defined one class WebDriverWait() as the child of FluentWait() class
		// WebDriverWait() contains no method of it's own
		// WebDriverWait() is recommended to use over FluentWait()
		// Prefer using WebDriverWait() - With or without fluent wait methods 
	public WebElement isPresenceOfElementLocated_FluentWait_COPY(By byLocator, int waitForTimeinSeconds, long pollingTime)
	{
		WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(waitForTimeinSeconds));
		
		webDriverWait
		.pollingEvery(Duration.ofMillis(pollingTime))
		.ignoring(NoSuchElementException.class)
		.ignoring(StaleElementReferenceException.class)
		.withMessage(Errors.ELEMENT_NOT_FOUND_EXCEPTION_MESSAGE);
		
		WebElement webElement = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
		return webElement;
	}
	
	public WebElement customWait(By byLocator, int maxTimeoutToWait, Long pollingIntervalInMilliSeconds)
	{	
		// Let the Webelement be null
		WebElement element = null;
		
		int attemptCount = 0;
		
		// Perform retry attempts
		while (attemptCount < maxTimeoutToWait)
		{
			try
			{
				element = elementUtil.getElement(byLocator);
				break;
			}
			
			catch (NoSuchElementException e)
			{
				System.out.println("Element is not found in attempt: " + attemptCount);
				try
				{
					Thread.sleep(pollingIntervalInMilliSeconds);
				}
				catch (InterruptedException e1)
				{
					e1.printStackTrace();
				}
			}
			attemptCount++;
		}
		
		if(element == null)
		{
			try
			{
				throw new Exception("NoSuchElementFound");
			}
			catch (Exception e)
			{
				System.out.println("Self Exception - Element not found within " + maxTimeoutToWait + " seconds");
			}
			
		}
		return element;
	}
}
