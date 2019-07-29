package com.automation.sysAdminPages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.automation.base.BaseTest;

public class AELoginPage extends BaseTest {

	WebDriver webDriver;

	public AELoginPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	@FindBy(xpath = ".//*[@id='uname']")
	WebElement username;

	@FindBy(id = "pswd")
	WebElement password;

	@FindBy(how = How.NAME, using = "sign-in")
	WebElement signIn;

    @FindBy(name="oldpswd")
    WebElement oldpsw;
    
    @FindBy(name="newpswd")
    WebElement newpassword;
    
    @FindBy(name="confirmpswd")
    WebElement confirmpassword;
    
    @FindBy(how=How.NAME,using="save")
    WebElement resetPassword;
    
 

	public void LoginAE() throws Exception {
		username.sendKeys(getValue("Username"));
		password.sendKeys(getValue("Password"));
		signIn.click();	
		
	   
	
	}

	public void resetPassword() throws Exception
	{
		username.sendKeys(getValue("Username"));
		password.sendKeys(getValue("Password"));
		signIn.click();		
		Thread.sleep(100);
		oldpsw.sendKeys(getValue("Password"));
		newpassword.sendKeys(getValue("ResetPassword"));
		confirmpassword.sendKeys(getValue("ResetPassword"));
		resetPassword.click();
		Thread.sleep(800);
		System.out.println("Login with reset password");
		Thread.sleep(100);
		System.out.println(webDriver.getTitle());
	}
	
	
}
