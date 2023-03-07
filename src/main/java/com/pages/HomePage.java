package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.utils.ActionsUtil;
import com.qa.utils.Constants;
import com.qa.utils.ElementUtil;
import com.qa.utils.JavascriptUtil;
import com.qa.utils.WaitUtils;


public class HomePage
{
	private WebDriver driver;
	private ElementUtil elementUtil;
	private WaitUtils waitUtils;
	private JavascriptUtil javascriptUtil;
	private ActionsUtil actionsUtil;
	
	private By userNameBy = By.xpath("(//div[@class='login-panel']/div)[1]");
	private By loginPanelItemsBy = By.xpath("//div[@class='login-panel']//a[@class='dropdown-list']");
	private By logoutLinkBy = By.xpath("//div[@class='login-panel']//a[text()='Logout']");
	private By searchBy = By.xpath("//input[@name='q']");
	
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
		waitUtils = new WaitUtils(driver);
		javascriptUtil = new JavascriptUtil(driver);
		actionsUtil = new ActionsUtil(driver);
	}
	
	public String getUserName()
	{
		return elementUtil.getElementText(waitUtils.isVisibilityOfElementLocated(userNameBy, Constants.DEFAULT_TIME_OUT));
	}
	
	public int getLoginPanelItems()
	{
		
		actionsUtil.doMoveToElement(waitUtils.isVisibilityOfElementLocated(userNameBy, Constants.DEFAULT_TIME_OUT));
		return elementUtil.getElementsText(waitUtils.isVisibilityOfElementsLocated(loginPanelItemsBy, Constants.DEFAULT_TIME_OUT)).size();
	}
	
	public boolean isSearchExist()
	{
		return elementUtil.isElementDisplayed(searchBy);
	}
	
	public void doLogout()
	{
		actionsUtil.doMoveToElement(userNameBy);
		elementUtil.doClick(waitUtils.ifElementVisibleAndClickable(logoutLinkBy, Constants.DEFAULT_TIME_OUT));
		javascriptUtil.waitForPageLoaded();
	}
}
