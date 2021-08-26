package com.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AccountsPage {

	private ChromeDriver driver;

	// 1. By locators/Object Repositories
	private By accountsSection = By.cssSelector("div#center_column span");
	
	//2. Constructor of AccountsPage class
	public AccountsPage(ChromeDriver driver) {
		this.driver = driver;
	}
	
	//3. page actions (features)
	public int getAccountsSectionCount() {
		return driver.findElements(accountsSection).size()-1; //-1 to exclude Home link
	}
	
	public List<String> getAccountsSectionList() {
		
		List<String> accountsList = new ArrayList<>();
		List<WebElement> accountsHeaderList = driver.findElements(accountsSection);
		
		for(WebElement e: accountsHeaderList) {
			String text = e.getText(); //retrieve text from each span under the div
			System.out.println(text); //print to console
			accountsList.add(text); //add each string from span to an Arraylist of String
		}
		return accountsList;
	}
	
	public String getAccountsPageTitle() {
		return driver.getTitle();
	}
		

}

