package module;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import common.CommonMethod;



public class Pratice extends CommonMethod {

	@FindBy(xpath = "//a[contains(text(),'Login')]")
    private WebElement login;
	WebElement ele = getDriver().findElement(By.xpath("//a[contains(text(),'Login')]"));
	public void loginpage() throws Exception {
		try {  
		webElementClick(login);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
