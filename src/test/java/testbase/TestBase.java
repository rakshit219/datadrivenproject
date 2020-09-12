package testbase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utilities.ExcelReader;
import utilities.ExtentManager;

public class TestBase {

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties or = new Properties();
	public static FileInputStream fis;
	public static Logger log=Logger.getLogger("devpinoyLogger");
	public static Alert alert;
	public ExtentReports report=ExtentManager.getInstance();
	public static ExtentTest test;
	public static WebElement dropdown;
	public static ExcelReader excel=new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\excel\\testdata.xlsx");
	public static String browser;
	
	@BeforeSuite
	public void setUp() {

		if (driver == null) {
			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.debug("Config property loaded succesfully");
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				or.load(fis);
				log.debug("OR property loaded succesfully");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(System.getenv("browser")!=null && !System.getenv("browser").isEmpty())
			{
				browser=System.getenv("browser");
			}
			else
			{
				browser=config.getProperty("browser");
			}
			config.setProperty("browser", browser);
			
			if (config.getProperty("browser").equals("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
				driver = new ChromeDriver();
				log.debug("Chrome browser initiated successfully");
			} else if (config.getProperty("browser").equals("firefox")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\geckodriver.exe");
				//driver = new FirefoxDriver();
			} else if (config.getProperty("browser").equals("ie")) {
				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}

			driver.get(config.getProperty("testurl"));
			log.debug("Navigated to site: "+config.getProperty("testurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicitwait")),
					TimeUnit.SECONDS);
		}

	}
	
	public boolean isElementPresent(By by){
		try{
			driver.findElement(by);
		    return true;
		    }
		catch(NoSuchElementException e){
			return false;
		}
	}
	
	public void click(String locator){
		driver.findElement(By.xpath(or.getProperty(locator))).click();
		test.log(LogStatus.INFO, "Clicked on : "+locator);
	}
	
	public void type(String locator,String value){
		driver.findElement(By.xpath(or.getProperty(locator))).sendKeys(value);
		test.log(LogStatus.INFO, "Typing on : "+locator+" entered value "+value);
	}
	
	public void select(String locator,String value){
		dropdown=driver.findElement(By.xpath(or.getProperty(locator)));
		Select s=new Select(dropdown);
		s.selectByVisibleText(value);
		test.log(LogStatus.INFO, "Selecting from dropdown : "+locator+" value "+value);
	}

	@AfterSuite
	public void tearDown() {
		if (driver != null) {
			driver.quit();
			log.debug("Site closed successfully");
		}
	}
}
