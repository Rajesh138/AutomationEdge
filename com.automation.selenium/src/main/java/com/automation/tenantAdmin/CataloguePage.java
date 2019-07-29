/**
 * 
 */
package com.automation.tenantAdmin;

import java.awt.Robot;
import java.awt.event.KeyEvent;

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
public class CataloguePage extends BaseTest {

	WebDriverWait wait;
	WebDriver webDriver;
	String setResult;

	public CataloguePage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	@FindBy(linkText="Catalogue")
	WebElement catalogueTab;
	
	@FindBy(id="catalogueSearch")
	WebElement catalogueSearch;
	
	@FindBy(xpath="//*/div[2]/span")
	WebElement selectWF;
	
	@FindBy(xpath="//*[@name='submit']")
	WebElement submit;
	
	@FindBy(id="popup-button-ok")
	WebElement popupOk;
	
	
	
	public void submitRequest() throws Exception{
		getfluentWait(catalogueTab);
		wait=new WebDriverWait(webDriver, 5000);
		wait.until(ExpectedConditions.elementToBeClickable(catalogueTab));
		
		catalogueTab.click();
		
		Robot robot=new Robot();
		
	    //catalogueSearch.click();
		Thread.sleep(2000);
		String searchWF=getValue("WorkflowName");
	    catalogueSearch.sendKeys(searchWF);
	    robot.keyPress(KeyEvent.VK_ENTER);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(selectWF));
		selectWF.click();
		Thread.sleep(2000);
		submit.click();
		popupOk.click();
		
		
	}
	
	
}
