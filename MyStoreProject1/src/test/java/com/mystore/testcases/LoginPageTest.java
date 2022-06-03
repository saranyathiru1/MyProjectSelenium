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

public class LoginPageTest extends BaseClass {
	
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
	
	@Test(dataProvider = "credentials", dataProviderClass = DataProviders.class, groups = {"Sanity","Smoke"})
	public void loginTest(String uname, String pword) throws InterruptedException {
		Log.startTestCase("loginTest");
		Log.info("user is going to click on SignIn");
		loginPage = indexPage.clickOnSignIn();
		Log.info("enter Username and Password");
		//homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		homePage = loginPage.login(uname,pword);
		Thread.sleep(3000);
		String actualURL = homePage.getCurrentURL();
		String expectedURL = "http://automationpractice.com/index.php?controller=my-account";
		Log.info("Verifying if user is able to login");
		Assert.assertEquals(actualURL, expectedURL);
		Log.info("Login is Success");
		Log.endTestCase("loginTest");
			
	}

}
