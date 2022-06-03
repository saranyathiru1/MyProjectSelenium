package com.mystore.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;
import com.mystore.dataprovider.DataProviders;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;
import com.mystore.utility.Log;

public class HomePageTest extends BaseClass{
	
	IndexPage indexPage;
	LoginPage loginPage;
	HomePage homePage;
	
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
	
	@Test(dataProvider = "credentials", dataProviderClass = DataProviders.class, groups = "Smoke")
	public void wishlistTest(String uname, String pword) {
		
		Log.startTestCase("wishlistTest");
		loginPage = indexPage.clickOnSignIn();
		//homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		homePage = loginPage.login(uname,pword);
		boolean wishlist = homePage.validateMyWishList();
		Assert.assertTrue(wishlist);
		Log.endTestCase("wishlistTest");
	}
	
	@Test(dataProvider = "credentials", dataProviderClass = DataProviders.class, groups = "Smoke")
	public void orderHistoryTest(String uname, String pword) {
		
		Log.startTestCase("orderHistoryTest");
		loginPage = indexPage.clickOnSignIn();
		//homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		homePage = loginPage.login(uname, pword);
		boolean orderHistory = homePage.validateOrderHistory();
		Assert.assertTrue(orderHistory);
		Log.endTestCase("orderHistoryTest");
	}
	
	
	

}
