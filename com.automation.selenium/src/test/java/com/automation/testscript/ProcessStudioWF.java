package com.automation.testscript;

/**
 * @author TejaswiniGawande
 *
 */
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.automation.commanutilities.BaseTest;
import com.automation.sysAdminPages.AELoginPage;
import com.automation.tenantAdmin.WorkflowPage;

public class ProcessStudioWF extends BaseTest {
	String setResult;
	String expectedResult = "Success";
	  
	  
	@Test(priority = 1)
	public void S017_loginTenantadmin() throws Exception {
		loadTestData("/SysadminTestData");
		getKey("S014");
		AELoginPage aelogin = PageFactory.initElements(webDriver, AELoginPage.class);
		aelogin.LoginAE();
		clearHash();
		loadTestData("/WorkflowDetails");
	}

	@Test(priority = 2)
	public void PS_WF_diverted() throws Exception {
		
		getKey("WF001");
		WorkflowPage workflow = PageFactory.initElements(webDriver, WorkflowPage.class);
		workflow.getWorkflowResult();
	}
	//@Test(priority = 3)
	public void PS_WF_Message() throws Exception {
		getKey("WF002");
		WorkflowPage workflow = PageFactory.initElements(webDriver, WorkflowPage.class);
		workflow.getWorkflowResult();
	}
	//@Test(priority = 4)
	public void PS_WF_configRun() throws Exception {
		getKey("WF003");
		WorkflowPage workflow = PageFactory.initElements(webDriver, WorkflowPage.class);
		workflow.getWorkflowResult();
	}
	//@Test(priority = 5)
	public void PS_WF_integer() throws Exception {
		getKey("WF005");
		WorkflowPage workflow = PageFactory.initElements(webDriver, WorkflowPage.class);
		workflow.getWorkflowResult();
	}
}
