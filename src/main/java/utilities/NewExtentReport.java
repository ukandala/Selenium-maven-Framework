 package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestNGListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class NewExtentReport implements ITestNGListener{

	public WebDriver driver;
	public ExtentReports extent;
	public ExtentTest test;
	
	
	public void setExtent() {
		extent = new ExtentReports(System.getProperty("user.dir") +"/test-output/NewExtent.html", true);
		extent.addSystemInfo("Host name", "Amitesh PC");
		extent.addSystemInfo("User name", "Amitesh Shukla");
		extent.addSystemInfo("OS", "Windows");
	}
	
	public void tearDown(ITestResult result) throws IOException {
		test = extent.startTest(result.getMethod().getMethodName());
		if(result.getStatus()==ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, "Test Case Failed " + result.getName());
			test.log(LogStatus.FAIL, "Test Case Failed " + result.getThrowable());
			
			String screenShotPath = NewExtentReport.getScreenShot(driver, result.getName());
			test.log(LogStatus.FAIL, test.addScreenCapture(screenShotPath));
		}
		else if(result.getStatus()==ITestResult.SUCCESS) {
			test.log(LogStatus.PASS, "Test Case Passed " + result.getName());
			}
			
			else if(result.getStatus()==ITestResult.SKIP) {
			test.log(LogStatus.SKIP, "Test Case Skiped " + result.getName());
	}
	
		extent.endTest(test);
	}
	
	public static String getScreenShot(WebDriver driver, String screenShotName) throws IOException {
		String date =  new SimpleDateFormat("yyyymmddmmss").format(new Date());
		TakesScreenshot shot = (TakesScreenshot)driver;
		File source = shot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/ScreenShots/" + screenShotName + date + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
	
	public void endReports() {
		extent.flush();
		extent.close();
	}
	
}
