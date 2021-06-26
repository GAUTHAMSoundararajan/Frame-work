package test.steps;

import java.io.IOException;

import io.cucumber.java.en.Given;
import module.HomePage;

public class login {

	@Given("^login using credentials on apllication$")
	public void invokeBrowser() throws IOException {
		System.out.println("im here");
		HomePage hp = new HomePage();
		hp.initializeBrowser("chrome", "www.hotstar");
	}
}
