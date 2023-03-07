// Problems with running multiple tests(classes) in parallel with one driver instance
	// Threads are dependent on each other
	// Suppose a thread execution is taking more time, a time-out may occur on a different thread which is waiting for the driver
// If we run testNG.xml command:  <suite name="Suite" parallel="test" thread-count="2">
	// 2 browsers will be launched executing 2 different threads
	// By default, webdriver is not thread safe
	// To make webdriver threadsafe, We use Java ThreadLocal class

package com.qa.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.google.common.base.Strings;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory
{
	public WebDriver driver;
	public Properties properties;
	public OptionsManager optionsManager;
	public static String highlightElement;
	
	// Threads to get the local copy of WebDriver object
	public static ThreadLocal<WebDriver> threadLocal = new ThreadLocal<WebDriver>();
	
	public WebDriver initWebDriver(Properties properties)
	{
		
		String browserName = properties.getProperty("browser").trim().toLowerCase();
		String pageURLString = properties.getProperty("url");
		
		highlightElement = properties.getProperty("highlightElement");
		
		optionsManager = new OptionsManager(properties);
				
		// Initialize the driver
		if(browserName.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			
			DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
			desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, optionsManager.getChromeOptions());

			if(Boolean.parseBoolean(properties.getProperty("remote")))
			{
				try
				{
					threadLocal.set(new RemoteWebDriver(new URL(properties.getProperty("huburl")), desiredCapabilities));
				}
				catch (MalformedURLException e)
				{
					e.printStackTrace();
				}
			}
			else
			{
				//driver = new ChromeDriver(optionsManager.getChromeOptions());
				threadLocal.set(new ChromeDriver(optionsManager.getChromeOptions()));
			}
		}
		else if(browserName.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			
			DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
			desiredCapabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, optionsManager.getFirefoxoptions());
			
			if(Boolean.parseBoolean(properties.getProperty("remote")))
			{
				try
				{
					threadLocal.set(new RemoteWebDriver(new URL(properties.getProperty("huburl")), desiredCapabilities));
				}
				catch (MalformedURLException e)
				{
					e.printStackTrace();
				}
			}
			else
			{
				//driver = new FirefoxDriver(optionsManager.getFirefoxoptions());
				threadLocal.set(new FirefoxDriver(optionsManager.getFirefoxoptions()));
			}
		}
		else if(browserName.equals("safari"))
		{
			//driver = new SafariDriver();
			threadLocal.set(new SafariDriver());
		}
		else
		{
			System.out.println("Please pass the correct browser name: " + browserName);
		}
		
		// Preconditions
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		
		// Launch the browser
		//getPageUrl(pageURLString);
		
		return getDriver();
	}

	// Return local copy of WebDriver
	public static synchronized WebDriver getDriver()
	{
		return threadLocal.get();
	}
	
	public String getScreenshot()
	{
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/FailedTestScreenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);

		try
		{
			FileUtils.copyFile(srcFile, destination);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return path;
	}
	
	public void getPageUrl(String url)
	{
		try
		{
			if(url == null)
			{
				throw new Exception("url is null ...");
			}
		}
		catch (Exception e)
		{
		}

		getDriver().get(url);
	}
	
	public void getPageUrl(URL url)
	{
		try
		{
			if(url == null)
			{
				throw new Exception("url is null ...");
			}
		}
		catch (Exception e)
		{
		}

		getDriver().navigate().to(url);
	}
	
	public void getPageUrl(String baseUrl, String endpoint)
	{
		try
		{
			if(baseUrl == null)
			{
				throw new Exception("baseUrl is null ...");
			}
		}
		catch (Exception e)
		{
		}

		getDriver().get(baseUrl + "/" + endpoint);
	}
	
	public void getPageUrl(String baseUrl, String endpoint, String queryParameter)
	{
		try
		{
			if(baseUrl == null)
			{
				throw new Exception("baseUrl is null ...");
			}
		}
		catch (Exception e)
		{
		}

		getDriver().get(baseUrl + "/" + endpoint + "?" + queryParameter);
	}
	
	// https://username:password@URL
	public void getPageUrlWithBrowserAuthentication(String url, String username, String password)
	{
		try
		{
			if(url == null)
			{
				throw new Exception("url is null ...");
			}
		}
		catch (Exception e)
		{
		}
		
		String urlAuthenticationString = "https://" + username + ":" + password + "@" + url.split("//")[1];

		getDriver().get(urlAuthenticationString);
	}
}
