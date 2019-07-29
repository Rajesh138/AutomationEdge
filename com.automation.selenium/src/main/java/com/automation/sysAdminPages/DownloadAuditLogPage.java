/**
 * 
 */
package com.automation.sysAdminPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Tejaswini Gawande
 *
 */
public class DownloadAuditLogPage {
	WebDriver webDriver;
	WebDriverWait wait;
	String setResult;
 
	public DownloadAuditLogPage(WebDriver webDriver) {

		this.webDriver = webDriver;
	}

	@FindBy(linkText="Logs")
	WebElement ClickLogsTab;
	
	@FindBy(name="download-logs")
	WebElement  clickOnDownload;
	
	@FindBy(id="downloadBtn")
	WebElement download;
	
	@FindBy(xpath = "//div[2]/h4")
	WebElement getMessage;
	
	public String downloadAuditLogs() throws Exception {

		this.wait = new WebDriverWait(webDriver, 1000);
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Logs")));

		ClickLogsTab.click();
		Thread.sleep(5000);
		clickOnDownload.click();
		Thread.sleep(5000);
		download.click();
		setResult = getMessage.getText();
		Thread.sleep(5000);
		System.out.println(setResult);
		return setResult;

	}
	
	
}
