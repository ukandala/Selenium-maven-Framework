package com.CiviCrm.Practice;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.CiviCrm.PageFactory.LoginPageFactory;
import com.CiviCrm.PageFactory.NewMaillingPageFactory;

import utilities.TestBase;

public class NewMailling extends TestBase{

	//WebDriver driver2;
	LoginPageFactory loginPage;
	NewMaillingPageFactory mailing;
	
	@BeforeClass
	public void initialze() {
		//TestBase.openBrowser("chrome", TestBase.readConfigFile("URL4"));
		TestBase.getBrowser("myBrowser");
		loginPage = new LoginPageFactory(driver);
		mailing = new NewMaillingPageFactory(driver);
	}
	
	@Test
	public void login() {
		loginPage.giveUserName();
		loginPage.givePassword();
		loginPage.clickLoginButton();
		//loginPage.removeSystemError();
		loginPage.verifyingPage();
	}
	
	@Test(dependsOnMethods= {"login"})
	public void createMail() throws Exception {
		Thread.sleep(1000);
		mailing.clickMailing();
		Thread.sleep(1000);
		mailing.clickNewMailing();
		Thread.sleep(3000);
		mailing.giveMailingName();
		mailing.openTemplate();
		Thread.sleep(2000);
		mailing.selectingTemplate();
		Thread.sleep(1000);
		mailing.selectRecipients();
		Thread.sleep(2000);
		mailing.scrollPageDown();
		Thread.sleep(1000);
		mailing.clickPlainText();
		mailing.giveTextArea();
		mailing.scrollPageDown();
		mailing.clickSaveDraft();
		Thread.sleep(2000);
		mailing.verifyPage();
		mailing.giveMailerName();
		mailing.clickSearch();
		Thread.sleep(2000);
		
	}
}
