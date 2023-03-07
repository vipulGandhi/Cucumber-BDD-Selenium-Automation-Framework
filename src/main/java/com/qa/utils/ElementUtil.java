package com.qa.utils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.qa.factory.DriverFactory;

import net.bytebuddy.asm.Advice.Return;

//Manually import static packages
//Contains static methods
import static org.openqa.selenium.support.locators.RelativeLocator.*;

public class ElementUtil
{
	private WebDriver driver;
	private JavascriptUtil javascriptUtil;
	
	
	public ElementUtil(WebDriver driver)
	{
		this.driver = driver;
		javascriptUtil = new JavascriptUtil(driver);
	}
	
	public WebElement getElement(By byLocator)
	{
		WebElement webElement = driver.findElement(byLocator);
		
		if(Boolean.parseBoolean(DriverFactory.highlightElement.trim().toLowerCase()))
		{
			javascriptUtil.flashWebElement(webElement);
		}
		return webElement;
	}
	
	public List<WebElement> getElements(By byLocator)
	{
		return driver.findElements(byLocator);
	}
	
	public List<String> getElementsText(By byLocator)
	{
		List<WebElement> allElements =  getElements(byLocator);
		List<String> elementTextList = new ArrayList<String>();
		
		for (WebElement eWebElement : allElements)
		{
			if(!eWebElement.getText().isBlank())
			{
				elementTextList.add(eWebElement.getText());
			}
		}
		
		return elementTextList;
	}
	
	public List<String> getElementsText(List<WebElement> allElements)
	{
		//List<WebElement> allElements =  getElements(byLocator);
		List<String> elementTextList = new ArrayList<String>();
		
		for (WebElement eWebElement : allElements)
		{
			if(!eWebElement.getText().isBlank())
			{
				elementTextList.add(eWebElement.getText());
			}
		}
		
		return elementTextList;
	}
	
	public void SelectWebelementFromList(List<WebElement> webElements, String elementText)
	{
		for (WebElement eWebElement : webElements)
		{
			if(eWebElement.getText().toLowerCase().trim().contains(elementText.toLowerCase().trim()))
			{
				eWebElement.click();
				break;
			}
		}
	}
	
	public void SelectWebelementsFromList(List<WebElement> webElements, List<String> elementText)
	{
		while (elementText.size() >  0)
		{
			for (WebElement eWebElement : webElements)
			{
				if(elementText.contains(eWebElement.getText().toLowerCase().trim()))
				{
					eWebElement.click();
					elementText.remove(eWebElement.getText().toLowerCase().trim());
				}
			}
		}
	}
	
	 public void clearTextField(WebElement webElement)
	 {
		 // webElement.clear();
		 while(!webElement.getAttribute("value").equals(""))
		 {
			 webElement.sendKeys(Keys.BACK_SPACE);
		 }
	}
	
	public void doSendKeys(By byLocator, String value)
	{
		clearTextField(getElement(byLocator));
		getElement(byLocator).sendKeys(value);
	}
	
	public void doSendKeys(WebElement webElement, String value)
	{
		clearTextField(webElement);
		webElement.sendKeys(value);
	}
	
	public String getElementText(By byLocator)
	{
		return getElement(byLocator).getText();
	}
	
	public String getElementText(WebElement webElement)
	{
		return webElement.getText();
	}
	
	public void doSendKeys(String locatorType, String locatorValue, String value)
	{
		getElement(getBy(locatorType, locatorValue)).sendKeys(value);
	}
	
	public void doClick(String locatorType, String locatorValue)
	{
		getElement(getBy(locatorType, locatorValue)).click();;
	}
	
	public void doClick(By byLocator)
	{
		getElement(byLocator).click();
	}
	
	public void doClick(WebElement webElement)
	{
		webElement.click();
	}
	
	public void pressEnter(By byLocator)
	{
		getElement(byLocator).sendKeys(Keys.ENTER);
	}
	
	public void pressEnter(WebElement webElement)
	{
		webElement.sendKeys(Keys.ENTER);
	}
	
	public void doSubmit(By byLocator)
	{
		getElement(byLocator).submit();;
	}
	
	public boolean isElementDisplayed(By byLocator)
	{
		if(getElements(byLocator).size() > 0)
		{
			return getElement(byLocator).isDisplayed();
		}
		return false;
		
		//OR
		//		try
		//		{
		//			getElement(byLocator).isDisplayed();
		//			return true;
		//		}
		//		catch (Exception e)
		//		{
		//			return false;
		//		}
	}
	
	public boolean isElementDisplayed(String locatorType, String locatorValue)
	{
		return getElement(getBy(locatorType, locatorValue)).isDisplayed();
	}
	
	public String getElementAttributeValue(By byLocator, String attributeName)
	{
		return getElement(byLocator).getAttribute(attributeName);
	}
	
	public List<String> getElementsAttributeValue(By byLocator, String attributeName)
	{
		List<WebElement> allElements =  getElements(byLocator);
		List<String> elementAttributeTextList = new ArrayList<String>();
		
		for (WebElement eWebElement : allElements)
		{
			if(!eWebElement.getAttribute(attributeName).isBlank())
			{
				elementAttributeTextList.add(eWebElement.getAttribute(attributeName));
			}	
		}
		
		return elementAttributeTextList;
	}
	
	public List<String> getElementsAttributeValue(List<WebElement> webElements, String attributeName)
	{
		List<String> elementAttributeTextList = new ArrayList<String>();
		
		for (WebElement eWebElement : webElements)
		{
			if(!eWebElement.getAttribute(attributeName).isBlank())
			{
				elementAttributeTextList.add(eWebElement.getAttribute(attributeName));
			}	
		}
		
		return elementAttributeTextList;
	}
	
	public void clickLinkFromList(By byLocator, String linkText)
	{
		List<WebElement> allWebElements = getElements(byLocator);
		for (WebElement webElement : allWebElements)
		{
			if(webElement.getText().equals(linkText))
			{
				webElement.click();
				break;
			}
		}
	}
	
	public int getElementsCount(By byLocator)
	{
		return getElements(byLocator).size();
	}
	
	public int getElementsCount(List<WebElement> webElements)
	{
		return webElements.size();
	}
	
	public void printAllStringElements(List<String> allStringElements)
	{
		for (String stringElement : allStringElements)
		{
			System.out.println(stringElement);
		}
	}
	
	public boolean isElementPresent(By byLocator)
	{
		return (getElementsCount(byLocator) == 0 ? false : true);
	}
	
	// Select By Index method
	// Use Cases
			// Dropdown where the items are fixed
			// Days(1 - 31), Months(1 - 12) dropdown
	public void doDropdownSelectByIndex(By byLocator, int index)
	{
		// No default constructor available
		// Override constructor takes "Webelement" parameter
		// "selectByIndex" is a non static method of "Select" class as we can access it by class object reference
		Select selectByIndexSelect = new Select(getElement(byLocator));
		selectByIndexSelect.selectByIndex(index);
	}
	
	// Select By Visible Text (Select by element text)
	public void doDropdownSelectByVisibleText(By byLocator, String visibleText)
	{
		Select selectByIndexSelect = new Select(getElement(byLocator));
		selectByIndexSelect.selectByVisibleText(visibleText);
	}
	
	// Select By Value (select by the key of element's value attribute)
	public void doDropdownSelectByValue(By byLocator, String value)
	{
		Select selectByIndexSelect = new Select(getElement(byLocator));
		selectByIndexSelect.selectByValue(value);
	}
	
	public List<WebElement> getAllSelectDropdownElements(By byLocator)
	{
		Select selectDropdown = new Select(getElement(byLocator));
		return selectDropdown.getOptions();
	}
	
	public int getAllSelectDropdownElementsCount(By byLocator)
	{
		return getAllSelectDropdownElements(byLocator).size();
	}
	
	public boolean isElementTextPresentInSelectDropdown(By byLocator, String elementText)
	{
		boolean isPresent = false;
		for (WebElement webElement : getAllSelectDropdownElements(byLocator))
		{
			if(webElement.getText().equals(elementText))
			{
				isPresent = true;
				break;
			}
		}
		return isPresent;
	}
	
	public void selectDropdownValueWithoutSelectClass(By byLocator, String elementText)
	{
		for (WebElement webElement : getElements(byLocator))
		{
			if(webElement.getText().equals("elementText"))
			{
				webElement.click();
				break;
			}
		}
	}
	
	// Find the webelement "webElementToFind" which is to the right of "handleWebElement" on the web page
	public WebElement getWebElementToTheRightOf(By webElementToFind, By handleWebElement)
	{
		return driver.findElement(with(webElementToFind).toRightOf(handleWebElement));
	}
	
	// Find the webelement "webElementToFind" which is to the left of "handleWebElement" on the web page
	public WebElement getWebElementToTheLeftOf(By webElementToFind, By handleWebElement)
	{
		return driver.findElement(with(webElementToFind).toLeftOf(getElement(handleWebElement)));
	}
	
	// Find the webelement "webElementToFind" which is above "handleWebElement" on the web page
	public WebElement getwebElementAbove(By webElementToFind, By handleWebElement)
	{
		return driver.findElement(with(webElementToFind).above(getElement(handleWebElement)));
	}
	
	// Find the webelement "webElementToFind" which is below "handleWebElement" on the web page
	public WebElement getwebElementBelow(By webElementToFind, By handleWebElement)
	{
		return driver.findElement(with(webElementToFind).below(getElement(handleWebElement)));
	}
	
	
	
	private By getBy(String locatorType, String locatorValue)
	{
		// Default value of By class is null
		By locator = null;
		
		switch (locatorType.toLowerCase())
		{
		case "id":
			locator = By.id(locatorValue);
			break;
		case "name":
			locator = By.name(locatorValue);
			break;
		case "classname":
			locator = By.className(locatorValue);
			break;
		case "cssselector":
			locator = By.cssSelector(locatorValue);
			break;
		case "xpath":
			locator = By.xpath(locatorValue);
			break;
		case "tagname":
			locator = By.tagName(locatorValue);
			break;
		case "linktext":
			locator = By.linkText(locatorValue);
			break;
		case "partiallinktext":
			locator = By.partialLinkText(locatorValue);
			break;
		
		default:
			System.out.println("Please pass correct locator");
			break;
		}
		
		return locator;
	}
}
