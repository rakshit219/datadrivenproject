package testcases;

import java.util.Hashtable;

import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.Test;

import testbase.TestBase;
import utilities.TestUtil;

public class OpenAccountTest extends TestBase {
	
	@Test(dataProviderClass = TestUtil.class, dataProvider = "dp")
	public void openAccountTest(Hashtable<String,String> data){
		if(!TestUtil.isTestRunnable("OpenAccountTest", excel)){
			throw new SkipException("Skipping the testcase as runmode is No");
		}
		click("oabtn");
		select("oacn",data.get("Customer"));
		select("oacu",data.get("Currency"));
		click("oapb");
		alert=driver.switchTo().alert();
		alert.accept();
		log.debug("Successfully opened customer account");
		Reporter.log("Successfully opened customer account");
		System.out.println("Git commit addition");
	
	}

}
