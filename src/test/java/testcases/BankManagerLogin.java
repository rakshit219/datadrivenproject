package testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.Test;

import testbase.TestBase;
import utilities.TestUtil;

public class BankManagerLogin extends TestBase {

	
	@Test()
	public void bankManagerLogin(){
		if(!TestUtil.isTestRunnable("BankManagerLogin", excel)){
			throw new SkipException("Skipping the testcase as runmode is No");
		}
		
		click("bmbtn");
		Assert.assertTrue(isElementPresent(By.xpath(or.getProperty("acbtn"))),"Login not successful");
		log.debug("Successfully clicked on Bank Manager Login");
		Reporter.log("Successfully clicked on Bank Manager Login");
	}
	
	
}
