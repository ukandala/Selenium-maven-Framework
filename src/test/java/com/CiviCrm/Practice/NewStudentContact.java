package com.CiviCrm.Practice;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.CiviCrm.PageFactory.LoginPageFactory;
import com.CiviCrm.PageFactory.NewStudentContactPageObject;
import utilities.TestBase;

public class NewStudentContact extends TestBase {
	
	WebDriver driver3;
	NewStudentContactPageObject page;
	LoginPageFactory loginPage;
	
	@BeforeClass
	public void intialze() {
		TestBase.getBrowser("myBrowser");
		page = new NewStudentContactPageObject(driver);
		loginPage = new LoginPageFactory(driver);
	}
	
	@Test
	public void loginInSite() {
		loginPage.giveUserName();
		loginPage.givePassword();
		loginPage.clickLoginButton();
		//loginPage.removeSystemError();
		loginPage.verifyingPage();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test(dependsOnMethods = {"loginInSite"})
	public void createNewStudent() throws Exception {
		page.clickContacts();
		Thread.sleep(1000);
		page.clickNewIndividual();
		Thread.sleep(1000);
		page.clickNewStudent();
		Thread.sleep(2000);
		page.verifyPage();
		page.giveFirstName();
		page.giveLastName();
		page.giveJobTitle();
		page.giveNickName();
		page.giveEmail();
		page.givePhone();
		page.scrollDownPage();
		page.savePage();
		Thread.sleep(2000);
		page.verifySavedPage();
	}

}
