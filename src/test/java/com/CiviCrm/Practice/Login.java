package com.CiviCrm.Practice;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.CiviCrm.PageFactory.LoginPageFactory;

import utilities.TestBase;

public class Login extends TestBase {
	
	//static WebDriver driver1;

	LoginPageFactory loginPage;
	
	@BeforeClass
	public void initialize() {
		//TestBase.openBrowser("chrome", TestBase.readConfigFile("URL4"));
		TestBase.getBrowser("myBrowser");
		loginPage = new LoginPageFactory(driver);
	}
	
	@Test
	public void loggingIn() throws Exception {
		loginPage.giveUserName();
		loginPage.givePassword();
		loginPage.clickLoginButton();
		//loginPage.removeSystemError();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		loginPage.scrollingPage();
		loginPage.verifyingPage();
	}
}
