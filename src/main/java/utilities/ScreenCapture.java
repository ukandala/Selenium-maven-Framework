package utilities;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import utilities.TestBase;



public class ScreenCapture extends TestBase {
	
	public static String screenShotName;
	
	public static void screenCapture(String methodName) throws IOException {
		
		Calendar cal = new GregorianCalendar();
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		int sec = cal.get(Calendar.SECOND);
		int min = cal.get(Calendar.MINUTE);
		int date = cal.get(Calendar.DATE);
		int day = cal.get(Calendar.HOUR_OF_DAY);
		
		//File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		screenShotName = methodName+"_"+year+"_"+date+"_"
		+(month+1)+"_"+day+"_"+min+"_" +sec+".png";
		FileUtils.copyFile(source, new File("./ScreenShots/"+ screenShotName));
		
		/*Date d = new Date();
		screenShotName = d.toString().replace(":", "_").replace(" ", "_");

		FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "./ScreenShot/" +methodName+"_"+ screenShotName + ".png"));*/
	}
}