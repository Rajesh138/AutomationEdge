/**
 * 
 */
package com.automation.sysAdminPages;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.selenium.BaseTest;

/**
 * @author Admin
 *
 */
public class AETenantPage extends BaseTest {
	WebDriver webDriver;
	WebDriverWait wait;
	String setResult;

	public AETenantPage(WebDriver webDriver) {

		this.webDriver = webDriver;

	}

	@FindBy(linkText = "Tenants")
	WebElement clickOnTenantTab;

	@FindBy(name = "add-tenant")
	WebElement addTenant;

	@FindBy(name = "description")
	WebElement description;

	@FindBy(id = "tenantName")
	WebElement tenantname;

	@FindBy(name = "orgCode")
	WebElement orgCode;

	@FindBy(name = "submit")
	WebElement clickOnCreate;

	@FindBy(xpath = "//*[@name='cancel'][contains(text(),'Cancel')]")
	WebElement closeTenantPop;

	@FindBy(name = "search")
	WebElement search;

	@FindBy(xpath = "//*[@name='cancel'][contains(text(),'Cancel')]")
	WebElement closePop;

	@FindBy(xpath = "//div[2]/h4")
	WebElement getMessage;

	By tenantTab = By.linkText("Tenants");

	public String createTenant() throws Exception {
		this.wait = new WebDriverWait(webDriver, 1000);
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Tenants")));
		clickOnTenantTab.click();
		Thread.sleep(100);
		wait.until(ExpectedConditions.elementToBeClickable(By.name("add-tenant")));
		Thread.sleep(100);
		addTenant.click();
		tenantname.sendKeys(getValue("TenantName"));
		description.sendKeys(getValue("Description"));
		orgCode.sendKeys(getValue("OrganizationCode"));

		clickOnCreate.click();
		assertEquals(webDriver.getTitle(), "AutomationEdge");
        setResult=getMessage.getText();
		Thread.sleep(1000);

		if (setResult.equals("Failure")) {
			System.out.println("Clickable? ");
			closePop.click();
			Thread.sleep(2000);
		}
		wait.until(ExpectedConditions.elementToBeClickable(tenantTab));

		setResult = getMessage.getText();
		Thread.sleep(2000);
		return setResult;
	}

	public void verifyTenantSearch() throws Exception {
		search.sendKeys(getValue("Search"));
		Thread.sleep(5000);
	}

	
}
