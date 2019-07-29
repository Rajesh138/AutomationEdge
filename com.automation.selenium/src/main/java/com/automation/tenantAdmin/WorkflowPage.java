/**
 * 
 */
package com.automation.tenantAdmin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.base.BaseTest;

/**
 * @author Tejaswini Gawande
 *
 */
public class WorkflowPage extends BaseTest {

	WebDriverWait wait;
	WebDriver webDriver;
	String setResult;
	// Wait<driver> fluentwait= new
	// FluentWait<driver>(webDriver).WithTimeout(10,
	// TimeUnit.SECONDS).PollingEvery(2,TimeUnit.SECONDS);

	public WorkflowPage(WebDriver webDriver) {
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

	@FindBy(id = "expectedCompletionTime")
	WebElement expectedCompletionTime;

	@FindBy(id = "maxCompletionTime")
	WebElement maxCompletionTime;

	@FindBy(name = "submit")
	WebElement submit;

	@FindBy(xpath = "//div[2]/h4")
	WebElement getMessage;

	@FindBy(xpath = "//td[6]/label")
	WebElement active;

	@FindBy(xpath = "//h2[@class='page-title inline-item']")
	WebElement home;

	public void getWorkflowResult() throws Exception {

		 createWorkflow();
		 updateWorkflow();
		 activateWorkflow();

		// AssignWorkflowToAgent Class object to load the function
		 AssignWorkflowToAgent agent = PageFactory.initElements(webDriver,
		 AssignWorkflowToAgent.class);
		 agent.assignWorkflowToAgent();

		// navigate to Catelog and submit the request
		
		CataloguePage catelogue = PageFactory.initElements(webDriver, CataloguePage.class);
		catelogue.submitRequest();

		RequestPage request = PageFactory.initElements(webDriver, RequestPage.class);
		request.checkWorkflowStatus();
	}

	public String createWorkflow() throws Exception {
		getfluentWait(home);
		wait = new WebDriverWait(webDriver, 5000);
		wait.until(ExpectedConditions.elementToBeClickable(workflowTab));
		workflowTab.click();
        getfluentWait(importButton);
		importButton.click();

		wait.until(ExpectedConditions.elementToBeClickable(workflowName));
		workflowName.sendKeys(getValue("WorkflowName"));
		description.sendKeys(getValue("description"));
		uploadZip.click();

		String filePath =System.getProperty("user.dir") + "\\src\\test\\resources\\WorkflowFiles\\"+ getValue("zip_path");
		String autoIt_file_path = "D:\\AutoITSetup\\AE_ChooseFile.exe " + filePath;
        
		System.out.println(autoIt_file_path);
		Thread.sleep(200);
		Runtime.getRuntime().exec(autoIt_file_path);
		// Runtime.getRuntime().exec("D://AutoITSetup//AE_ChooseFile.exe");

		Thread.sleep(10000);
		create.click();
		return setResult;
	}

	public void updateWorkflow() {
		maxCompletionTime.sendKeys("300");
		expectedCompletionTime.sendKeys("200");
		submit.click();
		setResult = getMessage.getText();
		System.out.println(setResult);
	}

	public void activateWorkflow() {
		if (setResult.equals("Success")) {
			wait.until(ExpectedConditions.elementToBeClickable(active));
			active.click();
		}
	}

}
