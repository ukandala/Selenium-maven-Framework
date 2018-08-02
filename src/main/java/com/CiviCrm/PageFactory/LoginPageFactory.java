package com.CiviCrm.PageFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utilities.TestBase;
import utilities.Xls_Reader;

public class LoginPageFactory {
	
	WebDriver driver;
	
	JavascriptExecutor	js;
	
	Xls_Reader reader =  new Xls_Reader(System.getProperty("user.dir") + "/DataResources/DataFile.xlsx");
	static Logger log =  LogManager.getLogger(LoginPageFactory.class.getName());

	@FindBy(id="edit-name")
	WebElement userName;
	
	@FindBy(id="edit-pass")
	WebElement passowrd;
	
	@FindBy(id="edit-submit")
	WebElement login;
	
	@FindBy(xpath=".//*[@id='crm-notification-container']//a[contains(text(),'x')]")
	WebElement systemError;
	
	
	
	//Intialize the class
	public LoginPageFactory(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void giveUserName() {
		userName.sendKeys(reader.getCellData("CiviCrm", "UserName", 2));
	}
	
	public void givePassword() {
		passowrd.sendKeys(reader.getCellData("CiviCrm", "Password", 2));
	}
	
	public void clickLoginButton() {
		login.click();
	}
	
	public void removeSystemError() {
		Actions act = new Actions(driver);
		act.moveToElement(systemError).click().build().perform();
	}
	
	public void verifyingPage() {
		Assert.assertEquals("CiviCRM Home | CiviCRM Sandbox on Drupal", driver.getTitle());
	}
	
	public void scrollingPage() {
		js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		//or
		//js.executeScript("window.scrollBy(0,250)", ""); for scrolling down
		//js.executeScript("window.scrollBy(0,-250)", ""); for scrolling up
	}
}
