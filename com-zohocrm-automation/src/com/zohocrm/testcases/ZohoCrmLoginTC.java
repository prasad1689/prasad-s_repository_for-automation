package com.zohocrm.testcases;

import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.zohocrm.basetest.basetest;
import com.zohocrm.util.ExtentManager;

public class ZohoCrmLoginTC extends basetest {
	
	String TCName = "ZohoCRMLoginValidationTestCase";
	@Test
	public void ZohoCrmLoginTestCase() {
		
			report = ExtentManager.prasad();
			test=report.startTest(TCName);
			
			test.log(LogStatus.INFO,"Sarted Executing testcase -"+TCName);
			report.endTest(test);
			report.flush();
		}
		
	}


