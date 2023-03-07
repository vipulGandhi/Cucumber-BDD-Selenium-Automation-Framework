package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.utils.Constants;
import com.qa.utils.ElementUtil;
import com.qa.utils.JavascriptUtil;
import com.qa.utils.WaitUtils;

public class LandingPage
{
	private WebDriver driver;
	private ElementUtil elementUtil;
	private WaitUtils waitUtils;
	private JavascriptUtil javascriptUtil;
	
	// By Locators
	private By signInLinkBy = By.xpath("//span[text()='Sign In']");
	private By pushNotificationDismissBy = By.xpath("//button[text()='No thanks']");
	private By emailBy = By.xpath("//input[@name='emailOrPhone']");
	private By passwordBy = By.xpath("//input[@title='password']");
	private By proceedButtonBy = By.xpath("//div[@class='auth-modal-login-container ']//span[text()='Sign In']");

	
	// Constructor
	public LandingPage(WebDriver driver)
	{
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
		waitUtils = new WaitUtils(driver);
		javascriptUtil = new JavascriptUtil(driver);
	}
	
	public boolean isTitleSubstringPresent(String pageTitleSubString)
	{
		return waitUtils.ifTitleContainsText(pageTitleSubString, Constants.DEFAULT_TIME_OUT);
	}
	
	public boolean isUrlSubstringPresent(String urlSubString)
	{
		return waitUtils.ifUrlContainsText(urlSubString, Constants.DEFAULT_TIME_OUT);
	}
	
	
	
	public HomePage doSignIn(String email, String password)
	{
		if(elementUtil.isElementDisplayed(pushNotificationDismissBy))
		{
			elementUtil.doClick(pushNotificationDismissBy);
		}

		elementUtil.doClick(waitUtils.ifElementVisibleAndClickable(signInLinkBy, Constants.DEFAULT_TIME_OUT));

		elementUtil.doSendKeys(waitUtils.ifElementVisibleAndClickable(emailBy, Constants.DEFAULT_TIME_OUT), email);

		elementUtil.doClick(proceedButtonBy);

		elementUtil.doSendKeys(waitUtils.ifElementVisibleAndClickable(passwordBy, Constants.DEFAULT_TIME_OUT), password);
		elementUtil.doClick(proceedButtonBy);

		javascriptUtil.waitForPageLoaded();
		
		return new HomePage(driver);
	}
}
