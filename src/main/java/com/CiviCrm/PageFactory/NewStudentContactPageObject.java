package com.CiviCrm.PageFactory;

import static org.testng.Assert.assertEquals;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.*;

public class NewStudentContactPageObject {

	WebDriver driver;
	JavascriptExecutor js;
	Xls_Reader reader = new Xls_Reader(System.getProperty("user.dir") + "/DataResources/DataFile.xlsx");
	
	@FindBy(xpath="//*[@id='civicrm-menu']//li[contains(text(),'Contacts')]")
	WebElement contacts;
	
	@FindBy(xpath="//*[@id='root-menu-div']//a[contains(text(),'New Individual')]")
	WebElement newIndividual;
	
	@FindBy(xpath=".//*[@id='root-menu-div']//a[contains(text(),'New Student')]")
	WebElement newStudent;
	
	@FindBy(id="first_name")
	WebElement firstName;
	
	@FindBy(id="last_name")
	WebElement lastName;
	
	@FindBy(id="select2-chosen-19")
	WebElement currentEmployer;
	
	@FindBy(id="//*[@id='select2-results-19']//*[contains(text(),'New Organization')]")
	WebElement newOrganiation;
	
	@FindBy(id="organization_name")
	WebElement organizationName;
	
	@FindBy(xpath="//button[@class='ui-button ui-corner-all ui-widget'][contains(text(),'Save')]")
	WebElement organizationNameSave;
	
	@FindBy(id="job_title")
	WebElement jobTitle;
	
	@FindBy(id="nick_name")
	WebElement nickName;
	
	@FindBy(id="email_1_email")
	WebElement email;
	
	@FindBy(id="phone_1_phone")
	WebElement phone;
	
	@FindBy(id="_qf_Contact_upload_view-bottom")
	WebElement save;
	
	//Initialize the page
	public NewStudentContactPageObject(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickContacts() {
		Actions act = new Actions(driver);
	    act.moveToElement(contacts).click().build().perform();
	}
	
	public void clickNewIndividual() {
		Actions act = new Actions(driver);
		act.moveToElement(newIndividual).build().perform();
	}
	
	public void clickNewStudent() {
		Actions act = new Actions(driver);
		act.moveToElement(newStudent).click().build().perform();
	}
	
	public void verifyPage() {
		assertEquals("New Student | CiviCRM Sandbox on Drupal", driver.getTitle());
	}
	
	public void giveFirstName() {
		firstName.sendKeys(reader.getCellData("CiviCrm", "FirstName", 2));
	}
	
	public void giveLastName() {
		lastName.sendKeys(reader.getCellData("CiviCrm", "LastName", 2));
	}
	
	public void selectCurrentEmployer() {
		currentEmployer.click();
	}
	
	public void clickNewOrganization() {
		Actions act = new Actions(driver);
		act.moveToElement(newOrganiation).click().build().perform();
	}
	
	public void giveJobTitle() {
		jobTitle.sendKeys(reader.getCellData("CiviCrm", "JobTitle", 2));
	}
	
	public void giveNickName() {
		nickName.sendKeys(reader.getCellData("CiviCrm", "Nickname", 2));
	}
	
	public void giveEmail() {
		email.sendKeys(reader.getCellData("CiviCrm", "Email", 2));
	}
	
	public void givePhone() {
		phone.sendKeys(reader.getCellData("CiviCrm", "Phone", 2));
	}
	
	public void scrollDownPage() {
		js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
	public void savePage() {
		save.click();
	}
	
	public void verifySavedPage() {
		assertEquals("amitesh shukla | CiviCRM Sandbox on Drupal", driver.getTitle());
	}
}
