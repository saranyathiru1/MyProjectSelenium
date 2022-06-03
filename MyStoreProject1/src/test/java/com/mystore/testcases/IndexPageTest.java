/**
 * 
 */
package com.mystore.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;
import com.mystore.pageobjects.IndexPage;
import com.mystore.utility.Log;

/**
 * @author Admin
 *
 */
public class IndexPageTest extends BaseClass{
	
	IndexPage indexPage;
	
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
	
	@Test(groups = "Smoke")
	public void verifyLogo() {
		Log.startTestCase("verifyLogo");
		Log.info("Validate if logo is present");
		boolean result = indexPage.validateLogo();
		Assert.assertTrue(result);
		Log.endTestCase("verifyLogo");
		
	}
	
	@Test(groups = "Smoke")
	public void verifyTitle() {
		Log.startTestCase("verifyTitle");
		Log.info("Verify if My Store Title is displayed");
		String actualtitle = indexPage.getMyStoreTitle();
		Assert.assertEquals(actualtitle, "My Store");
		Log.endTestCase("verifyTitle");
	}	

}
