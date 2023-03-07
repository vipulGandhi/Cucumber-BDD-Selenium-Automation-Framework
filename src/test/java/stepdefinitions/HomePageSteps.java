package stepdefinitions;

import java.util.List;
import java.util.Map;

import com.pages.HomePage;
import com.pages.LandingPage;
import com.qa.factory.DriverFactory;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.Assert; 

public class HomePageSteps
{
	private LandingPage landingPage = new LandingPage(DriverFactory.getDriver());
	private  HomePage homePage;
	
	private int loginPanelItemCount;
	
    @And("User sign in with valid username {string} and password {string}")
    public void user_has_logged_in_to_the_application(String userName, String password)
    {
    	homePage = landingPage.doSignIn(userName, password);
    }
	
    @When("User gets the count of login panel items")
    public void user_gets_the_count_of_login_panel_items()
    {
    	loginPanelItemCount = homePage.getLoginPanelItems();
    }

    @Then("Login panel item count should be {string}")
    public void login_panel_item_count_should_be(String itemCount)
    {
    	Assert.assertEquals(loginPanelItemCount, Integer.parseInt(itemCount));
    }
}
