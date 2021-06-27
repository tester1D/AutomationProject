package frameworkCommons;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class MobileCommons {

	static WebDriverWait wait;
	
	AppiumDriver driver;

	public void setValue(AppiumDriver driver, By  locator, String value, int timeOut) {
		MobileElement element=(MobileElement) driver.findElement(locator);
		waitForElement(driver,element,timeOut);
	    element.sendKeys(value);
	}
	public void scroll(AppiumDriver driver, By locator) {
		MobileElement element=(MobileElement) driver.findElement(locator);
		TouchActions action = new TouchActions(driver);
		action.scroll(element, 10, 100);
		action.perform();
	}


	public void setValue(AppiumDriver driver, MobileElement element, String value, int timeOut) {
		waitForElement(driver,element,timeOut);
	    element.sendKeys(value);
	}

	
	public void elementClick(AppiumDriver driver, By  locator,int timeOut) {
		MobileElement element=(MobileElement) driver.findElement(locator);
		waitForElement(driver,element,timeOut);
		element.click();
	}
	
	public void elementClick(AppiumDriver driver,MobileElement element,int timeOut) {
		waitForElement(driver,element,timeOut);
		element.click();
	}


	public static boolean isDisplayed(AppiumDriver driver, MobileElement  element) {
		boolean display;
		try {
			display = element.isDisplayed();

		} catch (Exception e) {
			display = false;
		}
		return display;
	}

	public static void waitForElement(AppiumDriver driver, MobileElement element, int timeOut) {
		wait = new WebDriverWait(driver, timeOut);
		try {

			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e2) {
			try {
				wait.until(ExpectedConditions.elementToBeSelected(element));
			} catch (Exception e3) {
				System.out.println("Element not found");
			}
		}
	}
	
	public static void WaitForAjax(AppiumDriver driver)
	{
		 while (true)
	        {
       try {
      Boolean ajaxIsComplete = (Boolean) ((JavascriptExecutor)driver).executeScript("return jQuery.active == 0");
      if (ajaxIsComplete){
        break;
        }
       }
      catch(Exception e) {
	        }}	 
	}
	
}
