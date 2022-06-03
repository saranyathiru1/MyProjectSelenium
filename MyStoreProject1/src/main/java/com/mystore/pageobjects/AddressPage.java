package com.mystore.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;

public class AddressPage extends BaseClass{
	
	@FindBy(xpath = "//span[text()='Proceed to checkout']")
	WebElement proceedtoCheckoutBtn;
	
	public AddressPage() {
		
		PageFactory.initElements(getDriver(), this);
	}
	
	public ShippingPage clickProceedtoCheckout() {
		
		Action.click(getDriver(), proceedtoCheckoutBtn);
		return new ShippingPage();
	}
	
	

}
