
package com.automation.sysAdminPages;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.selenium.BaseTest;

/**
 * @author Tejaswini Gawande
 *
 */

public class AETenantUserPage extends BaseTest{

	WebDriver webDriver;
	WebDriverWait wait;
	String setResult;
 
	public AETenantUserPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	@FindBy(xpath = "//div[2]/h4")
	WebElement getMessage;

	@FindBy(linkText = "Users")
	WebElement ClickOnUsersTab;

	@FindBy(linkText = "Tenant Users")
	WebElement tenantUsersTab;

	@FindBy(name = "add-new")
	WebElement clickOnAddew;

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

	@FindBy(xpath = "//*[@id='tenantOrgCode']")
	WebElement OrgCode;

	@FindBy(id = "role")
	WebElement userRole;

	@FindBy(name = "submit")
	WebElement clickOnSubmit;

	@FindBy(xpath = "//*/tr[3]/td[8]/span[1]")
	WebElement ClickOnEdit;

	@FindBy(xpath = "//*[@id='main-component']//*[@name='save']")
	WebElement saveEdit;

	@FindBy(xpath = "//*[@name='search']")
	WebElement searchUser;
	
	@FindBy(xpath = "//*[@class='pull-right']/button[2]")
	WebElement closePop;
	
	public String createTenantUser() throws Exception
	{
		this.wait = new WebDriverWait(webDriver, 1000);

		ClickOnUsersTab.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Tenant Users")));
		tenantUsersTab.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.name("add-new")));
		clickOnAddew.click();
		
		firstname.sendKeys(getValue("Firstname")+"11");
		lastname.sendKeys(getValue("Lastname"));
		email.sendKeys(getValue("Emailid"));
		username.sendKeys(getValue("Username"));
		System.out.println(getValue("Password")+"#####"+getValue("Confirmpassword"));
	
		confirmPassword.sendKeys(getValue("Password"));
		
		Select roleDrp= new Select(userRole);
		
		roleDrp.selectByIndex(1);
		password.sendKeys(getValue("Password"));
		
		Select OrgCodeDrp= new Select(OrgCode);
		//OrgCodeDrp.selectByVisibleText(getValue("TenantId"));
		OrgCodeDrp.selectByValue(getValue("TenantId"));
		clickOnSubmit.click();
		
		Thread.sleep(100);
		
		wait.until(ExpectedConditions.elementToBeClickable(ClickOnUsersTab));
		setResult = getMessage.getText();
		System.out.println(setResult);
		Thread.sleep(1000);
		if (setResult.equals("Failure")) {
			System.out.println("Clickable? ");
			closePop.click();
			Thread.sleep(1000);
		}
		return setResult;
	}
	
	public String EditTenantUser() throws Exception {
		this.wait = new WebDriverWait(webDriver, 1000);

		
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Tenant Users")));

		ClickOnEdit.click();
		
		password.sendKeys(getValue("Password"));
		confirmPassword.sendKeys(getValue("Confirmpassword"));
		saveEdit.click();

		Thread.sleep(100);

		wait.until(ExpectedConditions.elementToBeClickable(ClickOnUsersTab));
		setResult = getMessage.getText();
		System.out.println(setResult);
		Thread.sleep(1000);

		return setResult;
	}
	
	public void searchUser()
	{
		this.wait = new WebDriverWait(webDriver, 5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Tenant Users")));
		tenantUsersTab.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@name='search']")));
		searchUser.sendKeys(getValue("SearchUser"));
	}
	
}
