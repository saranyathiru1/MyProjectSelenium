package com.mystore.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;

public class LoginPage extends BaseClass{
	
	@FindBy(id="email")
	WebElement userName;
	
	@FindBy(name="passwd")
	WebElement password;
	
	@FindBy(id="SubmitLogin")
	WebElement signInButton;
	
	@FindBy(name="email_create")
	WebElement createnewEmailAccount;
	
	@FindBy(id="SubmitCreate")
	WebElement createNewAccountBttn;
	
	
	public LoginPage() {
		
		PageFactory.initElements(getDriver(),this);
	}
	
	public HomePage login(String uname, String pword) {
		
		Action.type(userName, uname);
		Action.type(password, pword);
		Action.click(getDriver(), signInButton);
		return new HomePage();
	}
	
	public AddressPage login1(String uname, String pword) {
		
		Action.type(userName, uname);
		Action.type(password, pword);
		Action.click(getDriver(), signInButton);
		return new AddressPage();
	}
	
	public AccountCreationPage createNewAccount(String newemail) {
		
		Action.type(createnewEmailAccount, newemail);
		Action.click(getDriver(), createNewAccountBttn);
		return new AccountCreationPage();
	}

}
