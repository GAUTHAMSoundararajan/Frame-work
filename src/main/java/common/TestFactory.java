package common;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class TestFactory {

	private static HashMap<Integer,LinkedList<DriverFactory>> driverFactories;
	private static HashMap<Integer,String> testScenarios;
	private static HashMap<Integer,List<String>> testArtifacts;
	private static TestFactory factory = getInstance();
	static String runid = "";

	public static TestFactory getInstance() {
		if(factory == null)
			factory = new TestFactory();

		if(driverFactories == null)
			driverFactories = new HashMap<Integer,LinkedList<DriverFactory>>();

		if(testArtifacts == null)
			testArtifacts = new HashMap<>();

		if(testScenarios == null)
			testScenarios = new HashMap<>();

		String runId = null;
		runId = DataFactory.generate("[String|AN|5");

		if(TestFactory.runid==null)
			TestFactory.runid = runId;
		return factory;

	}
	public static void startTestRun(String testName) {
		testScenarios.put(Thread.currentThread().hashCode(), testName);
		endTestRun(testName);
		//////////////////
		TestFactory.startTestRun(testName);

	}

	public static String getTestcaseName() {
		int threadId = Thread.currentThread().hashCode();
		String Testcase_Name = null;
		if(testScenarios == null)
			return null;

		for(int thread : testScenarios.keySet()) {
			if(thread == threadId) {
				Testcase_Name = testScenarios.get(thread);
				break;
			}
		}
		return Testcase_Name;
	}
	
	public static void endTestRun (String testName) {
		int threadId = Thread.currentThread().hashCode();
		if(driverFactories != null)
			for(int thread : driverFactories.keySet()) {
				if(thread == threadId) {
					driverFactories.remove(thread);
					break;
				}
			}
		if(testArtifacts != null)
			for(int thread : testArtifacts.keySet()) {
				if(thread == threadId) {
					testArtifacts.remove(thread);
					break;
				}
			}
		TestFactory.endTestRun(testName);
		///////////////////////////////////////////////
	}
}


