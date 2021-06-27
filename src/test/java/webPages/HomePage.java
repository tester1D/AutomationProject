package webPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import frameworkCommons.Commons;

public class HomePage {

	By menOption = By.xpath("//li/a[contains(@href, 'https://en-ae.sssports.com/mens.html')]");
	By menHomePageTitleText = By.xpath("//*[contains(@class,'hero-banner__heading')]/a[contains(@href,'mens')]");
	By shopAllButton = By.xpath("//div/a[contains(text(),'Shop All')]");
	By firstPictureInShopAll = By.xpath("(//picture//img)[1]");
	By displayedItems = By.xpath("//div[@class='image-container']");
	By selectSizeBtn = By.xpath("//div[@data-attr='vendorSize']");
	By sizeOption = By.id("select2-vendorSize-shoesize-results");
	By sizeButton = By.xpath("//button[@data-select-label='Select size']");
	By addToBag = By.xpath("//button[@data-add-to-chart-label='Add to bag']");
	By cartIcon = By.xpath("//div[@class='minicart__total']/button");
	By cartitemCount = By.xpath("(//div[@class='minicart__total']/button/child::span)[1]");
	By countInsideCart = By.xpath("(//*[@class='minicart__title']/child::span)[1]");
	By checkOutSecurely = By.xpath("//*[contains(text(),'Checkout securely')]");
	By chechOutPopUpClose = By.xpath("//div[@class='container cart']//child::button[contains(@class,'close')]");
	// Product Details
	By productBrand = By.xpath(
			"//*[@class='product-detail__product-brand-product-name']/child::span[contains(@class,'product-brand')]");
	By productName = By.xpath(
			"//*[@class='product-detail__product-brand-product-name']/child::span[contains(@class,'product-name')]");
	By productPrice = By.xpath("//*[@class='product-detail__wrapper']/child::div//span[@class='sales']/span");
	// Cart Item Details
	By cartItemBrand = By.xpath("(//*[@class='item-attributes '])[1]/*[contains(@class,'brand')]");
	By cartItemName = By.xpath("(//*[@class='item-attributes '])[1]/*[contains(@class,'line-item-name')]/span");
	By cartItemSize = By.xpath("//*[contains(@class,'product-line-item__attributes') and contains(text(),'Size')]");

	LoginPage lp = new LoginPage();

	public int getAllItemsCount(WebDriver driver) {
		return driver.findElements(displayedItems).size();
	}

	public void closeCheckOutPopUp(WebDriver driver) {
		if (Commons.isDisplayed(driver, addToBag))
			driver.findElement(chechOutPopUpClose).click();
	}

	public void clickRequiredOption(WebDriver driver, String option) {
		switch (option) {
		case "Men":
			driver.findElement(menOption).click();
			break;
		case "Women":
			// write code
			break;
		default:
			System.out.println("Wrong Option");
			break;
		}
		Commons.WaitForAjax(driver);
		lp.handlePageLoadPopups(driver);
	}

	public void clickShopAll(WebDriver driver) {
		Commons.waitForElement(driver, shopAllButton, 30);
		driver.findElement(shopAllButton).click();
		Commons.WaitForAjax(driver);
		lp.handlePageLoadPopups(driver);
	}

	public void clickOnAnyProduct(WebDriver driver) {
		Commons.moveToElement(driver, firstPictureInShopAll);
	}

	public void selectProductSize(WebDriver driver, String size) {
		Commons.waitForElement(driver, selectSizeBtn, 5);
		driver.findElement(selectSizeBtn).click();
		try {
			driver.findElement(By.xpath(
					"//ul[contains(@id,'select2-vendorSize-shoesize-results')]/li[contains(text(),'" + size + "')]"))
					.click();
		} catch (Exception e) {
             //do nothing
		}
	}

	public String getAddToCartButtonColor(WebDriver driver) {
		Commons.WaitForAjax(driver);
		Commons.waitForElement(driver, addToBag, 10);
		return Commons.getButtonColor(driver, addToBag);
	}

	public void clickAddtoBag(WebDriver driver) {
		Commons.WaitForAjax(driver);
		Commons.waitForElement(driver, addToBag, 5);
		Commons.moveToElement(driver, addToBag);
	}

	public int getCartItemCount(WebDriver driver) {
		Commons.WaitForAjax(driver);
		Commons.waitForElement(driver, cartIcon, 5);
		String itemCount = driver.findElement(cartitemCount).getText();
		return Integer.parseInt(itemCount);
	}

	public void clickOnCartIcon(WebDriver driver) {
		driver.findElement(cartIcon).click();
	}

	public boolean verifyCheckOutButtonDisplayed(WebDriver driver) {
		return Commons.isDisplayed(driver, checkOutSecurely);
	}

	public void clickCheckOutSecurely(WebDriver driver) {
		Commons.waitForElement(driver, checkOutSecurely, 20);
		driver.findElement(checkOutSecurely).click();
	}

	public int getCountInsideCartCount(WebDriver driver) {
		Commons.waitForElement(driver, cartIcon, 20);
		String itemCount = driver.findElement(countInsideCart).getText();
		return Integer.parseInt(itemCount);
	}

	public String getProductName(WebDriver driver) {
		Commons.waitForElement(driver, productName, 20);
		String prodName = driver.findElement(productName).getText();
		return prodName;
	}

	public String getProductBrand(WebDriver driver) {
		Commons.waitForElement(driver, productBrand, 20);
		String prodBrand = driver.findElement(productBrand).getText();
		return prodBrand;
	}

	public String getProductPrice(WebDriver driver) {
		Commons.waitForElement(driver, productPrice, 20);
		String prodBrand = driver.findElement(productPrice).getText();
		return prodBrand;
	}

	public String getCartItemName(WebDriver driver) {
		Commons.waitForElement(driver, cartItemName, 20);
		String itemName = driver.findElement(cartItemName).getText();
		return itemName;
	}

	public String getCartItemBrand(WebDriver driver) {
		Commons.waitForElement(driver, cartItemBrand, 20);
		String itemBrand = driver.findElement(cartItemBrand).getText();
		return itemBrand;
	}

	public String getCartItemSize(WebDriver driver) {
		Commons.waitForElement(driver, cartItemSize, 20);
		String itemBrand = driver.findElement(cartItemSize).getText();
		return itemBrand;
	}

	public String getMenHomePageTitle(WebDriver driver) {
		Commons.waitForElement(driver, menHomePageTitleText, 20);
		String titleText = driver.findElement(menHomePageTitleText).getText();
		return titleText;
	}
}
