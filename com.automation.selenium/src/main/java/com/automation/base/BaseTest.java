package com.automation.base;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Hashtable;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.automation.commanutilities.BrowserFactory;

public class BaseTest {
	
	BrowserFactory driver = new BrowserFactory();

	protected static WebDriver webDriver;
	FileInputStream excelFileIS;;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	private int intNoOfRow;
	private int intIndex = 0;
	int intNoOfColumn;
	
	Hashtable<String, Hashtable<String, String>> testDataTable = new Hashtable<String, Hashtable<String, String>>();
	static Hashtable<String, String> ElementValue = new Hashtable<String, String>();

	public String loadTestData(String testDataFilePath) throws Exception {

		testDataFilePath = System.getProperty("user.dir") + "/src/test/resources/testData" + testDataFilePath + ".xlsx";
		//System.out.println(testDataFilePath);
		Row headerRow;
		Row testDataRow;
		excelFileIS = new FileInputStream(testDataFilePath);
		workbook = new XSSFWorkbook(excelFileIS);
		sheet = workbook.getSheetAt(0);
		intNoOfRow = sheet.getPhysicalNumberOfRows();
         //System.out.println(intNoOfRow+""+intIndex);
		while (intIndex < intNoOfRow) {

			org.apache.poi.ss.usermodel.Row row = null;
			org.apache.poi.ss.usermodel.Cell cell = null;
			row = sheet.getRow(intIndex);
			cell = row.getCell(0);
			String CellValue = cell.toString().trim();
			// System.out.println(CellValue);

			if (CellValue.equalsIgnoreCase("TC ID")) {

				headerRow = sheet.getRow(getCurrentRowNumber());

				testDataRow = sheet.getRow(getCurrentRowNumber() + 1);
				// System.out.println(headerRow+""+testDataRow);
				Hashtable<String, String> dataValueSet = new Hashtable<String, String>();
				int clmNo = 0;
				String hashTableTestCaseID = "";
				do {
					String header = "", testData = "";
					if (clmNo == 0) {
						hashTableTestCaseID = this.getCellValue(testDataRow, clmNo);
						// System.out.println("TestcaseID"+hashTableTestCaseID);
					} else {
						// Key Data
						header = this.getCellValue(headerRow, clmNo);
						// Value
						testData = this.getCellValue(testDataRow, clmNo);
						//System.out.println("key="+header+"value="+testData);
					}

					if (!header.equals(""))
						dataValueSet.put(header, testData);
					// System.out.println("keyvalue pair
					// hashmap=="+dataValueSet);
					clmNo++;
				} while (clmNo < headerRow.getLastCellNum());
				// put the hash-table in list

				testDataTable.put(hashTableTestCaseID, dataValueSet);
				//System.out.println(testDataTable);
				dataValueSet = null;
			}
			intIndex++;

		}
		return testDataFilePath;

	}

	public void getKey(String TestCaseID) {
		
		//System.out.println("get Hash function" + testDataTable);
		for(String hashKeyValue : testDataTable.keySet()) {
               //System.out.println("hash key value"+hashKeyValue);
			if (hashKeyValue.equalsIgnoreCase(TestCaseID)) {
				//System.out.println("eeeeeee" + hashKeyValue);
				ElementValue = testDataTable.get(hashKeyValue);
				 //System.out.println(ElementValue);
			}
		}
	
	}

	public static String getValue(String Key) {
		String elementValue = ElementValue.get(Key);

		//System.out.println("show key " + elementValue);
		return elementValue;
	}

	String getCellValue(Row testDataRow, int columnNumber) {

		Cell testDataCell = testDataRow.getCell(columnNumber);
		return testDataCell.toString().trim();

	}

	public void next() {
		intIndex++;
	}

	public int getCurrentRowNumber() {
		return intIndex;
	}

	public String getColumn(int clmNo) {
		String strCellvalue = "";
		org.apache.poi.ss.usermodel.Row row = null;
		org.apache.poi.ss.usermodel.Cell cell = null;
		row = sheet.getRow(intIndex);
		intNoOfColumn = row.getPhysicalNumberOfCells();
		//System.out.println(intNoOfColumn);
		try {
			cell = row.getCell(clmNo);
			System.out.println(cell);
			if (cell == null) {
				strCellvalue = "";
			} else {
				strCellvalue = cell.toString().trim();
				strCellvalue = cell.toString();
			}

			return strCellvalue;
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return strCellvalue;
	}
	
	
	public static boolean isClickable(By tab, WebDriver driver) 
	{
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
			 if(wait.until(ExpectedConditions.elementToBeClickable(tab)) != null){
			
			driver.findElement(tab).click();
			System.out.println("false");
			return false;
			
		}
		else {
			System.out.println("true");
			return true;
			
		}
	}
	
	public void clearHash(){
		testDataTable.clear();
		ElementValue.clear();
		intIndex=0;
	}
	
	public WebElement getfluentWait(WebElement value){
			
			Wait wait = new FluentWait(webDriver)
			.withTimeout(Duration.ofSeconds(50))
			.pollingEvery(Duration.ofSeconds(10))
			.ignoring(NoSuchElementException.class);
			System.out.println( "check fluent wait"+wait.until(ExpectedConditions.visibilityOf(value)));
			
			return null;
		
	}
	
	@BeforeSuite
	public void loadPage() throws Exception {

		webDriver = driver.startBrowser("Chrome", "http://10.51.29.24:8080/aeui/#/login");
		loadTestData("/SysadminTestData");
	}

	@AfterSuite
	public void closePage()
	{
	 webDriver.quit();
	}
	
	public void pendDate()
	{
		
	}
	
}
