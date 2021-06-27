package frameworkCommons;

import java.util.Comparator;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Commons {

	static WebDriverWait wait;

	public static void waitForElement(WebDriver driver, By element, int timeOut) {
		wait = new WebDriverWait(driver, timeOut);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		} catch (Exception ex) {
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
	}
	

	public static void WaitForAjax(WebDriver driver)
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
	

	public static void javaScriptClick(WebDriver driver, By by) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", driver.findElement(by));
	}

	public static boolean isDisplayed(WebDriver driver, By by) {
		boolean display;
		try {
			display = driver.findElement(by).isDisplayed();

		} catch (Exception e) {
			display = false;
		}
		return display;
	}

	public static void moveToElement(WebDriver driver, By by) {
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(by)).click().build().perform();
	}

	public static void selectByText(WebDriver driver, By element, String value) {
		try {
			Select selectObj = new Select(driver.findElement(element));
			selectObj.selectByVisibleText(value);
		} catch (Exception e) {

		}
	}

	public static void selectByIndex(WebDriver driver, By element, int index) {
		Select selectObj = new Select(driver.findElement(element));
		selectObj.selectByIndex(index);
	}

	public static String getButtonColor(WebDriver driver, By by) {
		Commons.waitForElement(driver, by, 20);
		return getCssValue(driver, by, "background-color");
	}

	public static String getCssValue(WebDriver driver, By by, String attribute) {
		return driver.findElement(by).getCssValue(attribute);
	}

	public static void switchToFrameElement(WebDriver driver, By by) {
		driver.switchTo().frame(driver.findElement(by));
	}
	
	
	  public static <T> boolean isInOrder(Iterable<? extends T> iterable, Comparator<T> comparator) {
		      Iterator<? extends T> it = iterable.iterator();
		      if (it.hasNext()) {
		        T prev = it.next();
		        while (it.hasNext()) {
		          T next = it.next();
		          if (comparator.compare(prev, next) > 0) {
		            return false;
		          }
		          prev = next;
		        }
		      }
		      return true;
		    }
}