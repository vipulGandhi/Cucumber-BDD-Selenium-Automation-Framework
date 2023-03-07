package MyAppHooks;

import java.util.Properties;

import org.junit.FixMethodOrder;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.qa.factory.DriverFactory;
import com.qa.utils.ConfigReader;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ApplicationHooks
{
	
	DriverFactory driverFactory;
	WebDriver driver;
	ConfigReader configReader;
	Properties properties;

	
	// @Before method executes before every scenario
	@Before(order = 0)
	public void getProperty()
	{
		configReader = new ConfigReader();
		properties = configReader.initProperties();
	}
	
	@Before(order = 1)
	public void launchBrowser()
	{
		driverFactory = new DriverFactory();
		driver = driverFactory.initWebDriver(properties);
	}
	
	
	@After(order = 2)
	public void quitBrowser()
	{
		driver.quit();
	}
	
	@After(order = 1)
	public void tearDown(Scenario scenario)
	{
		if(scenario.isFailed())
		{
			String screenShotName = scenario.getName().replace(" ", "_");
			byte[] sourcePath = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(sourcePath, "image/png", screenShotName);
		}
	}
}
