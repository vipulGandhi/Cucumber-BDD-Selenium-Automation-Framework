package com.qa.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;

public class PageUtils
{
	private WebDriver driver;
	
	private String mainWindowHandleString;
	private Set<String> allWindowHandleIdsSet;
	private List<String> allWindowHandleIdsList;
	
	private String parentWindowHandleString; 
	private String childWindowHandleString;
	
	public PageUtils(WebDriver driver)
	{
		this.driver = driver;
		allWindowHandleIdsList = new ArrayList<String>();
	}
	
	
	private void getAllWindowHandles()
	{
		mainWindowHandleString = driver.getWindowHandle();
		allWindowHandleIdsSet =  driver.getWindowHandles();
		allWindowHandleIdsList.addAll(allWindowHandleIdsSet);
		
		parentWindowHandleString = allWindowHandleIdsList.get(0);
		childWindowHandleString = allWindowHandleIdsList.get(1);
	}
	
	private  int getOpenWindowsCount()
	{
		return allWindowHandleIdsList.size();
	}

	/**
	 * This method is applicable when only one child window is open
	 */
	public void switchToChildWindow()
	{
		getAllWindowHandles();
		if(getOpenWindowsCount() > 1)
		{
			driver.switchTo().window(childWindowHandleString);
		}
	}
	
	public void switchToParentWindow()
	{
		getAllWindowHandles();
		driver.close();
		driver.switchTo().window(parentWindowHandleString);
	}
}
