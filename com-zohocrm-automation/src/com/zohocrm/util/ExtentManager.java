package com.zohocrm.util;

import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	
	private static ExtentReports report = null;
	
	public static ExtentReports prasad() {//here return type is ExtentReports
		
		if(report== null) {
			Date d =new Date();
			String Timestamp=d.toString().replace(" ","_").replace(":", "_");
			String reportpath=System.getProperty("user.dir")+"//Reports//r_"+Timestamp+".html";
			
			report = new ExtentReports(reportpath);
		}
		return report;
	}
	
	
	

}
