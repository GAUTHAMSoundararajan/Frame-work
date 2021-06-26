package test.runners;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(plugin = {"pretty","json:target/cucumber/report.json","html:target/cucumber/report.html"},
tags = "@login",
features = "src/test/resources/features/launch.feature",
glue = {"test/steps","test/library"}
)
public class TestRunner extends AbstractTestNGCucumberTests{

	@BeforeTest
	public void before() {
		
	}
	@AfterTest
	public void after() {
		
	}
	//html:report/html","json:report/json/thread.json
}
