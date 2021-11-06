package test.library;

import org.testng.ISuite;
import org.testng.ISuiteListener;


import common.PropertiesHandler;

public class Listener implements ISuiteListener{
	
@Override
  public void onStart(ISuite arg0) {
	System.out.println("in listener");
	PropertiesHandler properties = new PropertiesHandler("config");
	String env = properties.get("ENVIRONMENT");
	System.out.println(env);
  }

@Override
public void onFinish(ISuite arg0) {
	
}
}
