package com.automation.tenantAdmin;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.ui.WebDriverWait;


import com.automation.base.BaseTest;

/**
 * @author Tejaswini Gawande
 *
 */
public class RequestPage extends BaseTest {
	WebDriverWait wait;
	WebDriver webDriver;
	String setResult;
	String workflowStatus;
	String workflowID;
	String workflowName;
	String requestvalue;
	String destFolder;
	private static String downloadPath = "D:\\Tejaswini_Workflow\\DownloadedFile\\";

	public RequestPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	@FindBy(linkText = "Requests")
	WebElement requestsTab;

	@FindBy(xpath = "//tbody[@class='tbody-stripped'][1]/tr/td[3]/span[1]")
	WebElement status;

	@FindBy(name = "refresh-btn")
	WebElement refresh;

	public void checkWorkflowStatus() throws Exception {
		getfluentWait(requestsTab);
		requestsTab.click();
		webDriver.findElement(By.xpath("//tbody[@class='tbody-stripped']"));
		List<WebElement> getCol = webDriver.findElements(By.xpath("//tbody[@class='tbody-stripped'][1]/tr/td"));
		System.out.println("No of columns" + getCol.size());

		int count = 0;
		for (int col = 1; col < getCol.size(); col++) {
			requestvalue = webDriver.findElement(By.xpath("//tbody[@class='tbody-stripped'][1]/tr/td[" + col + "]"))
					.getText();
			if (count == 0) {
				workflowID = requestvalue;
				System.out.println(workflowID);
			} else if (count == 1) {
				workflowName = requestvalue;
				System.out.println(workflowName);
			}
			count++;
		}
		// String foldername= System.getProperty("user.dir") +
		// "/src/test/resources/WorkflowFiles/"+workflowID+workflowName;

		workflowStatus = status.getText();
		String WFstatus = getStatus(workflowStatus);
		System.out.println("Final Status=" + WFstatus);

		if (WFstatus.equalsIgnoreCase(getValue("ResultStatus"))) {
			System.out.println("check the result");
			webDriver.findElement(By.xpath("//tbody[@class='tbody-stripped'][1]/tr/td[3]/span[2]")).click();
			webDriver.findElement(By.xpath("//tbody[@class='tbody-stripped'][1]/tr[2]/td/div/span[1]")).getText();
			String txtCount = getValue("OutputTextcount");
			
			//make folder to move the files
			destFolder = "D:\\Tejaswini_Workflow\\" + workflowID + workflowName;
			new File(destFolder).mkdir();
			
			
			outputMessageText((int) Float.parseFloat(txtCount));
			// findLinkForDownload();
		}
	}

	public String getStatus(String WFstatus) throws Exception {
		while (WFstatus.equalsIgnoreCase("New") || WFstatus.contains("Started")) {
			// System.out.println(status.getText());

			getfluentWait(refresh);

			refresh.click();
			Thread.sleep(1000);
			WFstatus = this.status.getText();
			// System.out.println(WFstatus);
		}
		return WFstatus;
	}

	public void outputMessageText(int Textcount) throws Exception {
		for (int i = 3; i <= Textcount; i++) {
			String expectedResult = getValue("Resultparam" + i).trim();

			String TextResult = webDriver
					.findElement(By.xpath("//tbody[@class='tbody-stripped'][1]/tr[2]/td/div/span[2]/span[" + i + "]"))
					.getText();
			// webDriver.findElement(By.xpath("//tbody[@class='tbody-stripped'][1]/tr[2]/td/div/span[2]/span[4]"+"/span/a")).click();
			WebElement block = webDriver
					.findElement(By.xpath("//tbody[@class='tbody-stripped'][1]/tr[2]/td/div/span[2]/span[" + i + "]"));

			List<WebElement> linkCount = block.findElements(By.tagName("a"));
			if (linkCount.size() > 0) {
				List<WebElement> outputfile = block.findElements(By.tagName("b"));
				String outputfilename = outputfile.get(0).getText();
				String FileResult = outputfilename.replace(":", "").trim();
				if (expectedResult.contains(FileResult)) {
					System.out.println("mactch" + FileResult);

					WebElement clickOnFile = webDriver.findElement(By.xpath(
							"//tbody[@class='tbody-stripped'][1]/tr[2]/td/div/span[2]/span[" + i + "]" + "/span/a"));
					clickOnFile.getAttribute("id");
					getfluentWait(clickOnFile);
					// Thread.sleep(1000);
					clickOnFile.click();
					Thread.sleep(1000);
					System.out.println(isFileDownloaded(downloadPath, FileResult));
					if (isFileDownloaded(downloadPath, FileResult)) {
                        //Path of source folder  
						String srcFilePath = downloadPath + FileResult;
						File srcFile = new File(srcFilePath);
						
						//path of destination folder
						String destFile= destFolder	+ "\\"+ srcFile.getName();
						File destinationFolder = new File(destFile);
				        
						//move file to destination folder and check isFilePresent
						boolean renameResult = srcFile.renameTo(destinationFolder);
						System.out.println("Use java io to move from " + srcFilePath + " to " + destinationFolder);
						if (renameResult) {
							System.out.println(" success. ");
							compareDownloadedFiles(destFile,System.getProperty("user.dir") + "/src/test/resources/WorkflowFiles/"+getValue("Resultparam"+i));
						} else {
							System.out.println(" fail. ");
						}
					}
				}

				if (TextResult.equalsIgnoreCase(expectedResult)) {
					System.out.println("true");
				} else {
					System.out.println("false");
				}
			}
		}
	}

	public boolean isFileDownloaded(String downloadPath, String fileName) {
		boolean flag = false;
		File dir = new File(downloadPath);
		File[] dir_contents = dir.listFiles();

		for (int i = 0; i < dir_contents.length; i++) {
			if (dir_contents[i].getName().equals(fileName))
				return flag = true;
		}

		return flag;
	}

	public Boolean compareDownloadedFiles(String actualFile, String expectedFile) throws Exception {

		BufferedReader reader1 = new BufferedReader(new FileReader(actualFile));

		BufferedReader reader2 = new BufferedReader(new FileReader(expectedFile));

		String line1 = reader1.readLine();

		String line2 = reader2.readLine();

		boolean areEqual = true;

		int lineNum = 1;

		while (line1 != null || line2 != null) {
			if (line1 == null || line2 == null) {
				areEqual = false;
				break;
			} 
			else if (!line1.equalsIgnoreCase(line2)) {
				areEqual = false;
				break;
			}

			line1 = reader1.readLine();

			line2 = reader2.readLine();

			lineNum++;
		}

		if (areEqual) {
			System.out.println("Two files have same content.");
		} else {
			System.out.println("Two files have different content. They differ at line " + lineNum);

			System.out.println("File1 has " + line1 + " and File2 has " + line2 + " at line " + lineNum);
		}

		reader1.close();

		reader2.close();
		return areEqual;
	}

}