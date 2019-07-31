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
import org.testng.Assert;

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
	String workflowMessage;
	private static String downloadPath = "..\\com.automation.selenium\\src\\downloads";

	public RequestPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	@FindBy(linkText = "Requests")
	WebElement requestsTab;

	@FindBy(xpath = "//tbody[@class='tbody-stripped'][1]/tr/td[3]/span[1]")
	WebElement status;

	@FindBy(name = "refresh-btn")
	WebElement refresh;
	
	@FindBy(xpath = "//*[@id='selectedColumns']/div/button")
	WebElement showColumnsButton;

	public void checkWorkflowStatus() throws Exception {
		getfluentWait(requestsTab);
		requestsTab.click();
		webDriver.findElement(By.xpath("//tbody[@class='tbody-stripped']"));
		List<WebElement> getCol = webDriver.findElements(By.xpath("//tbody[@class='tbody-stripped'][1]/tr/td"));
		//System.out.println("No of columns" + getCol.size());

		int count = 0;
		for (int col = 1; col < getCol.size(); col++) {
			requestvalue = webDriver.findElement(By.xpath("//tbody[@class='tbody-stripped'][1]/tr/td[" + col + "]"))
					.getText();
			if (count == 0) {
				workflowID = requestvalue;
				//System.out.println(workflowID);
			} else if (count == 1) {
				workflowName = requestvalue;
				//System.out.println(workflowName);
			}
			count++;
		}

		workflowStatus = status.getText();
		String WFstatus = getStatus(workflowStatus);
		System.out.println("Final Status=" + WFstatus);

		if (WFstatus.equalsIgnoreCase(getValue("ResultStatus"))) {
			//System.out.println("check the result");
			webDriver.findElement(By.xpath("//tbody[@class='tbody-stripped'][1]/tr/td[3]/span[2]")).click();

			// if message is correct then only check all details
			if (validateRequestMessage()) {
				String txtCount = getValue("OutputTextcount");
				//System.out.println(txtCount);
				// make folder to move the files
				destFolder = "D:\\Tejaswini_Workflow\\" + workflowID + workflowName;
				new File(destFolder).mkdir();
				// int messagecount=(int) Float.parseFloat(txtCount);

				System.out.println("inside");
				outputMessageText((int) Float.parseFloat(txtCount));

			}
			// outputMessageText((int) Float.parseFloat(txtCount));
		} else {
			Assert.fail("Workflow Result not matched");
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

	public Boolean validateRequestMessage() {
		workflowMessage = webDriver.findElement(By.xpath("//tbody[@class='tbody-stripped']/tr[2]/td/div/span[1]"))
				.getText();
		if (workflowMessage.equalsIgnoreCase(getValue("Message"))) {
			return true;
		} else
			return false;
	}

	public void outputMessageText(int Textcount) throws Exception {
		System.out.println(Textcount);
		if (Textcount != 0) {
			System.out.println("inside");
			for (int i = 1; i <= Textcount; i++) {
				String expectedResult = getValue("Resultparam" + i).trim();

				String TextResult = webDriver
						.findElement(
								By.xpath("//tbody[@class='tbody-stripped'][1]/tr[2]/td/div/span[2]/span[" + i + "]"))
						.getText();
				// webDriver.findElement(By.xpath("//tbody[@class='tbody-stripped'][1]/tr[2]/td/div/span[2]/span[4]"+"/span/a")).click();
				WebElement block = webDriver.findElement(
						By.xpath("//tbody[@class='tbody-stripped'][1]/tr[2]/td/div/span[2]/span[" + i + "]"));

				List<WebElement> linkCount = block.findElements(By.tagName("a"));
				if (linkCount.size() > 0) {
					List<WebElement> outputfile = block.findElements(By.tagName("b"));
					String outputfilename = outputfile.get(0).getText();
					String FileResult = outputfilename.replace(":", "").trim();
					if (expectedResult.contains(FileResult)) {
						System.out.println("mactch" + FileResult);

						WebElement clickOnFile = webDriver
								.findElement(By.xpath("//tbody[@class='tbody-stripped'][1]/tr[2]/td/div/span[2]/span["
										+ i + "]" + "/span/a"));
						clickOnFile.getAttribute("id");
						getfluentWait(clickOnFile);
						// Thread.sleep(1000);
						clickOnFile.click();
						Thread.sleep(5000);
						System.out.println(isFileDownloaded(downloadPath, FileResult));
						if (isFileDownloaded(downloadPath, FileResult)) {
							// Path of source folder
							String srcFilePath = downloadPath + FileResult;
							File srcFile = new File(srcFilePath);

							// path of destination folder
							String destFile = destFolder + "\\" + srcFile.getName();
							File destinationFolder = new File(destFile);

							// move file to destination folder and check
							// isFilePresent
							boolean renameResult = srcFile.renameTo(destinationFolder);
							//System.out.println("Use java io to move from " + srcFilePath + " to " + destinationFolder);
							if (renameResult) {
								System.out.println(" success. ");
								compareDownloadedFiles(destFile, System.getProperty("user.dir")
										+ "/src/test/resources/WorkflowFiles/" + getValue("Resultparam" + i));
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
			} else if (!line1.equalsIgnoreCase(line2)) {
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
	
	
	//Rajesh
	public void selectShowcolumnOptionButton() throws InterruptedException {
		getfluentWait(requestsTab);
		requestsTab.click();
		getfluentWait(showColumnsButton);
		showColumnsButton.click();
		Thread.sleep(1000);
		// selectDropDownOption(showColumnsButton, "Check all", "Text");
	}

	public void selectShowcolumnOptions(int optionIndex) {
		WebElement columnOpt = webDriver.findElement(By.xpath("//*[@id='selectedColumns']/div/ul"));
		// System.out.println("Total options are : "+columnOpt.getSize());
		List<WebElement> li_tags = columnOpt.findElements(By.tagName("li"));
		// System.out.println(li_tags.size());
		int value = li_tags.size();
		for (int i = 0; i < value; i++) {
			String optText = li_tags.get(i).getText();
			System.out.println("---show column options----" + optText);

			WebElement selectOpt = li_tags.get(optionIndex);
			selectOpt.click();

		}

	}


}