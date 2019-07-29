/**
 * @author Tejaswini Gawande
 *
 */
package com.automation.sysAdminPages;
import java.awt.Robot; 
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AEPluginsPage {
       WebDriverWait wait;
       WebDriver webDriver;
       String setResult;
       
       public AEPluginsPage(WebDriver webDriver)
       {
    	   this.webDriver=webDriver;
       }
	
	@FindBy(linkText="Plugins")
	WebElement pluginTab;
	
	@FindBy(name="upload-zip")
    WebElement clickOnUpload;
	
	@FindBy(id="chooseFile")
	WebElement chooseFile;
	
	@FindBy(id="uploadButton")
	WebElement uploadButton;
	
	@FindBy(id="select-all")
	WebElement clickOnSelectall;
	
	@FindBy(name="submit")
	WebElement clickOnSave;
	
	public String pluginUploadZip() throws Exception {
		wait = new WebDriverWait(webDriver, 1000);
		wait.until(ExpectedConditions.elementToBeClickable(pluginTab));
		pluginTab.click();
		
		wait.until(ExpectedConditions.elementToBeClickable(clickOnUpload));
		Thread.sleep(1000);
		clickOnUpload.click();
		chooseFile.click();
		Thread.sleep(1000);
		Robot r = new Robot();
		//r.keyPress(KeyEvent.VK_CAPS_LOCK);
		r.keyPress(KeyEvent.VK_SHIFT);
		r.keyPress(KeyEvent.VK_C);
		r.keyRelease(KeyEvent.VK_C);
		r.keyPress(KeyEvent.VK_SHIFT);
		r.keyPress(KeyEvent.VK_SEMICOLON);
		r.keyRelease(KeyEvent.VK_SEMICOLON);
		r.keyRelease(KeyEvent.VK_SHIFT);
		r.keyPress(KeyEvent.VK_BACK_SLASH);  // / (slash)
		r.keyPress(KeyEvent.VK_SHIFT);
		r.keyPress(KeyEvent.VK_P);
		r.keyPress(KeyEvent.VK_S);
		r.keyRelease(KeyEvent.VK_SHIFT);
		r.keyPress(KeyEvent.VK_MINUS);
		r.keyPress(KeyEvent.VK_P);
		r.keyPress(KeyEvent.VK_L);
		r.keyPress(KeyEvent.VK_U);
		r.keyPress(KeyEvent.VK_G);
		r.keyPress(KeyEvent.VK_I);
		r.keyPress(KeyEvent.VK_N);
		r.keyPress(KeyEvent.VK_S);
		r.keyRelease(KeyEvent.VK_S);
		r.keyPress(KeyEvent.VK_PERIOD);
		r.keyPress(KeyEvent.VK_Z);
		r.keyPress(KeyEvent.VK_I);
		r.keyPress(KeyEvent.VK_P);
		r.keyRelease(KeyEvent.VK_P);
		r.keyPress(KeyEvent.VK_ENTER);    // confirm by pressing Enter in the end
		r.keyRelease(KeyEvent.VK_ENTER);
		//element.sendKeys("C:\PS-plugins.zip");
		
		Thread.sleep(10000);
		uploadButton.click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("select-all")));
		clickOnSelectall.click();
		clickOnSave.click();
		
		
		return setResult;
		
	}
	
	
	
}
