package com.automation.aecommonfunctions;

import javax.management.DescriptorKey;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.selenium.BaseTest;
//import com.thoughtworks.selenium.webdriven.commands.GetValue;

/**
 * @author Rajesh
 *
 */
public class workflowAssignmentPage extends BaseTest {
	 
	static WebDriver webDriver;
	 static WebDriverWait wait;

	public workflowAssignmentPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	/*
	 * Agents page elements
	 */
	@FindBy(linkText="Agents")
	WebElement agents;
	
	@FindBy (linkText="Workflow Assignment")
	WebElement workflowAssignment;
	@FindBy (xpath="//button[@id='edit']/span")
	WebElement editButton;
	
	@FindBy (xpath="//input[@type='checkbox'][@id='7']")
	WebElement wfCheckBox ;
	/*
	 * Request page elements
	 */
	@FindBy(xpath="//*[@id='request-menuitem']/a/i")
	WebElement requestsTab;
	
	@FindBy(xpath="//*[@name='download-requests'][contains(text(), 'Download Requests')]")
	WebElement downloadRequestsButton;
	
	@FindBy(xpath="//*[@id='downloadBtn']/span")
	WebElement downloadButton;
	
	
	public void workflowAssignment() throws InterruptedException{
		agents.click();
		workflowAssignment.click();
		wait=wait=new WebDriverWait(webDriver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(editButton));
		editButton.click();
		Thread.sleep(5000);
		System.out.println("Clicked on workflow edit button");
		wfCheckBox.click();
		Thread.sleep(3000);
		
	}
	
	public void requestPage() throws InterruptedException{
		Thread.sleep(3000);
		requestsTab.click();
		downloadRequestsButton.click();
		downloadButton.click();
		
	}
}
