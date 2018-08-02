package utilities;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class TestBase {

	protected static WebDriver driver;
	protected static Properties prop;
	protected static Logger log =  LogManager.getLogger(TestBase.class.getName());
	
	@BeforeTest
	// Parameter will get browser from testng.xml on which browser test to run
	@Parameters({"myBrowser"})
	public static void getBrowser(String myBrowser) {
		if (System.getProperty("os.name").contains("Window")) {
			log.info("=============Session Starts==========");
			if (myBrowser.equalsIgnoreCase("firefox")) {
				// https://github.com/mozilla/geckodriver/releases
				System.out.println(System.getProperty("user.dir"));
				System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir") + "/drivers/geckodriver.exe");
				driver = new FirefoxDriver();
				log.info("=============Application Starts==========");
		
			} else if (myBrowser.equalsIgnoreCase("chrome")) {
				// https://chromedriver.storage.googleapis.com/index.html
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "/drivers/chromedriver.exe");
				driver = new ChromeDriver();
				log.info("=============Application Starts==========");
				
			}
		} else if (System.getProperty("os.name").contains("Mac")) {
			log.info("=============Session Starts==========");
			log.info(System.getProperty("os.name"));
			if (myBrowser.equalsIgnoreCase("firefox")) {
				System.out.println(System.getProperty("user.dir"));
				System.setProperty("webdriver.gecko.driver" , System.getProperty("user.dir")+"/executables/geckodriver");
				driver = new FirefoxDriver();
				log.info("=============Application Starts==========");
				driver.manage().window().fullscreen();
				driver.navigate().to(TestBase.readConfigFile("URL4"));
			
			} else if (myBrowser.equalsIgnoreCase("chrome")) {
				log.info(System.getProperty("user.dir"));
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/executables/chromedriver");
				driver = new ChromeDriver();
				log.info("=============Application Starts==========");
				driver.manage().window().fullscreen();
				driver.navigate().to(TestBase.readConfigFile("URL4"));
				
			} else if(myBrowser.equalsIgnoreCase("safari")) {
				driver = new SafariDriver();
				log.info("=============Application Starts==========");
				//driver.manage().window().fullscreen();
				driver.navigate().to(TestBase.readConfigFile("URL4"));
			}
		}
	}
	
	
//	public static void openBrowser(String browserName, String URL) {
//		
//		//Reporter.log("=============Session Starts==========", true);
//		log.info("=============Session Starts==========");
//		
//		System.setProperty("webdriver.gecko.driver" , System.getProperty("user.dir")+"/executables/geckodriver");
//		if(browserName.equalsIgnoreCase("firefox")) {
//			driver =  new FirefoxDriver();
//		//Reporter.log("=============Application Starts==========", true);
//		log.info("=============Application Starts==========");
//		}
//		
//		else if(browserName.equalsIgnoreCase("chrome")) {
//			
//			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/executables/chromedriver");
//			driver = new ChromeDriver();
//			//Reporter.log("=============Application Starts==========", true);
//			log.info("=============Application Starts==========");
//		}
//		driver.manage().window().fullscreen();
//		driver.navigate().to(URL);
//		//return driver;
//	}
	
//	   public static void tearDown() {
//		   if(!(driver == null)) {
//			   driver.close();
//				//Reporter.log("=============Session Ends==========", true);
//				log.info("=============Session Ends==========");
//		   } else {
//			   log.info("=============No WebDriver Session Found==========");
//		   }
//		  
//			}
	   
	   @AfterClass
		public static void closeBrowser() {
			if (driver != null) {
				driver.quit();
				log.info("=============Session Ends==========");
			}
			 log.info("=============No WebDriver Session Found==========");
		}
	
	
	/*Below method is for loading the config.properties file*/
	  public static void loadConfigFile() {
		try {
			prop = new  Properties();
			File f = new File(System.getProperty("user.dir")+"/config.properties");
			FileReader reader = new FileReader(f);
			prop.load(reader);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	  
	/*Below is for reading the data from the file*/
	public static String readConfigFile(String data) {
		loadConfigFile();
		prop.get(data);
		return prop.getProperty(data);
	}
	
	//implicit wait
	public static WebElement waitForElement(By locator, int timeout) {
		WebElement element = null;
		try {
			System.out.println("waiting for max: " + timeout + " seconds for element to be visible");
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			System.out.println("Element appered on the page");
		}catch(Exception e) {
			System.out.println("Element not available on the page");
		}
		return element;
	}
	
	//explicit wait
		public static WebElement clickWhenReady(By locator, int timeout) {
			WebElement element = null;
			try {
				System.out.println("waiting for max: " + timeout + " seconds for element to be clickable");
				WebDriverWait wait = new WebDriverWait(driver, timeout);
				element = wait.until(ExpectedConditions.elementToBeClickable(locator));
				element.click();
				System.out.println("Element clicked on the page");
			}catch(Exception e) {
				System.out.println("Element not available on the page");
			}
			return element;
		}
	
}
