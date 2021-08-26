package com.qa.Hooks;

import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.qa.factory.DriverFactory;
import com.qa.util.ConfigReader;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

//Class to launch browser (before each scenario)
public class ApplicationHooks {
	/* define private variables to access DriverFactory class and WD object
	 * private because these are specific to only ApplicationHooks class
	 */
	
	private DriverFactory driverFactory;
	private WebDriver driver;
	private ConfigReader configReader;
	Properties prop;
	String browserName;
	
	@Before(order = 0)
	public void getProperty() {
		configReader = new ConfigReader();
		prop = configReader.init_prop();
		
	}
	
	@Before(order = 1)
	public void launchBrowser() {
		String browserName = prop.getProperty("browser"); //read value of 'browser' from config.properties
		driverFactory = new DriverFactory();
		driver = driverFactory.initDriver(browserName); //store DF object in WD obj
	}
	/* @After annotations are processed in the REVERSE order
	 * @After(1) is initialized first and then @After(0)
	 */
	@After(order = 0)
	public void quitBrowser() {
		driver.quit();	
	}
	
	@After(order = 1)
	public void tearDown(Scenario scenario) {
		if(scenario.isFailed()) {
			//take screenshot:
		String screenshotName = scenario.getName().replaceAll(" ", "_"); //replace any spaces with an '_'
		byte[] sourcePath = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES); //store screenshot under sourcePath
		scenario.attach(sourcePath, "image/png", screenshotName);
		}
	}
}
