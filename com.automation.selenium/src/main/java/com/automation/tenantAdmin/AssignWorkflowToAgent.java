/**
 * 
 */
package com.automation.tenantAdmin;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.base.BaseTest;

/**
 * @author Tejaswini Gawande
 * 
 */
public class AssignWorkflowToAgent extends BaseTest {

	WebDriverWait wait;
	WebDriver webDriver;
	String setResult;

	public AssignWorkflowToAgent(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	@FindBy(linkText="Agents")
	WebElement agentsTab;
	
	@FindBy(linkText="Workflow Assignment")
	WebElement workflowAssignmentTab;
	
	
	@FindBy(name="edit")
	WebElement edit;
	
	@FindBy(xpath="//*[@id='save']")
	WebElement save;
	
	@FindBy(id="rightSearch")
	WebElement rightSearch;
	
	@FindBy(id="leftSearch")
	WebElement leftSearch;
	
	@FindBy(xpath="//*[@class='list-group']/a[1]/input")
	WebElement checkRadioButton;
	
	@FindBy(xpath="//*[@id='popup-button-ok']")
	WebElement popupOK;
	
	public String assignWorkflowToAgent() throws Exception{
		wait = new WebDriverWait(webDriver, 2000);
		wait.until(ExpectedConditions.elementToBeClickable(agentsTab));
		agentsTab.click();
		
		wait.until(ExpectedConditions.elementToBeClickable(workflowAssignmentTab));
		workflowAssignmentTab.click();
		
		Thread.sleep(200);
		edit.click();
	
		rightSearch.sendKeys(getValue("WorkflowName"));
		checkRadioButton.click();
		Thread.sleep(2000);
		save.click();
		popupOK.click();
		Thread.sleep(2000);
		return setResult;
	}
	
}
