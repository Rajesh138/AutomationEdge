/**
 * 
 */
package com.automation.sysAdminPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.base.BaseTest;
import com.automation.commanutilities.LoginLogout;

/**
 * @author Admin
 *
 */
public class AESysadminUserPage extends BaseTest {
	WebDriver webDriver;
	WebDriverWait wait;
	String setResult;

	public AESysadminUserPage(WebDriver webDriver) {

		this.webDriver = webDriver;

	}

	@FindBy(linkText = "Users")
	WebElement clickOnUsersTab;

	@FindBy(name = "add-new")
	WebElement addnew;

	@FindBy(id = "fname")
	WebElement firstname;

	@FindBy(id = "lname")
	WebElement lastname;

	@FindBy(xpath = "//*[@id='username']")
	WebElement username;

	@FindBy(id = "useremail")
	WebElement email;

	@FindBy(id = "pswd")
	WebElement password;

	@FindBy(id = "confirmPswd")
	WebElement confirmPassword;

	@FindBy(name = "submit")
	WebElement clickOnCreate;

	@FindBy(xpath = "//*[@class='pull-right']/button[2]")
	WebElement closePop;

	@FindBy(xpath = "//div[2]/h4")
	WebElement getMessage;

	@FindBy(xpath = "//*/tr[3]/td[1]/label")
	WebElement clickOnEdit;

	@FindBy(xpath = "//*[@id='main-component']//*[@name='save']")
	WebElement saveEdit;

	@FindBy(xpath = "//*[@name='search']")
	WebElement searchUser;

	public String createSysadmin() throws Exception {
		Boolean flag = true;
		this.wait = new WebDriverWait(webDriver, 1000);

		clickOnUsersTab.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.name("add-new")));
		addnew.click();
		firstname.sendKeys(getValue("Firstname"));
		lastname.sendKeys(getValue("Lastname"));
		username.sendKeys(getValue("Username"));
		password.sendKeys(getValue("Password"));
		confirmPassword.sendKeys(getValue("Confirmpassword"));
		clickOnCreate.click();
		wait = new WebDriverWait(webDriver, 5000);
		wait.until(ExpectedConditions.elementToBeClickable(clickOnUsersTab));
		setResult = getMessage.getText();
		System.out.println(setResult);

		if (setResult.equals("Failure")) {
			System.out.println(" in sysadmin Clickable? ");
			Thread.sleep(10000);
			wait.until(ExpectedConditions.elementToBeClickable(closePop));
			System.out.println("check");
			closePop.click();
			Thread.sleep(2000);
		} else {
			Thread.sleep(2000);
			LoginLogout login = PageFactory.initElements(webDriver, LoginLogout.class);
			login.AElogout();
			login.LoginAE("Username", "Confirmpassword", flag);
			Thread.sleep(1000);
		}
		return setResult;
	}

	public String editSysadmin() throws InterruptedException {
		clickOnUsersTab.click();
		clickOnEdit.click();
		email.clear();
		//email.sendKeys(getValue("Emailid"));
		password.sendKeys(getValue("Password"));
		confirmPassword.sendKeys(getValue("Confirmpassword"));
		saveEdit.click();
		Thread.sleep(1000);
		setResult = getMessage.getText();
		System.out.println(setResult);
		if (setResult.equals("Failure")) {
			System.out.println("Clickable? ");
			closePop.click();
			Thread.sleep(1000);
		}
		return setResult;
	}

	public void searchSysadmin() {
		this.wait = new WebDriverWait(webDriver, 5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@name='search']")));
		searchUser.sendKeys(getValue("SearchSYS"));
	}

}
