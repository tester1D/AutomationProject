package mobilePages;

import org.openqa.selenium.By;

import frameworkCommons.Commons;
import frameworkCommons.MobileCommons;
import io.appium.java_client.AppiumDriver;

public class HomePage {
	MobileCommons mobj = new MobileCommons();
    By addToCart =By.xpath("//android.view.ViewGroup[@content-desc='test-ADD TO CART']/android.widget.TextView");  
	By addToCartSuccess =By.xpath("//android.view.ViewGroup[@content-desc='test-Cart']/android.view.ViewGroup/android.widget.ImageView");

	public void clickAddToCard(AppiumDriver driver) {
		mobj.elementClick(driver, addToCart, 5);
	}
	
	public boolean verifyAddToCartSuccess(AppiumDriver driver) {
		return Commons.isDisplayed(driver, addToCartSuccess);
	}

}
