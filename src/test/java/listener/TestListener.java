package listener;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.relevantcodes.extentreports.LogStatus;

import testbase.TestBase;
import utilities.MonitoringMail;
import utilities.TestConfig;
import utilities.TestUtil;

public class TestListener extends TestBase implements ITestListener,ISuiteListener {
	
	public void onTestStart(ITestResult result) {
		test=report.startTest(result.getName().toUpperCase());

	}

	public void onTestSuccess(ITestResult result) {
	
		test.log(LogStatus.PASS, result.getName().toUpperCase()+" PASS");
		report.endTest(test);
		report.flush();
	}

	public void onTestFailure(ITestResult result) {
		System.setProperty("org.uncommons.reportng.escape-output","false");
	try {
		TestUtil.screenshot();
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	test.log(LogStatus.FAIL, result.getName().toUpperCase()+" FAIL WITH EXCEPTION : "+result.getThrowable());
	test.log(LogStatus.INFO, test.addScreenCapture(TestUtil.screenshotName));
	Reporter.log("Click to see Screenshot");
	Reporter.log("<a target='_blank' href="+TestUtil.screenshotName+">Screenshot</a>");
	Reporter.log("<br/>");
	Reporter.log("<a target='_blank' href="+TestUtil.screenshotName+"><img src="+TestUtil.screenshotName+" height=200 width=200></img></a>");
	report.endTest(test);
	report.flush();
	
	}

	public void onTestSkipped(ITestResult result) {

		test.log(LogStatus.SKIP, result.getName().toUpperCase()+" SKIP");
		report.endTest(test);
		report.flush();
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		

	}

	public void onStart(ITestContext context) {
		

	}

	public void onFinish(ITestContext context) {
		

	}
	public void onFinish(ISuite arg0){
		/*MonitoringMail mail=new MonitoringMail();
		try {
			mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, TestConfig.messageBody);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	public void onStart(ISuite arg0){
		
	}
}
