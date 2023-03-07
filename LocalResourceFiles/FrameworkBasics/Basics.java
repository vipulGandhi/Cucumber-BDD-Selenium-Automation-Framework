// Feature Layer
	// For every page, define seperate feature files
	// All feture files will be mapped to their respective step-definition files

// Step Definition Layer
	// For every feature file, create respective step-definition file
	// Every scenario in the step definition will be having some pre, postcondition
		// Launch Browser
		// Enter URL
		// Delete cookies
		// Quit browser
	// @Given, @When, @Then methods will call page actions of Page Layer

// Hooks Layer
	// Define Pre, Post conditions
	// @Before, @After, @BeforeStep, @AfterStep

// Page Layer
	// Follow Single Responsibilitu Principle(SRP)
	// For every web page, define a seperate page class
		// private By locators
		// public page actions
			// Page actions will be using the util methods from Util layer
		// class constructor(driver)

// Util Layer
	// Element Util
	// DB Util etc.

// DriverFactory.java Class
	// Initialize the driver with threadlocal class
		// The method will be called from @Before hook method

// Config.properties

// ConfigReader.java
	// Interact with Config.propertiesto get the properties
	// The method will be called from @Before hook method
	// InitProperty()
		// return property

// TestRunner.java
	// JUnit class
	// @CucumberOptions()

// Maven
	// pom.xml
		// Dependencies
			// WebDriver, WebDriverManager, Cucumber, JUnit
		// Plugins
			// Compiler, Surefire, failsafe(for feature file parallel execution)

// Reports
	// Json
	// XML
	// Cucumber HTML report
	// Extent (Spark) report
		// HTML Report
		// pdf report

// ====================================================================

// resources
	// src/test/resources
		// Feature Layer
		// config.property
		// cucumber.property (for HTML report)
		// extent.property
		// extent-config.xml
