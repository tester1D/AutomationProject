package testPackage;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.testng.Assert;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.model.Log;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.ServerArgument;
import mobileCore.CreateDriverSession;
import mobilePages.HomePage;
import mobilePages.Login;
import reporting.screenshot;

public class SunAndSandSports_MobileTest {
	AppiumDriver driver = null;
	AppiumDriverLocalService service;
	ExtentReports report;
	ExtentTest test;
	Login logObj = new Login();
	HomePage homObj = new HomePage();

	@BeforeSuite
	public void StartReport() {
		report = new ExtentReports(System.getProperty("user.dir") + "\\ExtendReport\\Reports\\"+getClass().getSimpleName()+".html");
	}

	@BeforeMethod
	public void SetDesiredCapabilities() {
		try {
			driver=CreateDriverSession.setCapabilities(test, "Android");
			System.out.println("This is driver"+driver);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Unable to set capabilities - Please make sure that valid values are provided");
			screenshot.getScreenshot(driver, "Setting up of driver session failed");
			Assert.fail();
		}
	}


	@Parameters({ "username", "password" })
	@Test(priority = 1)
	public void ValidLogin(String username, String password) {

		try {
			test = report.startTest("Valid Login");
			driver.manage().timeouts().implicitlyWait(29, TimeUnit.SECONDS);
			logObj.enterUsername(driver, username);
			logObj.enterPassword(driver, password);
			logObj.clickLogin(driver);
			boolean successLoginflag=logObj.homePageTitleDisplayed(driver);
			Assert.assertTrue(successLoginflag);
			test.log(LogStatus.PASS, "Successfully validated valid login. Username used is "+username+", Password Used is "+password);
		} catch (Exception e) {
			System.out.println("This is Exception"+e);
			test.log(LogStatus.FAIL, "Valid user is unable to login");
			screenshot.getScreenshot(driver, "ValidLoginFailed");
			Assert.fail();
		}
	}
	
	@Parameters({ "invalidUsername", "invalidPassword" })
	@Test(priority = 2)
	public void InvalidLogin(String invalidUsername, String invalidPassword) {

		try {
			test = report.startTest("InvalidLogin");
			driver.manage().timeouts().implicitlyWait(29, TimeUnit.SECONDS);
			logObj.enterUsername(driver, invalidUsername);
			logObj.enterPassword(driver, invalidPassword);
			logObj.clickLogin(driver);
			Assert.assertTrue(logObj.errorMsg(driver));
			test.log(LogStatus.PASS, "Successfully validated  Invalid Login. Username used is "+invalidUsername+", Password Used is "+invalidPassword);
		} catch (Exception e) {
			System.out.println("This is Exception"+e);
			test.log(LogStatus.FAIL, "Invalid User is able to Login");
			screenshot.getScreenshot(driver, "InvalidLoginFailed");
			Assert.fail();
		}
	}
	

	@Parameters({ "username", "password" })
	@Test(priority = 3)
	public void VerifyAddToCartButtonFunctionality(String username, String password) {

		try {
			test = report.startTest("AddToCartButtonFunctionality");
			driver.manage().timeouts().implicitlyWait(29, TimeUnit.SECONDS);
			logObj.enterUsername(driver, username);
			logObj.enterPassword(driver, password);
			logObj.clickLogin(driver);
			homObj.clickAddToCard(driver);
			Assert.assertTrue(homObj.verifyAddToCartSuccess(driver));
			test.log(LogStatus.PASS, "Successfully validated Add to cart functionality");			
		} catch (Exception e) {
			System.out.println("This is Exception"+e);
			test.log(LogStatus.FAIL, "Invalid User is able to Login");
			screenshot.getScreenshot(driver, "InvalidLoginFailed");
			Assert.fail();
		}
	}
	
	@AfterMethod(alwaysRun = true)
	public void TearDown() {
		driver.quit();
	}

	@AfterSuite()
	public void cleanReport() {
		report.endTest(test);
		report.flush();
	}

}
