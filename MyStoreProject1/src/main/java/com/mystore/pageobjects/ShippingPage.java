package com.mystore.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;

public class ShippingPage extends BaseClass{
	
	@FindBy(id = "cgv")
	WebElement checkBox;
	
	@FindBy(xpath = "//button/span[contains(text(),'Proceed to checkout')]")
	WebElement checkOutBtn;
	
	public ShippingPage() {
		
		PageFactory.initElements(getDriver(), this);
	}
	
	public void checkTerms() {
		
		Action.click(getDriver(), checkBox);
	}
	
	public PaymentPage clickCheckbox() {
		
		Action.click(getDriver(), checkOutBtn);
		return new PaymentPage();
	}
	
	
	

}
