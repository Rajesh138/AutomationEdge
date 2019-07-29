package com.automation.sysAdminPages;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.selenium.BaseTest;


/**
 * @author Rajesh
 *
 */
public class AEUserLoginPage extends BaseTest {

	static WebDriver driver;
	static WebDriverWait wait;

	public AEUserLoginPage(WebDriver webDriver) {

		this.driver = webDriver;
	}

	@FindBy(xpath = ".//*[@id='uname']")
	WebElement username;

	@FindBy(id = "pswd")
	WebElement password;
	@FindBy(how = How.NAME, using = "sign-in")
	WebElement signIn;

	public void aeLogin(String userName, String psWord) {
		username.sendKeys(userName);
		password.sendKeys(psWord);
		signIn.click();
		assertEquals(driver.getTitle(), "AutomationEdge");
	}
}
