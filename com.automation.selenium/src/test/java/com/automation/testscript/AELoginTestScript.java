package com.automation.testscript;

import static org.testng.Assert.assertEquals;


import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.automation.base.BaseTest;
import com.automation.sysAdminPages.*;



public class AELoginTestScript extends BaseTest {

	String setResult;
	String expectedResult = "Success";
  
	//@Test(priority = 1)
	public void resetPassword_S001() throws Exception
	{
		loadTestData("/SysadminTestData");
		getKey("S011");
		AELoginPage aelogin = PageFactory.initElements(webDriver, AELoginPage.class);
		aelogin.resetPassword();
	}
	
	@Test(priority = 2)
	public void verifyLogin_S002() throws Exception {
		
		getKey("S001");
		AELoginPage aelogin = PageFactory.initElements(webDriver, AELoginPage.class);
		aelogin.LoginAE();
	}

	//@Test(priority = 3)
	public void verifyCreateTenant_S003() throws Exception {
		getKey("S002");
		AETenantPage aetenant = PageFactory.initElements(webDriver, AETenantPage.class);
		setResult = aetenant.createTenant();
		assertEquals(setResult, expectedResult);	
	}

	//@Test(priority = 4)
	public void verifySearch_S004() throws Exception {
		AETenantPage aetenantsearch = PageFactory.initElements(webDriver, AETenantPage.class);
		getKey("S003");
		aetenantsearch.verifyTenantSearch();
	}

	//@Test(priority = 5)
	public void createSysadmin_S005() throws Exception {
		AESysadminUserPage aesys = PageFactory.initElements(webDriver, AESysadminUserPage.class);
		getKey("S004");
		setResult = aesys.createSysadmin();
		assertEquals(setResult, expectedResult);
		if (setResult.equals("Success")) {
			verifyLogin_S002();
		}
	}

	//@Test(priority = 6)
	public void editSysadminUser_S006() throws Exception {
		AESysadminUserPage aesysedit = PageFactory.initElements(webDriver, AESysadminUserPage.class);
		getKey("S005");
		setResult = aesysedit.editSysadmin();
		assertEquals(setResult, expectedResult);
	}

	//@Test(priority = 7)
	public void searchSysadminUser_S007()  {
		AESysadminUserPage search = PageFactory.initElements(webDriver, AESysadminUserPage.class);
		getKey("S006");
		search.searchSysadmin();
	}
	//@Test(priority = 8)
	public void createTenantAdmin_S008() throws Exception {
		AETenantUserPage users = PageFactory.initElements(webDriver, AETenantUserPage.class);
		getKey("S007");
		setResult = users.createTenantUser();
		assertEquals(setResult, expectedResult);
	}

   //@Test(priority = 9)
	public void EditTenantAdmin_S009() throws Exception {
		AETenantUserPage users = PageFactory.initElements(webDriver, AETenantUserPage.class);
		getKey("S008");
		setResult = users.EditTenantUser();
		assertEquals(setResult, expectedResult);
	}

	//@Test(priority = 10)
	public void searchUser_S010() {
		AETenantUserPage users = PageFactory.initElements(webDriver, AETenantUserPage.class);
		getKey("S009");
		users.searchUser();
	}

	//@Test(priority = 11)
	public void downloadAuditlogs_S011() throws Exception {
		DownloadAuditLogPage auditLogs = PageFactory.initElements(webDriver, DownloadAuditLogPage.class);
		setResult = auditLogs.downloadAuditLogs();
		assertEquals(setResult, expectedResult);
	}
	
	//@Test(priority = 12)
	public void S012_serverUrlSetting() throws Exception {
		getKey("S012");
		AESettingPage urlSetting = PageFactory.initElements(webDriver, AESettingPage.class);
		setResult = urlSetting.settingServerURL();
		assertEquals(setResult, expectedResult);
	}
	
	//@Test(priority=13,dependsOnMethods={"S012_serverUrlSetting"})
	public void S013_cleanupRequests() throws Exception{
		getKey("S013");
		AESettingPage cleanupRequests=PageFactory.initElements(webDriver, AESettingPage.class);
		cleanupRequests.cleanupRequests();
	}
	
	//@Test(priority = 14)
	public void S014_viewProfileDetails() throws Exception {
		getKey("S014");
		CheckUserDetails profile = PageFactory.initElements(webDriver, CheckUserDetails.class);
		profile.userProfileDetails();
	}

	//@Test(priority=15)
	public void S015_uploadPluginZip() throws Exception {
		AEPluginsPage uploadPlugin = PageFactory.initElements(webDriver, AEPluginsPage.class);
		uploadPlugin.pluginUploadZip();
	}
	//@Test(priority=16)
		public void S016_uploadPluginJar(){
		//AEPluginsPage uploadPlugin = PageFactory.initElements(webDriver, AEPluginsPage.class);
		
	}
	
	
	
}