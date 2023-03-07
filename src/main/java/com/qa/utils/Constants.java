package com.qa.utils;

import java.util.ArrayList;
import java.util.List;

public class Constants
{
	
	//--------------------------------------------------------------------------------------
	// Global
	public static final int DEFAULT_TIME_OUT = 150000;
	public static final String SCROLL_SPEED = "2";
	
	//--------------------------------------------------------------------------------------
	// Excel Sheet Names
	public static final String SHEET_NAME_SEARCH_ITEMS = "SearchItems";  
	
	// Landing Page
	public static final String lANDING_PAGE_TITLE_FRACTION = "Lenskart.com";  
	public static final String lANDING_PAGE_URL_FRACTION = "lenskart.com";
	public static final String EMAIL_ERROR_MESSAGE = "Account Does not exists..";
	
	//--------------------------------------------------------------------------------------
	// Home Page
	public static List<String> loginPanelItems()
	{
		List<String> loginPanelItemsString = new ArrayList<String>();
		
		loginPanelItemsString.add("My Orders");
		loginPanelItemsString.add("My Prescription");
		loginPanelItemsString.add("My Store Credit");
		loginPanelItemsString.add("My Dittos");
		loginPanelItemsString.add("Account Information");
		loginPanelItemsString.add("Logout");
		
		return loginPanelItemsString;
	}
}
