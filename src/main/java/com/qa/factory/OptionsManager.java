package com.qa.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager
{
	private Properties properties;
	private ChromeOptions chromeOptions;
	private FirefoxOptions firefoxOptions;
	
	
	public OptionsManager(Properties properties)
	{
		this.properties = properties;
	}
	
	public ChromeOptions getChromeOptions()
	{
		chromeOptions = new ChromeOptions();
		
		if(Boolean.parseBoolean(properties.getProperty("headless").trim().toLowerCase()))
			chromeOptions.addArguments("--headless");
		
		if(Boolean.parseBoolean(properties.getProperty("incognito").trim().toLowerCase()))
			chromeOptions.addArguments("--incognito");
		
		// block popups
		chromeOptions.addArguments("disable-popup-blocking");
		
		return chromeOptions;
	}
	
	public FirefoxOptions getFirefoxoptions()
	{
		firefoxOptions = new FirefoxOptions();
		
		if(Boolean.parseBoolean(properties.getProperty("headless").trim().toLowerCase()))
			firefoxOptions.addArguments("--headless");
		
		if(Boolean.parseBoolean(properties.getProperty("incognito").trim().toLowerCase()))
			firefoxOptions.addArguments("--incognito");
		
		return firefoxOptions;
	}
}
