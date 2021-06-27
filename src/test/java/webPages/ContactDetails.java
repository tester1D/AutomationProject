package webPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import frameworkCommons.Commons;

public class ContactDetails {

	By contactNumber = By.name("shippingPhoneNumber");
	By titleDD = By
			.xpath("(//span[contains(text(),'Title')]/parent::span/following-sibling::span[@role='presentation'])[1]");
	By firstName = By.id("shippingFirstNamedefault");
	By lastName = By.id("shippingLastNamedefault");
	By addressLine1 = By.xpath("//input[@id='shippingAddressOnedefault']");
	By regionDD = By
			.xpath("(//span[contains(text(),'Region')]/parent::span/following-sibling::span[@role='presentation'])[1]");
	By areaDD = By
			.xpath("(//span[contains(text(),'Area')]/parent::span/following-sibling::span[@role='presentation'])[1]");

	public void setContactNumber(WebDriver driver, String number) {
		Commons.waitForElement(driver, contactNumber, 10);
		driver.findElement(contactNumber).sendKeys(number);
	}

	public void setFirstName(WebDriver driver, String fname) {
		Commons.waitForElement(driver, firstName, 10);
		driver.findElement(firstName).sendKeys(fname);
	}

	public void setLastName(WebDriver driver, String lname) {
		Commons.waitForElement(driver, lastName, 10);
		driver.findElement(lastName).sendKeys(lname);
	}

	public void setAddressLine(WebDriver driver, String addressLine) {
		Commons.waitForElement(driver, addressLine1, 10);
		driver.findElement(addressLine1).sendKeys(addressLine);
	}

	public void setTitle(WebDriver driver, String title) {
		Commons.waitForElement(driver, titleDD, 5);
		driver.findElement(titleDD).click();
		try {
			driver.findElement(By.xpath(
					"//li[contains(@id,'select2-shippingSalutationdefault') and contains(text(),'" + title + "')]"))
					.click();
		} catch (Exception e) {
			// do nothing
		}
	}

	public void setRegion(WebDriver driver, String region) {
		driver.findElement(regionDD).click();
		try {
			driver.findElement(
					By.xpath("//li[contains(@id,'shippingCitydefault') and contains(text(),'" + region + "')]"))
					.click();
		} catch (Exception e) {
			// do nothing
		}
	}

	public void setArea(WebDriver driver, String area) {
		driver.findElement(areaDD).click();
		try {
			driver.findElement(
					By.xpath("//li[contains(@id,'shippingAreadefault') and contains(text(),'" + area + "')]")).click();
		} catch (Exception e) {
			// do nothing
		}
	}
}