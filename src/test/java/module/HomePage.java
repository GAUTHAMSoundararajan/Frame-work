package module;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import common.CommonMethod;
import common.DriverFactory;


public class HomePage extends CommonMethod {
	//@findby work panna vekanum null varama
	// checkbox class also driver null varama pathukanum

	
	
	public void initialize(String browser, String url) throws IOException {
	// TODO Auto-generated method stub
		System.out.println(" AM HERE");
		try {          	
		initializeBrowser("chrome", "https://phptravels.com/demo/");
		Thread.sleep(5000);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

}
	
}
