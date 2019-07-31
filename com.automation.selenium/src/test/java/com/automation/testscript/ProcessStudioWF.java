package com.automation.testscript;

import javax.management.DescriptorKey;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import com.automation.base.BaseTest;
import com.automation.sysAdminPages.AELoginPage;
import com.automation.tenantAdmin.WorkflowPage;

public class ProcessStudioWF extends BaseTest {
	String setResult;
	String expectedResult = "Success";
	  
	  
	@Test(priority = 1, groups={"mandatory","functional"})
	public void S017_loginTenantadmin() throws Exception {
		getKey("S017");
		AELoginPage aelogin = PageFactory.initElements(webDriver, AELoginPage.class);
		aelogin.LoginAE();
		clearHash();
	}

	//@Test(priority = 2)
	public void PS_WF_diverted() throws Exception {
		loadTestData("/WorkflowDetails");
		getKey("WF001");
		WorkflowPage workflow = PageFactory.initElements(webDriver, WorkflowPage.class);
		workflow.getWorkflowResult();
	}
	@Test(priority = 3, description="TestCaseID : WF002")
	public void PS_WF_diverted1() throws Exception {
		getKey("WF002");
		WorkflowPage workflow = PageFactory.initElements(webDriver, WorkflowPage.class);
		workflow.getWorkflowResult();
	}
}
