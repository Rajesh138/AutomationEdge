package com.automation.commanutilities;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.automation.selenium.BaseTest;

public class LoginLogout extends BaseTest{
	WebDriver webDriver;

	public LoginLogout(WebDriver webDriver) {

		this.webDriver = webDriver;
	}
	
    @FindBy(name="oldpswd")
    WebElement oldpsw;
    
    @FindBy(name="newpswd")
    WebElement newpassword;
    
    @FindBy(name="confirmpswd")
    WebElement confirmpassword;
    
	@FindBy(xpath = ".//*[@id='uname']")
	WebElement username;

	@FindBy(id = "pswd")
	WebElement password;

	@FindBy(how = How.NAME, using = "sign-in")
	WebElement signIn;
	
    @FindBy(how=How.XPATH,using="//a/div") 
    WebElement clickOnLogout;
    
    @FindBy(how=How.NAME,using="sign-out")
    WebElement signOut;
    
    @FindBy(how=How.NAME,using="save")
    WebElement resetPassword;
    
    
	public void LoginAE(String userName, String passWord, Boolean flag) throws Exception {
		System.out.println("Login by new user"+getValue(userName));
		username.sendKeys(getValue(userName));
		password.sendKeys(getValue(passWord));
		signIn.click();
		Thread.sleep(100);
		if (flag == true) {
			ResetPassword();
			AElogout();
		}
	}
	
	public void AElogout() throws Exception
	{
		Thread.sleep(1000);
		clickOnLogout.click();
		signOut.click();
		Thread.sleep(4000);
	}
	
	public void ResetPassword() throws Exception
	{
		System.out.println("reset password");
		oldpsw.sendKeys(getValue("Confirmpassword"));
		newpassword.sendKeys(getValue("ResetPassword"));
		confirmpassword.sendKeys(getValue("ResetPassword"));
		resetPassword.click();
		Thread.sleep(1000);
		System.out.println("Login with reset password");
		LoginAE("Username", "ResetPassword", false);
		Thread.sleep(1000);
	}
}