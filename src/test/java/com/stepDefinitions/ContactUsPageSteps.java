package com.stepDefinitions;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.chrome.ChromeDriver;

import com.pages.ContactUsPage;
import com.qa.util.ExcelReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class ContactUsPageSteps {

	private ContactUsPage contactUsPage;
	private ChromeDriver driver;

	@Given("user navigates to Contact Us page")
	public void user_navigates_to_contact_us_page() {

		// Launch browser and goto loginpage URL
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Avinav\\Downloads\\chromedriver.exe");
		driver = new ChromeDriver();
		contactUsPage = new ContactUsPage(new ChromeDriver());
		driver.get("http://automationpractice.com/index.php?controller=contact");
	}

	@When("user fillers the form from given sheetname {string} and rownumber {int}")
	public void user_fillers_the_form_from_given_sheetname_and_rownumber(String SheetName, Integer RowNumber)
			throws InvalidFormatException, IOException {

		ExcelReader reader = new ExcelReader();
		List<Map<String, String>> excelData = reader.getData("C:\\Users\\Avinav\\Desktop\\automation.xlsx", SheetName);

		String heading = excelData.get(RowNumber).get("subjectheading");// read 1st row heading from excel sheeet
		String email = excelData.get(RowNumber).get("email");
		String orderRef = excelData.get(RowNumber).get("orderref");
		String message = excelData.get(RowNumber).get("message");

		contactUsPage.fillContactUsForm(heading, email, orderRef, message); // call method in ContactUsPage class to
																			// fill in data on the page

	}

	@When("user clicks on send button")
	public void user_clicks_on_send_button() {

		contactUsPage.clickSend();

	}

	@Then("it shows a successful message {string}")
	public void it_shows_a_successful_message(String expectedSuccessMsg) {

		String actualSuccessMsg = contactUsPage.getSuccessMessg();
		Assert.assertEquals(expectedSuccessMsg, actualSuccessMsg);

	}

}
