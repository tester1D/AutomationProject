package testPackage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import frameworkCommons.Commons;
import reporting.screenshot;
import webPages.ContactDetails;
import webPages.HomePage;
import webPages.LoginPage;

public class SunAndSandSports_WebTest {

	WebDriver driver;

	ExtentReports report;
	ExtentTest test;
	LoginPage logObj = new LoginPage();
	HomePage homeObj = new HomePage();
	Commons comObj = new Commons();
	ContactDetails contactObj = new ContactDetails();
	
	@BeforeSuite
	public void startReport() {		
		report = new ExtentReports(System.getProperty("user.dir") + "\\ExtendReport\\Reports\\"+getClass().getSimpleName()+".html");
	}
	
	@BeforeMethod
	public void setUp() {
	
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@Test(priority = 1)
	public void launchBrowser() {

		try {
			test = report.startTest("launchBrowser");
			logObj.launchBrowser(driver);
			test.log(LogStatus.PASS, "Navigated to the specified URL");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "launch Browser Failed");
			screenshot.getScreenshot(driver, "LaunchBrowserFailed");
			Assert.fail();
		}
	}

	@Parameters({ "category"})
	@Test(priority = 2)
	public void verifyAvailabilityOfITem(String category) {

		try {
			test = report.startTest("verifyAvailabilityOfITem");
			logObj.launchBrowser(driver);
			homeObj.clickRequiredOption(driver, category);
			String expPageTitle = homeObj.getMenHomePageTitle(driver);
			Assert.assertEquals(expPageTitle, "ALL MEN'S. RIGHT HERE.",
					"On clicking on 'Men' options are not getting displayed");
			homeObj.clickShopAll(driver);
			Assert.assertTrue(homeObj.getAllItemsCount(driver) > 0, "Items are not available in the page");
			test.log(LogStatus.PASS, "Availability Of Item Validated");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Items are not available");
			screenshot.getScreenshot(driver, "verifyAvailabilityOfITem");
		}

	}

	@Parameters({ "category", "productSize" })
	@Test(priority = 3, enabled = false)
	public void verifyAddToButtonColorChangeFunctionality(String category, String productSize) {

		try {
			test = report.startTest("verifyAddToButtonColorChangeFunctionality");
			logObj.launchBrowser(driver);
			homeObj.clickRequiredOption(driver, category);
			homeObj.clickShopAll(driver);
			homeObj.clickOnAnyProduct(driver);
			Assert.assertEquals(homeObj.getAddToCartButtonColor(driver), "rgba(228, 228, 228, 1)");
			homeObj.selectProductSize(driver, productSize);
			String buttonColorAfterAdd = homeObj.getAddToCartButtonColor(driver);
			Assert.assertEquals(buttonColorAfterAdd, "rgba(242, 188, 65, 1)");

			test.log(LogStatus.PASS, "Successfully validated 'Add to Item' Button Color Change Functionality");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "'Add to Item' Button Color is not Changing ");
			screenshot.getScreenshot(driver, "verifyAddToButtonColorChangeFunctionality");
		}

	}

	@Parameters({ "category", "productSize" })
	@Test(priority = 4)
	public void verifyCartGotUpdated(String category, String productSize) {
		try {
			test = report.startTest("verifyCartGotUpdated");
			logObj.launchBrowser(driver);
			homeObj.clickRequiredOption(driver, category);
			homeObj.clickShopAll(driver);
			homeObj.clickOnAnyProduct(driver);
			homeObj.selectProductSize(driver, productSize);
			homeObj.clickAddtoBag(driver);
			Assert.assertTrue(homeObj.getCartItemCount(driver) > 0, "Item Got added to cart");
			homeObj.clickOnCartIcon(driver);
			Assert.assertEquals(homeObj.getCountInsideCartCount(driver), 1);
			test.log(LogStatus.PASS, "Cart is getting updated on adding items to cart");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Cart is not getting updated on adding items to cart\"");
			screenshot.getScreenshot(driver, "verifyCartGotUpdated");
		}
	}
	
	@Parameters({ "category", "productSize" })
	@Test(priority = 5)
	public void validationofItemsAdded(String category, String productSize) {

		try {
			String pBrand, pName, pPrice, cBrand, cName, cPrice;
			test = report.startTest("validationofItemsAdded");
			logObj.launchBrowser(driver);
			homeObj.clickRequiredOption(driver, category);
			homeObj.clickShopAll(driver);
			homeObj.clickOnAnyProduct(driver);
			homeObj.selectProductSize(driver, productSize);
			homeObj.clickAddtoBag(driver);
			homeObj.closeCheckOutPopUp(driver);
			pBrand = homeObj.getProductBrand(driver);
			pName = homeObj.getProductName(driver);
			pPrice = homeObj.getProductPrice(driver);
			homeObj.clickOnCartIcon(driver);
			cBrand = homeObj.getProductBrand(driver);
			cName = homeObj.getProductName(driver);
			cPrice = homeObj.getProductPrice(driver);
			Assert.assertEquals(pBrand, cBrand, "Brand Name selected and the one displayed in cart is not matching");
			Assert.assertEquals(pName, cName, "Product Name selected and the one displayed in cart is not matching");
			Assert.assertEquals(pPrice, cPrice, "Product Name selected and the one displayed in cart is not matching");
			test.log(LogStatus.PASS, "Successfully validated : Brand Name, Product Name and Price of Product");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Validation Failed For : Brand Name, Product Name and Price of Product");
			screenshot.getScreenshot(driver, "validationofItemsAdded");		
		}
	}

	@Parameters({ "category", "productSize" })
	@Test(priority = 6)
	public void verifyCheckOutFunctionality(String category, String productSize) {
		try {
			test = report.startTest("verifyCheckOutFunctionality");
			logObj.launchBrowser(driver);
			homeObj.clickRequiredOption(driver, category);
			homeObj.clickShopAll(driver);
			homeObj.clickOnAnyProduct(driver);
			homeObj.selectProductSize(driver, productSize);
			homeObj.clickAddtoBag(driver);
			homeObj.clickOnCartIcon(driver);
			Assert.assertTrue(homeObj.verifyCheckOutButtonDisplayed(driver), "CheckOut button is not available");
			homeObj.clickCheckOutSecurely(driver);
			Assert.assertTrue(logObj.userGettingNavigatedToLoginPage(driver),
					"On clicking on checkout securely user is not getting navigated to login Page");
			test.log(LogStatus.PASS, "Checkout securely functionality validated successfully");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Checkout securely functionality failed ");
			screenshot.getScreenshot(driver, "verifyCheckOutFunctionality");
		}
	}
	
	@Parameters({ "category", "productSize" })
	@Test(priority = 7)
	public void verifyContinueAsGuestFunctionalty(String category, String productSize) {
		try {
			test = report.startTest("verifyContinueAsGuestFunctionalty");
			logObj.launchBrowser(driver);
			homeObj.clickRequiredOption(driver, category);
			homeObj.clickShopAll(driver);
			homeObj.clickOnAnyProduct(driver);
			homeObj.selectProductSize(driver, productSize);
			homeObj.clickAddtoBag(driver);
			homeObj.clickOnCartIcon(driver);
			homeObj.clickCheckOutSecurely(driver);
			logObj.setMailId(driver);
			Assert.assertTrue(logObj.verifyContinueAsGuestBtnDisplayed(driver),
					"Continue As Guest Button is not Displayed");
			logObj.clickSubmit(driver);
			test.log(LogStatus.PASS, "Continue As Guest Functionalty  validated successfully");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Continue As Guest Functionalty Failed");
			screenshot.getScreenshot(driver, "verifyContinueAsGuestFunctionalty");
		}
	}

	@Parameters({  "category", "productSize","contactNumber", "title", "firstName", "lastName", "region", "addressLine" })
	@Test(priority = 8)
	public void verifyEnteringCheckoutDetails(String category, String productSize, String contactNumber, String title, String firstName, String lastName,
			String region, String addressLine) {
		try {
			test = report.startTest("verifyEnteringCheckoutDetails");
			logObj.launchBrowser(driver);
			homeObj.clickRequiredOption(driver, category);
			homeObj.clickShopAll(driver);
			homeObj.clickOnAnyProduct(driver);
			homeObj.selectProductSize(driver, productSize);
			homeObj.clickAddtoBag(driver);
			homeObj.clickOnCartIcon(driver);
			homeObj.clickCheckOutSecurely(driver);
			logObj.setMailId(driver);
			logObj.clickSubmit(driver);
			contactObj.setContactNumber(driver, contactNumber);
			contactObj.setTitle(driver, title);
			contactObj.setFirstName(driver, firstName);
			contactObj.setLastName(driver, lastName);
			contactObj.setRegion(driver, region);
			contactObj.setAddressLine(driver, addressLine);
			test.log(LogStatus.PASS, "Entering of Checkout Details Validated");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Checkout Details Validation Failed");
			screenshot.getScreenshot(driver, "verifyEnteringCheckoutDetails");
		}
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();

	}
	@AfterSuite()
	public void cleanReport() {
		report.endTest(test);
		report.flush();
	}
}