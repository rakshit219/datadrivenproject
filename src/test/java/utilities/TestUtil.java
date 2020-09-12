package utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import testbase.TestBase;

public class TestUtil extends TestBase {

	public static String screenshotName;

	public static void screenshot() throws IOException {
		File file1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Date d = new Date();
		screenshotName = d.toString().replace(" ", "_").replace(":", "_") + ".jpg";
		FileUtils.copyFile(file1, new File(System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\" + screenshotName));
	}

	@DataProvider(name = "dp")
	public Object[][] getData(Method m) {
		String sheetName =m.getName();
		int row = excel.getRowCount(sheetName);
		int col = excel.getColumnCount(sheetName);
		Object[][] data = new Object[row - 1][1];
		Hashtable<String,String> ht=null;
		for (int i = 2; i <= row; i++) {
			ht=new Hashtable<String, String>();
			for (int j = 0; j < col; j++) {
				ht.put(excel.getCellData(sheetName, j, 1), excel.getCellData(sheetName, j, i));
				data[i-2][0]=ht;
			}
		}
		return data;
	}
	
	public static boolean isTestRunnable(String testcase,ExcelReader excel){
		String sheetName="TestSuite";
		int row=excel.getRowCount(sheetName);
		for(int i=2;i<=row;i++){
			String tcid=excel.getCellData(sheetName, "TestCase", i);
			if(tcid.equalsIgnoreCase(testcase)){
				String runmode=excel.getCellData(sheetName, "Runmode", i);
				if(runmode.equalsIgnoreCase("Y")){
					return true;
				}else{
					return false;
				}
			}
		}
		return false;
	}
}
