/**
 * 
 */
package com.automation.sysAdminPages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Tejaswini Gawande
 *
 */
public class CheckUserDetails {
	  WebDriverWait wait;
      WebDriver webDriver;
      String setResult;
      
	public CheckUserDetails(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	@FindBy(linkText="Users")
	WebElement usersTab;
	
	@FindBy(xpath="//a/div")
	WebElement userlog;
	
	@FindBy(xpath="//*[@name='profile']")
	WebElement profile;
	
	@FindBy(xpath="//*[@id='about-me']/div[3]/button[1]")
	WebElement saveProfile;
	
	public void userProfileDetails() throws Exception{
		wait = new WebDriverWait(webDriver, 1000);
		wait.until(ExpectedConditions.elementToBeClickable(usersTab));
		usersTab.click();
		wait.until(ExpectedConditions.elementToBeClickable(userlog));
		userlog.click();
		profile.click();
		Thread.sleep(3000);
		//String firstx="//*[@id='about-me']/div[2]/div/table/tbody/tr[";
		//String scondx="]/td[2]";
		//*[@id="about-me"]/div[2]/div/table/tbody/tr[1]/td[2]
		 List<WebElement>  row = webDriver.findElements(By.xpath("//*[@id='about-me']/div[2]/div/table/tbody/tr"));
		 System.out.println("row count"+row.size());
		 //int count=row.size();
		//WebElement data=webDriver.findElement(By.xpath("//*[@class='profile-table']/tbody/tr[1]/td[2]"));
		 for(int i=1;i<=6;i++)
		 {
		
			 String final_xpath="//*[@id='about-me']/div[2]/div/table/tbody/tr["+i+"]/td[2]";
			 WebElement Table_data = webDriver.findElement(By.xpath(final_xpath));
			 String inn=Table_data.getText();
			   System.out.print(i+"  "+""+""+inn);   
		 }
		 saveProfile.click(); 
		Thread.sleep(1000);
		
	}
	
}
