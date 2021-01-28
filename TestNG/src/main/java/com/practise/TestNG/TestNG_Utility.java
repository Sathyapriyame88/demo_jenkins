package com.practise.TestNG;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNG_Utility {
	static String dateformat=new SimpleDateFormat("YYYYMMddHHmmss").format(new Date());
	static WebDriver driver;
	static ExtentReports extent;
	static ExtentHtmlReporter rpt;
		public static void launchBrowser() throws Exception {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();// to maximize the window

		}

		public static void gototsalesforceURL() {
			driver.get("https://login.salesforce.com/");
		}

		public static void waitExplicitly(int iseconds, WebElement ele) {
			WebDriverWait wait = new WebDriverWait(driver, iseconds);
			wait.ignoring(NoSuchElementException.class,	ElementNotVisibleException.class);
			wait.until(ExpectedConditions.visibilityOf(ele));

		}

		public static void logintosalesforce() throws Exception {
			waitExplicitly(10, driver.findElement(By.id("username")));
			driver.findElement(By.id("username")).clear();
			driver.findElement(By.id("username")).sendKeys("spt@abc.com");
			// sending password
			waitExplicitly(10, driver.findElement(By.id("password")));
			driver.findElement(By.id("password")).clear();
			driver.findElement(By.id("password")).sendKeys("sathyasampu1");
			// click login button
			waitExplicitly(10, driver.findElement(By.id("Login")));
			driver.findElement(By.id("Login")).click();
			Thread.sleep(5000);

		}
		public static void quitBrowser() throws Exception {
			Thread.sleep(5000);
			driver.quit();
		}
		public static void extendreport(){
			extent=new ExtentReports();
			String path=System.getProperty("User.dir")+"//extendReport//"+"reports.html";
			rpt=new ExtentHtmlReporter(path);
			extent.attachReporter(rpt);
			
		}
		public static String takeScreenShot() throws IOException {
			TakesScreenshot screenshot=(TakesScreenshot)driver;
			String spath=System.getProperty("user.dir")+"//extendReport//ScreenShot"+dateformat+"Screenshot.PNG";
			File srcfile=screenshot.getScreenshotAs(OutputType.FILE);
			File dstfile=new File(spath);
			FileUtils.copyFile(srcfile, dstfile);
	        return spath;
			
		}

	
}
