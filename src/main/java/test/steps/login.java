package test.steps;

import java.io.IOException;

import common.CommonMethod;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import module.HomePage;
import module.Pratice;


public class login  extends CommonMethod {

	@Given("^login using credentials on aplication$")
	public void invokeBrow() throws IOException {
		System.out.println("im here");
		HomePage hp = new HomePage();
		hp.initialize("chrome", "www.hotstar");
	}
	
	@And("^some other precondition$")
	public void perform() throws Exception {
		System.out.println("in click");
		Pratice pp = CommonMethod.getPage(Pratice.class);
		pp.loginpage();
	}
	
}
