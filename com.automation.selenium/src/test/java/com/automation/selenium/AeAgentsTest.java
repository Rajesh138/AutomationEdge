package com.automation.selenium;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.automation.aecommonfunctions.workflowAssignmentPage;
import com.automation.sysAdminPages.AELoginPage;
import com.automation.sysAdminPages.AEUserLoginPage;

/*
 *
 *  @author Rajesh
 *  
 */
public class AeAgentsTest extends BaseTest {

	AEUserLoginPage userLogin;
	workflowAssignmentPage wfAssignments;
	//static WebDriver webDriver;
	
	@Test(description = "workflow Assignment to Agents")
	public void wfAssignToAgents() throws InterruptedException {
		System.out.println("Entered in to Login test script");
		userLogin = PageFactory.initElements(webDriver, AEUserLoginPage.class);
		wfAssignments = PageFactory.initElements(webDriver, workflowAssignmentPage.class);
		userLogin.aeLogin("rajesh", "Pune@123");
		wfAssignments.workflowAssignment();
	}
	
	@Test(description="Download existing requests")
	public void downloadRequests() throws InterruptedException{
		wfAssignments = PageFactory.initElements(webDriver, workflowAssignmentPage.class);
		wfAssignments.requestPage();
	}

}
