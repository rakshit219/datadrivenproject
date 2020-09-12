package testcases;

import java.util.Hashtable;

import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.Test;

import testbase.TestBase;
import utilities.TestUtil;

public class AddCustomerTest extends TestBase {

	@Test(dataProviderClass = TestUtil.class, dataProvider = "dp")
	public void addCustomerTest(Hashtable<String,String> data) {
		
		if(!TestUtil.isTestRunnable("AddCustomerTest", excel)){
			throw new SkipException("Skipping the testcase as runmode is No");
		}

		if(!data.get("Runmode").equalsIgnoreCase("Y")){
			throw new SkipException("Skipping the data as runmode is not Y");
		}
		
		click("acbtn");
		type("acfn", data.get("First Name"));
		type("acln", data.get("Last Name"));
		type("acpc", data.get("Post Code"));
		click("acsb");
		alert = driver.switchTo().alert();
		alert.accept();
		log.debug("Successfully added customer");
		Reporter.log("Successfully added customer");
	}

}
