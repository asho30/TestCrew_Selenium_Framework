package com.testcrew.testcases;

import java.awt.AWTException;
import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import com.testcrew.base.TestBase;
import com.testcrew.pages.SubscribePage;
import com.testcrew.utils.Plans;
import com.testcrew.utils.TestUtil;

public class TestCrew_Test extends TestBase {

	String country, currency, litePrice, classicPrice, premiumPrice;
	int startRowNumber, endRowNumber;
	SubscribePage subscribePage;

	public TestCrew_Test() {
		super();
	}

	@BeforeSuite()
	public void setExtent() {

		extent = new ExtentReports("AutoReport/index.html", true);
		extent.addSystemInfo("Framework", "Page Object");
		extent.addSystemInfo("Author", "Ahmed Ashour");
		extent.addSystemInfo("Enviroment", "Win 11");
		extent.addSystemInfo("Test", "Apar Tech Test");

	}

	@BeforeMethod
	public void start(Method method) throws InterruptedException, Throwable {
		logger = extent.startTest(method.getName());
		initialization(prop.getProperty("url")); // From config.properties
		// The following for multiple data lines, but for a single data line row number = end row number = 1.
		startRowNumber = Integer.parseInt(prop.getProperty("startRowDataNumber"));
		endRowNumber = Integer.parseInt(prop.getProperty("endRowDataNumber"));
		pagesInitialization();
	}

	@Test(priority = 1)
	public void TC_ValidateSubscriptionPlansDetails() throws Exception {
		for (int rowNumber = startRowNumber; rowNumber <= endRowNumber; rowNumber++) {
			// read test data
			readTestData(rowNumber);
			changeCountry();
			validateSubscriptionPlansDetails();
		}
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws Throwable {

		if (result.getStatus() == ITestResult.FAILURE) {

			logger.log(LogStatus.FAIL, "Test Failed " + result.getThrowable());
			String picturePath = TestUtil.TakeSnapshot(driver, result.getName());
			logger.log(LogStatus.FAIL, logger.addScreenCapture(picturePath));

		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(LogStatus.SKIP, "Test case Skipped is " + result.getName());

		} else {
			logger.log(LogStatus.PASS, "Test passed");
			String picturePath = TestUtil.TakeSnapshot(driver, result.getName());
			logger.log(LogStatus.PASS, logger.addScreenCapture(picturePath));

		}
		endTest();
	}

	public void endTest() {
		extent.endTest(logger);
		driver.close();
	}

	@AfterSuite()
	public void endReport() {

		extent.flush();
		extent.close();
	}

	public void pagesInitialization() {
		subscribePage = new SubscribePage();
	}

	public void readTestData(int rowNumber) throws IOException {
		String inputFileName = prop.getProperty("testDataFolderPath");
		String sheetName = prop.getProperty("sheetName");
		country = TestUtil.readFromExcelFile(inputFileName, sheetName, "Country", rowNumber);
		currency = TestUtil.readFromExcelFile(inputFileName, sheetName, "Currency", rowNumber);
		litePrice = TestUtil.readFromExcelFile(inputFileName, sheetName, "Lite Price", rowNumber);
		classicPrice = TestUtil.readFromExcelFile(inputFileName, sheetName, "Classic Price", rowNumber);
		premiumPrice = TestUtil.readFromExcelFile(inputFileName, sheetName, "Premium Price", rowNumber);
		return;
	}
	
	public void changeCountry() throws Exception {
		subscribePage.changeCountry(country);
	}
	
	public void validateSubscriptionPlansDetails() throws InterruptedException, AWTException {
		Assert.assertEquals(subscribePage.getCurrency(Plans.LITE), currency);
		Assert.assertEquals(subscribePage.getCurrency(Plans.CLASSIC), currency);
		Assert.assertEquals(subscribePage.getCurrency(Plans.PREMIUM), currency);
		Assert.assertEquals(subscribePage.getPrice(Plans.LITE), litePrice);
		Assert.assertEquals(subscribePage.getPrice(Plans.CLASSIC), classicPrice);
		Assert.assertEquals(subscribePage.getPrice(Plans.PREMIUM), premiumPrice);
	}

}
