package com.mystore.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;
import com.mystore.dataprovider.DataProviders;
import com.mystore.pageobjects.AddToCartPage;
import com.mystore.pageobjects.AddressPage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;
import com.mystore.pageobjects.OrderConfirmationPage;
import com.mystore.pageobjects.OrderPage;
import com.mystore.pageobjects.OrderSummaryPage;
import com.mystore.pageobjects.PaymentPage;
import com.mystore.pageobjects.SearchResultPage;
import com.mystore.pageobjects.ShippingPage;
import com.mystore.utility.Log;

public class EndToEndTest extends BaseClass{
	
	IndexPage indexPage;
	SearchResultPage searchResultPage;
	AddToCartPage addToCartPage;
	OrderPage orderPage;
	LoginPage loginPage;
	AddressPage addressPage;
	ShippingPage shippingPage;
	PaymentPage paymentPage;
	OrderSummaryPage orderSummaryPage;
	OrderConfirmationPage orderConfirmationPage;
	
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
	public void endToEndTest(String product, String quantity, String size) {
		
		Log.startTestCase("endToEndTest");
		searchResultPage = indexPage.searchProduct(product);
		addToCartPage = searchResultPage.clickOnProduct();
		addToCartPage.enterQuantity(quantity);
		addToCartPage.selectSize(size);
		addToCartPage.clickOnAddtoCart();
		orderPage = addToCartPage.proceedToCheckOut();
		loginPage  = orderPage.clickProceedtoCheckout();
		addressPage = loginPage.login1(prop.getProperty("username"), prop.getProperty("password"));
		shippingPage = addressPage.clickProceedtoCheckout();
		shippingPage.checkTerms();
		paymentPage = shippingPage.clickCheckbox();
		orderSummaryPage = paymentPage.clickOnPaymentMethod();
		orderConfirmationPage = orderSummaryPage.clickConfirmOrderBtn();
		String actualMessage = orderConfirmationPage.validateConfirmMessage();
		String expectedMessage = "Your order on My Store is complete.";
		Assert.assertEquals(actualMessage, expectedMessage);
		Log.endTestCase("endToEndTest");
	}
}
