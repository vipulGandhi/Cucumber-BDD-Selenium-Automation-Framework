package com.qa.utils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

public class AlertUtils
{
	
	WebDriver driver;
	Alert alert;
	
	public AlertUtils(WebDriver driver)
	{
		this.driver= driver;
	}

	public boolean isAlertPresent() 
	{ 
	    try 
	    { 
	    	alert = driver.switchTo().alert(); 
	        return true; 
	    }
	    catch (NoAlertPresentException Ex) 
	    { 
	        return false; 
	    }
	}
	
	public void acceptAlert()
	{
		if(isAlertPresent())
			alert.accept();
	}
	
	public void dismissAlert()
	{
		if(isAlertPresent())
		{
			System.out.println("Dismiss Alert ===");
			alert.dismiss();
		}
			
	}
	
	public String getAlertText()
	{
		if(isAlertPresent())
			return alert.getText();
		return null;
	}
	
	public void setAlertText(String alertText)
	{
		if(isAlertPresent())
			 alert.sendKeys(alertText);
	}
}
