package com.stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.pages.LoginPage;
import com.qa.factory.DriverFactory;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

/* Step Definition class/file DOESNOT contain
 * driver elements/objects
 * By locators
 */
public class LoginPageSteps {
	
	//create a private obj of LoginPage class, since this obj is used ONLY in this LoginPageSteps class
	//private LoginPage loginPage = new LoginPage(DriverFactory.getDriver()); //get runtime driver and assign to LoginPage object
	
	private LoginPage loginPage;
	private static String PageTitle; //make this Static to use title in multiple methods in this class
	private ChromeDriver driver;
	
	@Given("User is on login page")
	public void user_is_on_login_page() {
		
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Avinav\\Downloads\\chromedriver.exe");
		driver = new ChromeDriver();
		loginPage = new LoginPage(driver);
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		
//		//launch URL
//		try {			
//		DriverFactory.getDriver().get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
//	}catch (RuntimeException  e) {
//		
//		e.printStackTrace();
//	}
}
	@When("user gets the title of the page")
	public void user_gets_the_title_of_the_page() {
		
		PageTitle = loginPage.getLoginPageTitle();
	    System.out.println("Page title is: "+PageTitle);
	}
	
	@Then("page title should be {string}")
	public void page_title_should_be(String expectedTitle) {
	    
		Assert.assertTrue(PageTitle.contains(expectedTitle));
	}

	@Then("forgot password link should be displayed")
	public void forgot_password_link_should_be_displayed() {

		Assert.assertTrue(loginPage.isForgotPaswordLinkExists()); //ensure forgot password link returns true
	}

	@When("User enters username {string}")
	public void user_enters_username(String userName) {
	    loginPage.enterUserName(userName); //enter username defined in feature file
	}

	@When("User enters password {string}")
	public void user_enters_password(String password) {
	    loginPage.enterPassword(password); //enter password defined in feature file
	}
	
	@When("User clicks on login button")
	public void user_clicks_on_login_button() {
	    loginPage.clickOnLogin();
	}


}
