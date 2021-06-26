package common;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import javax.naming.ConfigurationException;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.testng.TestException;

public class PropertiesHandler {
	public static PropertiesConfiguration config = null; 
	public PropertiesHandler (String fileName) throws TestException {
		if(fileName.isEmpty() || fileName == null)
			throw new TestException("Failed during properties handler . file name is null");
		fileName = fileName + ".properties";
		 String path = System.getProperty("user.dir")+ File.separator + "src"+ File.separator+"test"+ File.separator + "resources"
                 + File.separator+"testdata"+ File.separator + fileName;
		 File file = new File(path);
		 System.out.println(path);
		 if(!file.exists())
			 throw new TestException("Properties File is missing in path : "+ file.getAbsolutePath());
		try {
		this.config= new PropertiesConfiguration() ;
		this.config.setDelimiter('=');
		this.config.setDelimiterParsingDisabled(true);
		this.config.load(path);
		System.out.println(config);
	        }
		 catch (Exception e) {
			 throw new TestException(" failed during Configuration Setting in properties file handler ");
		 }
	}
	
	public String get(String propertyKey)throws TestException {
		String propertyValue= null;
		//System.out.println(config.getKeys());
		Iterator<String> key = this.config.getKeys();
		while(key != null && key.hasNext()) {
			String input = key.next();
			//System.out.println(input);
			if(input.equalsIgnoreCase(propertyKey)) {
				if(this.config.getProperty(input).getClass() == ArrayList.class) {
					propertyValue = this.config.getProperty(input).toString();
				}
				else {
					propertyValue =(String) this.config.getProperty(input);
				}
				break;
			}
		}
		if(propertyValue == null)
			throw new TestException("could not find the key '"+ propertyKey + "' in properties file ");
		return propertyValue;
		
	}
	
}

