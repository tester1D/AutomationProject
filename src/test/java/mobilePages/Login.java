package mobilePages;

import org.openqa.selenium.By;

import frameworkCommons.Commons;
import frameworkCommons.MobileCommons;
import io.appium.java_client.AppiumDriver;

public class Login {

	MobileCommons mobj = new MobileCommons();
	
	By usernameElement=By.xpath("//android.widget.EditText[@content-desc='test-Username']");
    By passwordElement=By.xpath("//android.widget.EditText[@content-desc='test-Password']");
    By loginButtonElement=By.xpath("//android.view.ViewGroup[@content-desc='test-LOGIN']/android.widget.TextView");
    By homePageIcon=By.xpath("//android.view.ViewGroup[@content-desc='test-Menu']/android.view.ViewGroup/android.widget.ImageView");
    By addToCart =By.xpath("//android.view.ViewGroup[@content-desc='test-ADD TO CART']/android.widget.TextView");
    By invalidLoginError=By.xpath("//android.view.ViewGroup[@content-desc='test-Error message']/android.widget.TextView");
    
	public void enterUsername(AppiumDriver driver, String username) {
	
		mobj.elementClick(driver, usernameElement, 10);
		mobj.setValue(driver, usernameElement, username, 10);
	}
	
	public void enterPassword(AppiumDriver driver, String password) {
		mobj.elementClick(driver, passwordElement, 10);
		mobj.setValue(driver, passwordElement, password, 5);
	}
	
	public void clickLogin(AppiumDriver driver) {
		mobj.elementClick(driver, loginButtonElement, 5);
	}
	
	public boolean homePageTitleDisplayed(AppiumDriver driver) {
		return Commons.isDisplayed(driver, homePageIcon);
	}
	
	public boolean errorMsg(AppiumDriver driver) {
		return Commons.isDisplayed(driver, invalidLoginError);
	}
	
	public void scrollPage(AppiumDriver driver) {
		mobj.scroll(driver, homePageIcon);
	}
}
