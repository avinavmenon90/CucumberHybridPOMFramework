package com.Runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"C:\\Users\\Avinav\\eclipse-workspace\\CucumberHybridPOMFramework\\src\\test\\resources\\com\\features\\ContactUs.feature"},
		glue = {"com/stepDefinitions", "Hooks"},
		plugin = {"pretty",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"timeline:test-output-thread/"				
				},
		dryRun = false
		
		)
public class TestRunner {

}
