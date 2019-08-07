package com.automation.testscript;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.automation.base.BaseTest;
import com.automation.tenantAdmin.RequestPage;

public class verifyRequestPageTestScript extends BaseTest {
	
	static RequestPage requestPge;
	
	
	@Test(description="Verify check alla option")
	
	public void verifyCheckAllaOption() throws InterruptedException{
		requestPge=PageFactory.initElements(webDriver, RequestPage.class);
		requestPge.selectShowcolumnOptionButton();
		requestPge.selectShowcolumnOptions(0);
	}
	
	@Test(description="Testcase for :: Verify default options get selected or not")
	public void verifyDefaultOptions() throws InterruptedException{
		requestPge=PageFactory.initElements(webDriver, RequestPage.class);
		requestPge.selectShowcolumnOptions(1);
		requestPge.selectShowcolumnOptionButton();
	

}
	@Test(description="This test method verifies download requests")
	public void downloadRequestsTestScript(){
		requestPge=PageFactory.initElements(webDriver, RequestPage.class);
		requestPge.downloadRequests();
		
	}
}