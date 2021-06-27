package mobileCore;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class CreateDriverSession {

	public static AppiumDriver setCapabilities(ExtentTest test, String platformName) throws Exception {

		URL url = null;
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
		
		switch (platformName) {
		case "Android":
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 3");
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
			capabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
			//capabilities.setCapability("avd", "Pixel_3");
			//capabilities.setCapability("avdLaunchTimeout", 180000);
			capabilities.setCapability("appPackage","com.swaglabsmobileapp");
			capabilities.setCapability("appActivity","com.swaglabsmobileapp.SplashActivity");
			capabilities.setCapability("appWaitForLaunch","true");
			String appURL = System.getProperty("user.dir") + File.separator + "apkFiles" + File.separator
					+ "Android.SauceLabs.Mobile.Sample.app.2.2.1.apk";
			capabilities.setCapability(MobileCapabilityType.APP, appURL);
			try {
				url = new URL("http://0.0.0.0:4723/wd/hub");
			} catch (Exception e) {
				test.log(LogStatus.FAIL, e);
				Assert.fail();
			}
			// create a driver section
			return new AndroidDriver(url, capabilities);
		case "IOS":
			// code for IOS
			return new IOSDriver(url, capabilities);
		default:
			throw new Exception("Invalid Platform");
		}
	}
}
