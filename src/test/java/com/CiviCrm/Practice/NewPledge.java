package com.CiviCrm.Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class NewPledge {

	WebDriver driver;
	
	@Test
	public void testPledge() throws Exception {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/executables/chromedriver");
		driver = new ChromeDriver();
		driver.get("http://dmaster.demo.civicrm.org/");
		driver.findElement(By.id("edit-name")).sendKeys("demo");
		driver.findElement(By.id("edit-pass")).sendKeys("demo");
		driver.findElement(By.id("edit-submit")).click();
		Thread.sleep(3000);
		WebElement conti = driver.findElement(By.xpath("//*[@id='civicrm-menu']//li[contains(text(),'Contributions')]"));
		Actions act =new Actions(driver);
		act.moveToElement(conti).click().build().perform();
		WebElement pledge = driver.findElement(By.xpath("//*[@id='root-menu-div']//span[contains(text(),'Pledges')]"));
		WebElement newPledge = driver.findElement(By.xpath("//*[@id='root-menu-div']//a[contains(text(),'New Pledge')]"));
		Actions act2 = new Actions(driver);
		act2.moveToElement(pledge).build().perform();
		Thread.sleep(1000);
		Actions act3 = new Actions(driver);
		act3.moveToElement(newPledge).click().build().perform();
		Thread.sleep(1000);
		driver.findElement(By.id("select2-chosen-2")).click();
		WebElement contType = driver.findElement(By.xpath("//*[@id='select2-results-2']//a[contains(text(),'New Individual')]"));
		Actions act4 = new Actions(driver);
		act4.moveToElement(contType).click().build().perform();
		Thread.sleep(1000);
		driver.findElement(By.id("first_name")).sendKeys("ami");
		driver.findElement(By.id("last_name")).sendKeys("Test");
		Thread.sleep(1000);
		WebElement saveTab = driver.findElement(By.id("//button[@class='ui-button ui-corner-all ui-widget'][contains(text(),'Save')]"));
		Actions act5 = new Actions(driver);
		act5.moveToElement(saveTab).click().build().perform();
		Thread.sleep(2000);
	}
}
