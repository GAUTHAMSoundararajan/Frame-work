package module;

import java.io.IOException;
import common.DriverFactory;

public class HomePage {
	
	
	public  static void initializeBrowser(String browser, String url) throws IOException {
	// TODO Auto-generated method stub
		System.out.println(" AM HERE");
		try {
		DriverFactory.initializeBrowser("chrome", "https://google.com");
		}
		catch (Exception e) {
			e.printStackTrace();
		}

}
	
}
