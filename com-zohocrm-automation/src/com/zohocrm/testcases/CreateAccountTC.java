package com.zohocrm.testcases;

import java.util.HashMap;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.zohocrm.basetest.basetest;
import com.zohocrm.util.DataReader;
import com.zohocrm.util.ExtentManager;

public class CreateAccountTC extends basetest {
	String TCName = "CreateAccountTestCase";

	@Test
	public void CreateAccountTestCase() {
		report = ExtentManager.prasad();
		test=report.startTest(TCName);

		test.log(LogStatus.INFO,"Started Executing testcase -"+TCName);

		test.log(LogStatus.INFO, "Loading config file");
		loadConfig();
		//calling TC data method
		HashMap<String, String> TCData = DataReader.getData(TCName);

		test.log(LogStatus.INFO, "Launching Browser");
		launchBrowser();

		test.log(LogStatus.INFO, "Navigating to URL");
		navigateTo();
		
		setText("FullName_ID", TCData.get("FullName"));
		setText("Email_NAME", TCData.get("Email"));
		setText("Password_XPATH", TCData.get("PWD"));
		
		test.log(LogStatus.INFO, "Taking Screenshot");
		takescreenshot();
		
	}
	@AfterMethod
	public void tearDown() {
		if(driver!=null) {
			driver.quit();	
		}		
		report.endTest(test);
		report.flush();
	}


}
