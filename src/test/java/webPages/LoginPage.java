package webPages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import frameworkCommons.Commons;

public class LoginPage {

	By emailId = By.name("guestEmail");
	By continueAsGuestBtn = By.xpath("//button[contains(text(),'Continue As Guest')]");
	By signUpPopFrame = By.xpath("//*[@id='preview-notification-frame']");
	By closeButton = By.id("NC_CLOSE_ICON");
	By declineButton = By.xpath("//button[contains(@class,'decline')]");
	By notNowBtn = By.xpath("//input[contains(@name,'notnow')]");
	By notNowFrame = By.xpath("//iframe[@class='__st_preview_frame_bpn']");

	public void setMailId(WebDriver driver) {
		Commons.waitForElement(driver, emailId, 10);
		driver.findElement(emailId).sendKeys("diya.elsa@yahoo.com");
	}

	public void clickSubmit(WebDriver driver) {
		Commons.waitForElement(driver, continueAsGuestBtn, 10);
		driver.findElement(continueAsGuestBtn).click();
	}

	public boolean verifyContinueAsGuestBtnDisplayed(WebDriver driver) {
		return Commons.isDisplayed(driver, continueAsGuestBtn);
	}

	public void launchBrowser(WebDriver driver) throws InterruptedException{
		driver.get("http://en-ae.sssports.com");
		driver.manage().window().maximize();
		handlePageLoadPopups(driver);
	}

	public boolean userGettingNavigatedToLoginPage(WebDriver driver) {
		return Commons.isDisplayed(driver, emailId);
	}

	public void handlePageLoadPopups(WebDriver driver) {
		try {
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			Commons.WaitForAjax(driver);
			if (Commons.isDisplayed(driver, signUpPopFrame)) {
				switchToFrameByName(driver, "preview-notification-frame");
				if (Commons.isDisplayed(driver, closeButton))
					driver.findElement(closeButton).click();
			}
			if (Commons.isDisplayed(driver, declineButton))
				driver.findElement(declineButton).click();
			if (Commons.isDisplayed(driver, notNowFrame)) {
				driver.switchTo().frame(driver.findElement(notNowFrame));
				driver.findElement(notNowBtn).click();
			}
			driver.switchTo().defaultContent();
		} catch (Exception ex) {
			throw ex;
		}
	}

	public void switchToFrameByName(WebDriver driver, String frameName) {
		driver.switchTo().frame(frameName);
	}
}