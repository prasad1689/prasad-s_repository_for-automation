package com.zohocrm.basetest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class basetest {

	public WebDriver driver;
	public FileInputStream fis;
	public Properties prop;
	public ExtentReports report;
	public ExtentTest test;

	public void loadConfig() {
		try {
			fis = new FileInputStream("config.properties");
			prop = new Properties();
			prop.load(fis);

		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public void takescreenshot() {

		try {
			Date d =new Date();
			String Timestamp=d.toString().replace(" ","_").replace(":", "_");
			String Screenshotpath=System.getProperty("user.dir")+"//ScreenShot//s_"+Timestamp+".png";
			//Screenshot method
			TakesScreenshot TS = (TakesScreenshot)driver;
			File F = TS.getScreenshotAs(OutputType.FILE);
			FileHandler.copy(F, new File(Screenshotpath));			
			test.log(LogStatus.INFO, test.addScreenCapture(Screenshotpath));
		}catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void launchBrowser() {
		if(prop.getProperty("ExecutionBrowser").equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//Drivers//chromedriver.exe");
			driver = new ChromeDriver();
		} else if(prop.getProperty("ExecutionBrowser").equals("IE")) {
			driver = new InternetExplorerDriver();
			System.setProperty("webdriver.ie.driver", 
					System.getProperty("user.dir")+"//Drivers//chromedriver.exe");

		}else {
			System.err.println("Invalid Browser");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

	}

	public void navigateTo() {
		driver.get(prop.getProperty("APPURL"));
	}

	public WebElement getElement(String locatorkey) {
		WebElement elm;
		if(locatorkey.endsWith("_ID")) {
			elm=driver.findElement(By.id(prop.getProperty(locatorkey)));
		}else if(locatorkey.endsWith("_NAME")) {
			elm=driver.findElement(By.name(prop.getProperty(locatorkey)));
		}else if(locatorkey.endsWith("_XPATH")) {
			elm=driver.findElement(By.xpath(prop.getProperty(locatorkey)));
		}else {
			System.out.println("invalid locator specified");
			elm=null;
		}
		return elm;
	}

	public void setText(String locatorkey,String value) {
		WebElement elm = getElement(locatorkey);
		elm.sendKeys(value);
	}

	public void click(String locatorkey) {
		WebElement elm = getElement(locatorkey);
		elm.click();
	}

	public String getattribute(String locatorkey,String attributeName) {
		WebElement Elm = getElement(locatorkey);
		return Elm.getAttribute(attributeName);
	}

	public String getText(String locatorkey) {
		WebElement elm = getElement(locatorkey);
		return elm.getText();
	}



}
