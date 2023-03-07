package stepdefinitions;

import java.util.List;
import java.util.Map;

import org.junit.Assert;

import com.pages.HomePage;
import com.pages.LandingPage;
import com.qa.factory.DriverFactory;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

public class LandingPageSteps
{
	
	private LandingPage landingPage = new LandingPage(DriverFactory.getDriver());
	private HomePage homePage;
	
    @Given("User is on Landing Page {string}")
    public void user_is_on_landing_page(String pageUrl)
    {
    	DriverFactory.getDriver().get(pageUrl);
    }
    
    @Then("Page title should contain {string}")
    public void page_title_should_contain(String pageTitleSubString)
    {
    	Assert.assertTrue(landingPage.isTitleSubstringPresent(pageTitleSubString));
    }

    @Then("Page url should contain {string}")
    public void page_url_should_contain(String urlSubString)
    {
    	System.out.println("Landing Page URL");
    }
    
    @When("User sign in with valid username and password")
    public void user_sign_in_with_valid_username_and_password(DataTable dataTable)
    {
    	List<Map<String, String>> credentialListMap = dataTable.asMaps(String.class, String.class);
    	String userName = credentialListMap.get(0).get("UserName");
    	String password = credentialListMap.get(0).get("Password");
    	homePage = landingPage.doSignIn(userName, password);
    }
    
    @Then("User is on the dashboard with the username {string}")
    public void user_is_on_the_dashboard_page_with_the_username(String userName)
    {
    	Assert.assertEquals(homePage.getUserName(), userName);
    }
}
