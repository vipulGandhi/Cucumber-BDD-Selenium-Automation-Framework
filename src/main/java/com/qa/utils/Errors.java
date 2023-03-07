// Maintaining all the error messages in a singl class file
// Advantages
	// Reusability
		// For all the errors across the project, the same message will be displayed
		// We have created a single string in string pool
	/* During code coverage/ static analysis with any code monitoring tool (eg. https://www.sonarqube.org/,
	 	https://www.baeldung.com/jacoco), the tool checks for the code quality (for duplicate code blocks etc.)
	 	and share a detailed report.
	 */
	

package com.qa.utils;

public class Errors
{
	public static final String ELEMENT_NOT_FOUND_EXCEPTION_MESSAGE = "Element not found ...";
	
	public static final String IMAGE_COUNT_ERROR_MESSAGE = "Image count should be greater than 0";

}
