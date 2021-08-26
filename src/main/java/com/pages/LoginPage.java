package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

/*private By locators
 * constructor
 *public page actions
 * private WebDriver
 */
public class LoginPage {

	private ChromeDriver driver;
	
	//1. By locators/Object Repositories
	private By emailID = By.name("email");
	private By password = By.name("passwd");
	private By signInButton = By.name("SubmitLogin");
	private By forgotPasswordLink = By.linkText("Forgot your password?");
	
	//2. Constructor of page class
	public LoginPage(ChromeDriver driver) {
		this.driver = driver; //initialize pvt driver defined in this class
	}
	
	//3. page actions (features)
	public String getLoginPageTitle() {
		return driver.getTitle(); //return title of login page
	}
	
	public boolean isForgotPaswordLinkExists() {
		return driver.findElement(forgotPasswordLink).isDisplayed();
	}
	
	public void enterUserName(String username) {
		driver.findElement(emailID).sendKeys(username);
	}
	
	public void enterPassword(String pwd) {
		driver.findElement(password).sendKeys(pwd);
	}
	
	public void clickOnLogin() {
		driver.findElement(signInButton).click();
	}
	
	public String getHomePageTitle() {
		return driver.getTitle(); //return title of login page
	}
	
	/*method to combine login steps above into a single method
	 * 
	 */
	public AccountsPage doLogin(String un, String pwd) {
		System.out.println("Login with: "+un+ " and " + pwd);
		driver.findElement(emailID).sendKeys(un);
		driver.findElement(password).sendKeys(pwd);
		driver.findElement(signInButton).click();
		
		//This method should return Object of the next page: Accounts Page (page-chaining concept)
		return new AccountsPage(driver);
	}
}
