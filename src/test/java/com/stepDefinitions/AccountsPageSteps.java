package com.stepDefinitions;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeDriver;

import com.pages.AccountsPage;
import com.pages.LoginPage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import junit.framework.Assert;

public class AccountsPageSteps {
	
	private LoginPage loginPage;
	private ChromeDriver driver;
	private AccountsPage accountsPage;

	@Given("user has already logged in to application")
	public void user_has_already_logged_in_to_application(DataTable credentialsTable) {
		
		List<Map<String,String>> credentialList = credentialsTable.asMaps(); //read datatable from feature file and store in List of Map of String as key-value pair
		String uname = credentialList.get(0).get("username");
		String pwd = credentialList.get(0).get("password");
		
		//Launch browser and goto loginpage URL
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Avinav\\Downloads\\chromedriver.exe");
		driver = new ChromeDriver();
		loginPage = new LoginPage(driver);
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		
		accountsPage = loginPage.doLogin(uname, pwd); //call method in LoginPage class to login with credentials passed, returns next page obj AccountsPage
	}

	@Given("user is on Accounts page")
	public void user_is_on_accounts_page() {
		String acPageTitle = accountsPage.getAccountsPageTitle();
		System.out.println("checking accounts page title returned: "+acPageTitle);
	    
	}

	@Then("user gets accounts section")
	public void user_gets_accounts_section(DataTable sectionsTable) {
		
		List<String> expAccountsSectionList = sectionsTable.asList();//read 2nd datatable from feature file and store in a List of Strings
		System.out.println("Expected Accounts section list"+ expAccountsSectionList);
		List<String> actualAccountsSectionList = accountsPage.getAccountsSectionList(); //coming from Accounts Page class
		System.out.println("Actual Accounts section list"+ actualAccountsSectionList);
		
		/*All strings returned by WebDriver should equal
		 * List of strings extracted from feature file
		 */
		Assert.assertTrue(expAccountsSectionList.containsAll(actualAccountsSectionList)); 
	    
	}

	@Then("accounts section count should be {int}")
	public void accounts_section_count_should_be(Integer expectedSectionCount) {
		Assert.assertTrue(accountsPage.getAccountsSectionCount() == expectedSectionCount);
	}
}
