package com.automation.sysAdminPages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.selenium.BaseTest;


public class AESettingPage extends BaseTest{
	WebDriverWait wait;
	String setResult="Failure";
WebDriver webDriver;
	public AESettingPage(WebDriver webDriver)
	{
		this.webDriver=webDriver;
	}
	
	@FindBy(linkText="Settings")
	WebElement setting;
	
	@FindBy(name="add-new")
	WebElement addurl;
	
	@FindBy(name="serverUrl")
	WebElement serverUrl;
	
	@FindBy(name="verify")
	WebElement verifyButton;
	 
	@FindBy(xpath="//*/form/fieldset[3]/button[2]")
	WebElement clickOnSave;
	
	@FindBy(name="add-new")
	WebElement configure;
	
	@FindBy(id="cleanupOldReqHours")
	WebElement reqHour;
	
	@FindBy(xpath = "//div[2]/h4")
	WebElement getMessage;
	
	public String settingServerURL() throws Exception {
		this.wait = new WebDriverWait(webDriver, 5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Settings")));
		setting.click();
		Thread.sleep(1000);
		addurl.click();
		Thread.sleep(1000);
		webDriver.findElement(By.xpath("//*[@id='serverUrl']")).sendKeys(Keys.chord(Keys.CONTROL,"a"));
		Thread.sleep(1000);
		//webDriver.findElement(By.xpath("//*[@id='serverUrl']")).clear();
		Thread.sleep(1000);
		serverUrl.sendKeys(getValue("url"));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("verify")));
		verifyButton.click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[2]/h4")));
		String verify = getMessage.getText();
		if (verify.equals("Success")) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*/form/fieldset[3]/button[2]")));
			clickOnSave.click();
			// System.out.println(setResult);
			setResult = getMessage.getText();
		}
		return setResult;

	}
	
	public String cleanupRequests() throws Exception {
		Thread.sleep(1000);
		this.wait = new WebDriverWait(webDriver, 10000);
		wait.until(ExpectedConditions.elementToBeClickable(By.name("add-new")));
		configure.click();
		reqHour.sendKeys(getValue("CleanRequest"));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("verify")));
		verifyButton.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[2]/h4")));
		String verify = getMessage.getText();
		if (verify.equals("Success")) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*/form/fieldset[3]/button[2]")));
			clickOnSave.click();
			// System.out.println(setResult);
			setResult = getMessage.getText();
		}
		return setResult;

	}
}
