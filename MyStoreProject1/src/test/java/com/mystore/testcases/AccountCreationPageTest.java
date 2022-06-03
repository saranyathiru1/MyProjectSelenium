package com.mystore.testcases;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;
import com.mystore.dataprovider.DataProviders;
import com.mystore.pageobjects.AccountCreationPage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;
import com.mystore.utility.Log;

public class AccountCreationPageTest extends BaseClass {
	
	IndexPage indexPage;
	LoginPage loginPage;
	AccountCreationPage accountcreationPage;
	
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
	
	@Test(dataProvider = "email", dataProviderClass = DataProviders.class, groups = "Sanity")
	public void verifyCreateAccountTest(String emailid) {
		Log.startTestCase("verifyCreateAccountTest");
		loginPage = indexPage.clickOnSignIn();
		accountcreationPage = loginPage.createNewAccount(emailid);
		boolean formtitle = accountcreationPage.validateAccountCreatePage();
		Assert.assertTrue(formtitle);
		Log.endTestCase("verifyCreateAccountTest");
	
	}

}
