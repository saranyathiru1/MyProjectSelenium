package com.mystore.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;

public class AddToCartPage extends BaseClass{
	
	@FindBy(id = "quantity_wanted")
	WebElement quantityElement;
	
	
	@FindBy(name = "group_1")
	WebElement sizeDropdown;
	
	////span[text()=\"Add to cart\"]
	@FindBy(xpath = "//p[@id='add_to_cart']//span")
	WebElement addtoCartBtn;
	
	@FindBy(xpath = "//*[@id=\"layer_cart\"]//h2/i")
	WebElement addtoCartmessage;
	
	@FindBy(xpath = "//span[contains(text(),'Proceed to checkout')]")
	WebElement proceedtoCheckoutBtn;
	
	public AddToCartPage() {
		
		PageFactory.initElements(getDriver(), this);
	}
	
	public void enterQuantity(String quantity) {
		
		Action.type(quantityElement, quantity);
		
	}
	
	public void selectSize(String size) {
		
		Action.selectByVisibleText(size, sizeDropdown);
	}
	
	public void clickOnAddtoCart() {
		
		Action.click(getDriver(), addtoCartBtn);
		
	}
	
	public boolean verifyAddtoCartMessage() {
		
		Action.fluentWait(getDriver(), addtoCartmessage, 10);
		return Action.isDisplayed(getDriver(), addtoCartmessage);
	}
	
	public OrderPage proceedToCheckOut() {
		
		Action.fluentWait(getDriver(), proceedtoCheckoutBtn, 10);
		Action.JSClick(getDriver(), proceedtoCheckoutBtn);
		//Action.fluentWait(driver, proceedtoCheckoutBtn, 10);
		return new OrderPage();
	}
	
	
	

}
