package com.CiviCrm.PageFactory;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utilities.Xls_Reader;

public class NewMaillingPageFactory {
	
	WebDriver driver;
	
	JavascriptExecutor js;
	
	Xls_Reader reader = new Xls_Reader(System.getProperty("user.dir") + "/DataResources/DataFile.xlsx");
	
	@FindBy(xpath="//*[@id='civicrm-menu']//li[contains(text(),'Mailings')]")
	WebElement maillings;
	
	@FindBy(xpath="//*[@id='root-menu-div']//*[contains(text(),'New Mailing')]")
	WebElement newMailling;
	
	@FindBy(id="crmUiId_3")
	WebElement mailingName;
	
	@FindBy(id="select2-chosen-12")
	WebElement template;
	
	@FindBy(id="s2id_autogen12_search")
	WebElement searchTemplate;
	
	@FindBy(xpath="//*[@id='select2-results-12']")
	WebElement selectTemplate;
	
	@FindBy(id="s2id_autogen13")
	WebElement receipients;
	
	@FindBy(xpath="//*[@class='crmMailing-include'][contains(text(),'Advisory Board')]")
	WebElement 	selectReceipients;
	
	@FindBy(xpath="//*[@id='tab-mailing']//div[contains(text(),'Plain Text')]")
	WebElement plainText;
	
	@FindBy(id="crmUiId_2")
	WebElement textArea;
	
	@FindBy(xpath="//*[@id='crm-main-content-wrapper']//*[@ng-click='save().then(leave)']")
	WebElement saveDraft;
	
	@FindBy(id="mailing_name")
	WebElement findMailer;
	
	@FindBy(id="_qf_Search_refresh")
	WebElement search;

	@FindBy(xpath="//*[@id='crm-mailing_18']/td[1]")
	WebElement data;
	
	//Initializing the 
	public NewMaillingPageFactory(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickMailing() {
		Actions act = new Actions(driver);
		act.moveToElement(maillings).click().build().perform();
	}
	
	public void clickNewMailing() {
		Actions act = new Actions(driver);
		act.moveToElement(newMailling).click().build().perform();
	}
	
	public void giveMailingName() {
		mailingName.sendKeys(reader.getCellData("CiviCrm", "MailingName", 2));
	}
	
	public void openTemplate() {
		template.click();
		searchTemplate.sendKeys("sample civi");
	}
	
	public void selectingTemplate() {
		selectTemplate.click();
		/*Actions act = new Actions(driver);
		act.moveToElement(selectTemplate).click().build().perform();*/
	}
	
	
	
	public void selectRecipients() {
		receipients.click();
		receipients.sendKeys("Advisor");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Actions act = new Actions(driver);
		act.moveToElement(selectReceipients).click().build().perform();
	}
	
	public void scrollPageDown() {
		js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
	public void clickPlainText() {
		plainText.click();
	}
	
	public void giveTextArea() {
		textArea.sendKeys(reader.getCellData("CiviCrm", "TextForMail", 2));
	}
	
	public void clickSaveDraft() {
		saveDraft.click();
	}
	
	public void verifyPage() {
		Assert.assertEquals("Find Mailings | CiviCRM Sandbox on Drupal", driver.getTitle());
	}
	
	public void giveMailerName() {
		findMailer.sendKeys(reader.getCellData("CiviCrm", "SearchData", 2));
	}
	
	public void clickSearch() {
		search.click();
	}
}
