package com.mystore.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.mystore.actiondriver.Action;
import com.mystore.utility.ExtentManager;

import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseClass {
	
	public static Properties prop;
	//public static WebDriver driver;
	
	//Declare ThreadLocal driver
	public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
	
	//Added comments for base class now
	@BeforeSuite(groups = {"Smoke", "Sanity","Regression"})
	public void loadConfig() {
		
		ExtentManager.setExtent();
		DOMConfigurator.configure("log4j.xml");
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "\\Configurations\\config.properties");
			prop.load(ip);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static WebDriver getDriver() {
		
		//Get driver from threadlocalmap
		return driver.get();
	}
	
	public void launchApp(String browserName) {
		//String browserName = prop.getProperty("browser");
		if (browserName.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			// Set Browser to ThreadLocalMap
			//driver = new ChromeDriver();
			driver.set(new ChromeDriver());
		} else if (browserName.equalsIgnoreCase("FireFox")) {
			WebDriverManager.firefoxdriver().setup();
			//driver = new FirefoxDriver();
			driver.set(new FirefoxDriver());
		} else if (browserName.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			//driver = new InternetExplorerDriver();
			driver.set(new EdgeDriver());
		}
		
		getDriver().manage().window().maximize();
		Action.implicitWait(getDriver(),20);
		Action.pageLoadTimeOut(getDriver(), 40);
		getDriver().get(prop.getProperty("url"));
	
	
	}
	
	@AfterSuite(groups = {"Smoke", "Sanity","Regression"})
	public void afterSuite() {
		ExtentManager.endReport();
	}
	
	
}
