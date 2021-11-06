package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.TestException;

public class DriverFactory {
	
	private static WebDriver driver = null;
	public static int waitTime = 10;
	public static String Download_Path = System.getProperty("user.dir")+ File.separator + "reports"+ File.separator+"downloads";
	public static String firefox_Path = System.getProperty("user.dir")+ File.separator + "lib"+ File.separator+"geckodriver_32.exe";
	public static String IE_32_Path = System.getProperty("user.dir")+ File.separator + "lib"+ File.separator+"IEDriverServer_32.exe";
	public static String IE_64_Path = System.getProperty("user.dir")+ File.separator + "lib"+ File.separator+"IEDriverServer_64.exe";
	public static String Chrome_Path = System.getProperty("user.dir")+ File.separator + "lib"+ File.separator+"chromedriver.exe";
	
	
	
	
	public static WebDriver getDriverPath(String browser,String url) {
		
		browser = browser.replaceAll(" ", "");
		if(browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",Chrome_Path);
			HashMap<String,Object> chromePrefs = new HashMap<String,Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", Download_Path);
			
			ChromeOptions options = new ChromeOptions();
			
			options.setExperimentalOption("prefs", chromePrefs);
			options.setExperimentalOption("useAutomationExtension",false);
			options.addArguments("start-maximized");
			
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			capabilities.setPlatform(Platform.WINDOWS);
			 driver = new ChromeDriver(capabilities);
			 driver.manage().deleteAllCookies();
			 driver.get(url);
			 driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
			 driver.manage().window().maximize();
			 
		}
		else if(browser.equalsIgnoreCase("IE") || browser.equalsIgnoreCase("IE32") || browser.equalsIgnoreCase("IE64") || browser.equalsIgnoreCase("InternetExplorer64")  || browser.equalsIgnoreCase("InternetExplorer")) {
			
			if(browser.equalsIgnoreCase("IE") || browser.equalsIgnoreCase("IE32") || browser.equalsIgnoreCase("InternetExplorer"))
			System.setProperty("webdriver.ie.driver",IE_32_Path);
			
			if( browser.equalsIgnoreCase("IE64") || browser.equalsIgnoreCase("InternetExplorer64"))
			System.setProperty("webdriver.ie.driver",IE_64_Path);
			
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			driver = new InternetExplorerDriver(capabilities);
			driver.manage().deleteAllCookies();
			driver.get(url);
			driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
			driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, "0"));
		}
		else if(browser.equalsIgnoreCase("firefox") || browser.equalsIgnoreCase("gecko")) {
			System.setProperty("webdriver.gecko.driver",firefox_Path);
			FirefoxProfile profile = new FirefoxProfile();
			profile.setAcceptUntrustedCertificates(true);
			profile.setPreference("browser.download.dir", Download_Path);
			profile.setPreference("browser.download.folderList", 2);// to auto download to folder
			
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability(FirefoxDriver.PROFILE, profile);
			capabilities.setCapability("requireWindowFocus", true);
		    capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		    driver = new FirefoxDriver(capabilities);
		    driver.manage().deleteAllCookies();
		    driver.get(url);
		    driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		}
		else {
			
			throw new TestException("Browser name doesn't match. Expected : chrome or IE or Firefox.  Actual : '"+browser+"'");
		}
		return driver;
		
	}
	

}
