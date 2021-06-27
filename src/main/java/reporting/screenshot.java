package reporting;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;

public class screenshot {

	public static String getScreenshot(WebDriver driver, String screenshotName) {

		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots" under src
		// folder
		
		String s=System.getProperty("user.dir") ;
		String destination = System.getProperty("user.dir") + "\\ExtendReport\\FailedScreenshots\\" + screenshotName + dateName
				+ ".png";
		File finalDestination = new File(destination);
		try {
			FileUtils.copyFile(source, finalDestination);
		} catch (Exception ex) {
			System.out.println("Failed to take Screenshot");
		}
		// Returns the captured file path
		return destination;
	}
}