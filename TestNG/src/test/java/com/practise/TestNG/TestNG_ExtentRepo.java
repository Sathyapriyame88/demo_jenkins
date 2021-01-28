package com.practise.TestNG;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class TestNG_ExtentRepo extends TestNG_Utility{
	static ExtentReports extent;
	static ExtentHtmlReporter rpt;
	ExtentTest test;
	String dateformat = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	@BeforeSuite
	public void initialization() throws Exception {
		launchBrowser();
		gototsalesforceURL();
		extent=new ExtentReports();
		rpt=new ExtentHtmlReporter(System.getProperty("user.dir")+"//extendReport//"+dateformat+"reports.html");
		extent.attachReporter(rpt);
	}
	
		
	

	
	@Test
	public void login() throws Exception {
		test = extent.createTest("login");
		waitExplicitly(10, driver.findElement(By.id("username")));
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("spt@abc.com");
	test.info("Username Entered");
		waitExplicitly(10, driver.findElement(By.id("password")));
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("sathyasampu1");
	test.info("Password Entered");
		waitExplicitly(10, driver.findElement(By.id("Login")));
		driver.findElement(By.id("Login")).click();
		test.log(Status.PASS, "Login to salesforce");
		Thread.sleep(5000);
		
	}
	@AfterSuite
	public void closeReport() {
		extent.flush();
		driver.quit();
	}

	

}
