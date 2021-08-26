package com.qa.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	public WebDriver driver;
	
	//Thread local for parallel execution
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
	
	/* This method used to initialize threadlocal driver on the basis of
	 * the given browser
	 * @param browser
	 * @return WebDriver
	 */
	public WebDriver initDriver(String browser) {
		System.out.println("browser value from config.properties: "+browser);
		
		if(browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			tlDriver.set(new ChromeDriver()); //set ThreadLocal obj with ChromeDriver obj
		}
		else if(browser.equals("firefox")) {
			WebDriverManager.chromedriver().setup();
			tlDriver.set(new FirefoxDriver()); //set ThreadLocal obj with ChromeDriver obj
		}
		else if(browser.equals("safari")) {
			WebDriverManager.chromedriver().setup();
			tlDriver.set(new SafariDriver()); //set ThreadLocal obj with ChromeDriver obj
		}
		else {
			System.out.println("Please check the value of the browser passed: "+browser);
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		
		return getDriver();
		
	}
	
	/* this method is used to get the driver with ThreadLocal
	 * @return ThreadLocal<WebDriver> obj
	 */
	public static synchronized WebDriver getDriver() {
		boolean currentURL = tlDriver.get().getCurrentUrl().isEmpty();
		if(currentURL)
		{
			System.out.println("currentURL true");
		}
		else {
			System.out.println("currentURL false");
		}
		//System.out.println("DriverFactory obj: "+);
		return tlDriver.get();
		
	}

}
