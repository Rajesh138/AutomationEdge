package com.automation.selenium;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.automation.sysAdminPages.AELoginPage;
import com.automation.sysAdminPages.AEWorkflowPage;

public class ProcessStudioWF extends BaseTest{
	String setResult;
	String expectedResult = "Success";
	
	@Test(priority=1)
	public void S017_loginTenantadmin() throws Exception{
	getKey("S017");
	AELoginPage aelogin = PageFactory.initElements(webDriver, AELoginPage.class);
	aelogin.LoginAE();
	clearHash();
	
}
	
	@Test(priority=2)
    public void processStudio_WF() throws Exception
	{
		loadTestData("/WorkflowDetails");
		getKey("WF001");
		AEWorkflowPage workflow= PageFactory.initElements(webDriver, AEWorkflowPage.class);
		workflow.createWorkflow();
		
	}


}
