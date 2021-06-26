package test.library;

import java.util.Calendar;

import common.TestFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;



public class Hooks extends TestFactory{

	public static Calendar Testcase_Start_Time;
	public static Calendar Testcase_End_Time;
	
	@Before
	public void beforeHook(Scenario scenario) {
		System.out.println("in hooks");
		Testcase_Start_Time = Calendar.getInstance();
		String name = scenario.getName();
		System.out.println("started executing Test case: "+ name);
		TestFactory.getInstance();
		//TestFactory.startTestRun(name);
	}
	
	
	@After
	public void afterHook(Scenario scenario) {
		
	}
}
