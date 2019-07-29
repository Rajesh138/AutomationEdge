
package com.automation.sysAdminPages;

import javax.management.Descriptor;
import javax.management.DescriptorKey;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.selenium.BaseTest;
//import com.thoughtworks.selenium.webdriven.commands.GetValue;

/**
 * @author Tejaswini Gawande
 *
 */
public class AEWorkflowPage extends BaseTest {

	WebDriverWait wait;
	WebDriver webDriver;
	String setResult;

	public AEWorkflowPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	@FindBy(linkText = "Workflows")
	WebElement workflowTab;

	@FindBy(name = "add-new")
	WebElement importButton;

	@FindBy(id = "workflow_name")
	WebElement workflowName;

	@FindBy(id = "description")
	WebElement description;

	@FindBy(id = "category_name")
	WebElement categoryName;

	@FindBy(xpath = "//fieldset[7]/div/label")
	WebElement uploadZip;

	@FindBy(name = "submit")
	WebElement create;

	@FindBy(linkText = "Agents")
	WebElement agentsTab;

	@FindBy(linkText = "Workflow Assignment")
	WebElement workflowAssignmentPage;
	@FindBy(name = "edit")
	WebElement editButton;

	@FindBy(xpath = "//input[@type='checkbox'][@id='1']")
	WebElement wfCheckBox;

	public String createWorkflow() throws Exception {
		System.out.println("reached till class");
		Thread.sleep(200);
		wait = new WebDriverWait(webDriver, 1000);
		wait.until(ExpectedConditions.elementToBeClickable(workflowTab));
		workflowTab.click();
		Thread.sleep(200);
		importButton.click();
		Thread.sleep(200);
		wait.until(ExpectedConditions.elementToBeClickable(workflowName));
		workflowName.sendKeys(getValue("WorkflowName"));
		description.sendKeys(getValue("description"));
		uploadZip.click();

		System.out.println("end of class");
		return setResult;

	}

	/**
	 * @author Rajesh
	 * 
	 * Assign workflows to Agents
	 *
	 */
	
	public void workflowAssignment() {

		agentsTab.click();
		workflowAssignmentPage.click();
		wfCheckBox.click();

	}

}