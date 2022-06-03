package com.mystore.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;
import com.mystore.dataprovider.DataProviders;
import com.mystore.pageobjects.AddToCartPage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.OrderPage;
import com.mystore.pageobjects.SearchResultPage;
import com.mystore.utility.Log;

public class OrderPageTest extends BaseClass{
	
	IndexPage indexPage;
	SearchResultPage searchResultPage;
	AddToCartPage addToCartPage;
	OrderPage orderPage;
	
	@Parameters("browser")
	@BeforeMethod(groups = {"Smoke", "Sanity","Regression"})
	public void setup(String browser) {
		launchApp(browser);
		indexPage = new IndexPage();	
	}
	
	@AfterMethod(groups = {"Smoke", "Sanity","Regression"})
	public void tearDown() {
		getDriver().quit();
	}

	@Test(dataProvider = "getProduct", dataProviderClass = DataProviders.class, groups = "Regression")
	public void verifyTotalPrice(String product, String quantity, String size) {
		Log.startTestCase("verifyTotalPrice");
		searchResultPage = indexPage.searchProduct(product);
		addToCartPage = searchResultPage.clickOnProduct();
		addToCartPage.enterQuantity(quantity);
		addToCartPage.selectSize(size);
		addToCartPage.clickOnAddtoCart();
		orderPage = addToCartPage.proceedToCheckOut();
		Double untiPrice = orderPage.getUnitPrice();
		Double totalPrice = orderPage.getTotalPrice();
		//System.out.println("totalPrice" + totalPrice);
		Double totalExpectedPrice = (untiPrice * (Double.parseDouble(quantity))) + 2;
		//System.out.println("totalExpectedPrice" + totalExpectedPrice);
		Assert.assertEquals(totalPrice, totalExpectedPrice);
		Log.endTestCase("verifyTotalPrice");
	}
}
