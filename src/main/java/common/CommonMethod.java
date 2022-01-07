package common;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.TestException;




public class CommonMethod {

	public static LinkedHashMap<Integer,WebDriver> driverfactories = new LinkedHashMap<>();
	
 protected synchronized  void initializeBrowser(String browser,String url) throws IOException {
		
		Properties pro = new Properties();
		Thread currentThread =  Thread.currentThread();
		int threadID = currentThread.hashCode();
		FileInputStream Fio = new FileInputStream(System.getProperty("user.dir")+ File.separator +"src"+ File.separator+"test"+ File.separator+"resources"+ File.separator+"testdata"+ File.separator+"config.properties");
		pro.load(Fio);
		String value = pro.getProperty("EXECUTION_LOG");
		System.out.println("naven :"+value);
		if(browser == null)
			throw new TestException("please provide the broswer name in config file");
		boolean isInvoked = false;
		for(int i=0;i<5;i++) {
			try {
				 WebDriver driver = DriverFactory.getDriverPath(browser,url);
				 driverfactories.put(threadID,driver);
			    System.out.println("browser invoked");
			    isInvoked=true;
			    	break;
		    
		}		
	    catch(Exception e) {
	    	System.out.println("browser not loaded ..Trying again");
	    	e.printStackTrace();
	    	//closeBrowser();
	    }
		}
		System.out.println("TiTle :"+getDriver().getTitle());
		if(!isInvoked)
	    	throw new TestException("Error in invoking the browser");
		
	}
 public synchronized static WebDriver getDriver() {
	 
	 Thread currentThread = Thread.currentThread();
	 int threadID = currentThread.hashCode();
	 System.out.println("79:"+threadID);
	 WebDriver driver = null;
	 try {
	 driver = (WebDriver) driverfactories.get(threadID);
	 }
	 catch (Exception e) {
		e.printStackTrace();
		return driver;
	}
	return driver;
	 
 }
 //This method helps to load the locators (ie) pagefactory implementaion 
 public static <T> T getPage(Class<T> page) {
	 return org.openqa.selenium.support.PageFactory.initElements(getDriver(), page);
 }
 
	public static void webElementTextbox(WebElement element,String field,String value) throws Exception {
		
		if(!element.isDisplayed())
			throw new Exception("Value '"+value +"' cannot be entered as field'"+field+"' is not displayed ");
		if(!element.isEnabled())	
			throw new Exception("Value '"+value +"' cannot be entered as field'"+field+"' is disabled ");
		
		try {
			if(value==null|| value.isEmpty()) 
				return;
			element.clear();
			element.sendKeys(value);
			//log.info("Text :"+  value + "Entered in "+field);			
			
		}
		catch (NoSuchElementException e) {
			throw new Exception("Field :'"+field+"' not found, could not enter '"+value +"' into textbox");
		}
		catch (NullPointerException e) {
			throw new Exception("Element is not initialized");
		}
		catch (ElementNotVisibleException e) {
			throw new Exception("Field :'"+field+"' not displayed, could not enter '"+value +"' into textbox");
		}
		catch (TimeoutException e) {;
	
			throw new Exception("Time out, Click operation is not completed before time");
		}
	}
	
	protected synchronized static void closeBrowser() {
		if(getDriver() != null) {
			getDriver().close();
		try {
			Alert alert = getDriver().switchTo().alert();
			alert.accept();
		}
		catch (Exception e) {
			
		}
		getDriver().quit();
		}
		
	}
	
	public static void webElementClick(WebElement element) throws Exception {
		try {
			if(!element.isDisplayed())
				throw new Exception(element.toString()+" is not displayed ");
			if(!element.isEnabled())	
				throw new Exception(element.toString()+" is disabled ");
			System.out.println(element);
			scrollIntoView(element);
			int retryCount =0;
			while(retryCount<=5) {
				try {
					element.click();
					break;
				}
				catch(Exception e) {
					scrollIntoView(element);
					retryCount++;
				}
			}
		}
			catch (NoSuchElementException e) {
				throw new Exception("NoSuchElementException, Field not found");
			}
			catch (NullPointerException e) {
				e.printStackTrace();
			}
			catch (ElementNotVisibleException e) {
				throw new Exception("ElementNotVisibleException, Field is not visible");
			}
			catch (TimeoutException e) {
		
				throw new Exception("Time out, Click operation is not completed before time");
			}
	} 
	

	public static void webElementJsClick(WebElement element) throws Exception {
		try {
			if(!element.isDisplayed())
				throw new Exception(element.toString()+" is not displayed ");
			if(!element.isEnabled())	
				throw new Exception(element.toString()+" is disabled ");
			
			scrollIntoView(element);
			JavascriptExecutor js = (JavascriptExecutor)getDriver();
			js.executeScript("arguments[0].click()", element);
			
		}
			catch (NoSuchElementException e) {
				throw new Exception("NoSuchElementException, Field not found");
			}
			catch (NullPointerException e) {
				throw new Exception("NullPointerException, Element is not initialized");
			}
			catch (ElementNotVisibleException e) {
				throw new Exception("ElementNotVisibleException, Field is not visible");
			}
			catch (TimeoutException e) {
		
				throw new Exception("Time out, Click operation is not completed before time");
			}
	} 
	
	public static void webElementActionClick(WebElement element) throws Exception {
		try {
			if(!element.isDisplayed())
				throw new Exception(element.toString()+" is not displayed ");
			if(!element.isEnabled())	
				throw new Exception(element.toString()+" is disabled ");
			Actions action = new Actions(getDriver());
			action.moveToElement(element).perform();
			action.click(element).perform();
		}
			catch (NoSuchElementException e) {
				throw new Exception("NoSuchElementException, Field not found");
			}
			catch (NullPointerException e) {
				throw new Exception("NullPointerException, Element is not initialized");
			}
			catch (ElementNotVisibleException e) {
				throw new Exception("ElementNotVisibleException, Field is not visible");
			}
			catch (TimeoutException e) {
		
				throw new Exception("Time out, Click operation is not completed before time");
			}
	} 
	
	public static void doubleClick(WebElement element) throws Exception {
		try {
			if(!element.isDisplayed())
				throw new Exception(element.toString()+" is not displayed ");
			if(!element.isEnabled())	
				throw new Exception(element.toString()+" is disabled ");
			Actions action = new Actions(getDriver());
			action.doubleClick(element).perform();
		}
			catch (NoSuchElementException e) {
				throw new Exception("NoSuchElementException, Field not found");
			}
			catch (NullPointerException e) {
				throw new Exception("NullPointerException, Element is not initialized");
			}
			catch (ElementNotVisibleException e) {
				throw new Exception("ElementNotVisibleException, Field is not visible");
			}
			catch (TimeoutException e) {
		
				throw new Exception("Time out, Click operation is not completed before time");
			}
	} 
	
	public static void rightClick(WebElement element) throws Exception {
		try {
			if(!element.isDisplayed())
				throw new Exception(element.toString()+" is not displayed ");
			if(!element.isEnabled())	
				throw new Exception(element.toString()+" is disabled ");
			Actions action = new Actions(getDriver());
			action.contextClick(element).perform();
		}
			catch (NoSuchElementException e) {
				throw new Exception("NoSuchElementException, Field not found");
			}
			catch (NullPointerException e) {
				throw new Exception("NullPointerException, Element is not initialized");
			}
			catch (ElementNotVisibleException e) {
				throw new Exception("ElementNotVisibleException, Field is not visible");
			}
			catch (TimeoutException e) {
		
				throw new Exception("Time out, Click operation is not completed before time");
			}
	} 
	
	public static void selectDropdown(WebElement element,String value) throws Exception {
		
		if(!element.isDisplayed())
			throw new Exception(element.toString()+" is not displayed ");
		if(!element.isEnabled())	
			throw new Exception(element.toString()+" is disabled ");
		if(value==null|| value.isEmpty())
			throw new Exception(" please provide the option to be selected");
		List<String> values = new ArrayList<>();
	try {
		boolean isDropdownSelected = false;
		Select dropdown = new Select(element);
		try {
			dropdown.selectByVisibleText(value);
			isDropdownSelected = true;
		}
		
		catch (Exception e) {
			List<WebElement> options = dropdown.getOptions();
			for(int i =0 ;i<options.size();i++) {
				values.add(options.get(i).getText());
				if(options.get(i).getText().trim().equalsIgnoreCase(value.trim())) {
					dropdown.selectByIndex(i);
					isDropdownSelected = true;
					break;
				}
					
			}
		}
			
			String uiValue = dropdown.getFirstSelectedOption().getText();
			if(!(uiValue != null && uiValue.trim().equalsIgnoreCase(value.trim()) && isDropdownSelected == true)) 
				throw new TestException("could not select value '"+value+"' for options");
			
	}
			catch (NoSuchElementException e) {
				throw new Exception("value '"+value+"' is not found . please select from the available options : "+ values );
			}
			catch (NullPointerException e) {
				throw new Exception("NullPointerException, Element is not initialized");
			}
			catch (ElementNotVisibleException e) {
				throw new Exception("ElementNotVisibleException, Field is not visible");
			}
			catch (TimeoutException e) {
		
				throw new Exception("Time out, Click operation is not completed before time");
			}
			
		}
		
public static void selectDropdownByValue(WebElement element,String value) throws Exception {
		
		if(!element.isDisplayed())
			throw new Exception(element.toString()+" is not displayed ");
		if(!element.isEnabled())	
			throw new Exception(element.toString()+" is disabled ");
		if(value==null|| value.isEmpty())
			throw new Exception(" please provide the option to be selected");
		List<String> values = new ArrayList<>();
	try {
		Select dropdown = new Select(element);
		try {
			dropdown.selectByValue(value);
		}
		
		catch (Exception e) {
			List<WebElement> options = dropdown.getOptions();
			for(int i =0 ;i<options.size();i++) {
				values.add(options.get(i).getAttribute("value"));
				if(options.get(i).getAttribute("value").trim().equalsIgnoreCase(value.trim())) {
					dropdown.selectByIndex(i);
					break;
				}
					
			}
		}
	}
			catch (NoSuchElementException e) {
				throw new Exception("value '"+value+"' is not found . please select from the available options : "+ values );
			}
			catch (NullPointerException e) {
				throw new Exception("NullPointerException, Element is not initialized");
			}
			catch (ElementNotVisibleException e) {
				throw new Exception("ElementNotVisibleException, Field is not visible");
			}
			catch (TimeoutException e) {
		
				throw new Exception("Time out, Click operation is not completed before time");
			}
			
		}
		
	
	
	public static boolean switchToWindows(String Windows) throws TestException {
		Set<String> allWindows  = null;
		boolean isWindowSwitched = false;
		try {
			getDriver().manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
			allWindows = getDriver().getWindowHandles();
			for(String window: allWindows) {
				getDriver().switchTo().window(window);
				if(getDriver().getTitle().trim().contains(Windows.trim())) {
					isWindowSwitched = true;
					break;
				}
			}
			if(!isWindowSwitched)
				throw new TestException("Window not switched.The expexted window : '"+Windows+"' not present. ");
		}
		catch(Exception e){
		throw new TestException("Failed while Switching window"+e.getMessage());
		}
		return isWindowSwitched;
		
	}
	public static void scrollIntoView(WebElement element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor)getDriver();
			System.out.println("dddd : "+js);
			js.executeScript("arguments[0].scrollIntoView(true);", element);
		}
		catch(Exception e) {
			e.printStackTrace();
			//throw new TestException("scrolling action failed, element not found");
		}
	}
//returns alert message and accept or dismiss alert
	public static String alertHandles(String action) {
		Alert alert = null;
		String alertMessage = null;
		try {
		WebDriverWait wait = new WebDriverWait(getDriver(), 5);
		alert = wait.until(ExpectedConditions.alertIsPresent());
		if(alert!=null) {
			alertMessage = getDriver().switchTo().alert().getText();
			if(action.equalsIgnoreCase("accept")) {
				alert.accept();
			}
			if(action.equalsIgnoreCase("dismiss")) {
				alert.dismiss();
			}
		}
		else {
			throw new Exception("Alert is not present");
		}
		}
		catch(Exception e) {
			throw new TestException(e.getMessage());
		}
		
		return alertMessage;
	}
	public static void alertHandles(String text,String action) {
		Alert alert = null;
		String alertMessage = null;
		try {
		WebDriverWait wait = new WebDriverWait(getDriver(), 5);
		alert = wait.until(ExpectedConditions.alertIsPresent());
		if(alert!=null) {
			alertMessage = getDriver().switchTo().alert().getText();
			if(alertMessage.trim().toLowerCase().replaceAll(" ", "").contains(text.trim().toLowerCase().replaceAll(" ", ""))) {
			if(action.equalsIgnoreCase("accept")) {
				alert.accept();
			}
			if(action.equalsIgnoreCase("dismiss")) {
				alert.dismiss();
			}
			}
			else {
				throw new TestException("Alert message mismatch . Expected : '"+text+"' Actual : '"+alertMessage+"'");
			}
		}
		else {
			throw new Exception("Alert is not present");
		}
		}
		catch(Exception e) {
			throw new TestException(e.getMessage());
		}
	}
}
