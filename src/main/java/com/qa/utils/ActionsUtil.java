// While using Actions class, always use build() and perform() methods.
// If there is a chain of actions, first build the actions then perform the actions
// If there is a single action, we can use only perform() as well

// User actions
	// Right click / Context Click
	// Double click
	// Drag drop
	// Move to specific element

// When to use these methods
	// When the webelement is visible on the page but selenium is not able to interact with the element [ElementNotInteractableException]
		// When the webelement in question is overlapped by some other webelement


package com.qa.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionsUtil
{
	Actions actions;
	ElementUtil elementUtil;
	
	public ActionsUtil(WebDriver driver)
	{
		// Pass the driver element in class constructor
		actions = new Actions(driver);
		elementUtil = new ElementUtil(driver);
	}
	
	public void doRightClick(By byLocator)
	{
		actions
		.contextClick(elementUtil.getElement(byLocator))
		.build()
		.perform();
	}
	
	public void doDragAndDrop(By byDraggableElement, By byDroppableElement)
	{
		// Method - 01
		// Observe Builder pattern
			// Every method is returning same current class object
		//actions
		//.clickAndHold(elementUtil.getElement(byDraggableElement))
		//.moveToElement(elementUtil.getElement(byDroppableElement))
		//.release()
		//.build()
		//.perform();
	
	// Method - 02
	actions
		.dragAndDrop(elementUtil.getElement(byDraggableElement), elementUtil.getElement(byDroppableElement))
		.build()
		.perform();
	}
	
	public void doMoveToElement(By byLocator)
	{
		actions
		.moveToElement(elementUtil.getElement(byLocator))
		.build()
		.perform();
	}
	
	public void doMoveToElement(WebElement webElement)
	{
		actions
		.moveToElement(webElement)
		.build()
		.perform();
	}
	
	public void doSendKeys(By byLocator, String value)
	{
		actions
		.sendKeys(elementUtil.getElement(byLocator), value)
		.build()
		.perform();
	}
	
	public void doClick(By byLocator)
	{
		actions
		.click(elementUtil.getElement(byLocator))
		.build()
		.perform();
	}
	
	public void inputKeyboardKeysDown(WebElement webElement, CharSequence inputKeys)
	{
		actions.keyDown(webElement, inputKeys)
		.build()
		.perform();
	}
	
}
