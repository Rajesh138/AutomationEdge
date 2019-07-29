/**
 * 
 */
package com.automation.commanutilities;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author Tejaswini Gawande
 *
 */
public class ReadExcelData {
	
	private String flFile;
	private int intIndex = 0;
	private int intNoOfRow;
	private int intNoOfColumn;
	private XSSFSheet sheet;
	private XSSFWorkbook workbook;
	private FileInputStream excelFileIS;

	public ReadExcelData(String filePath) {
		flFile = filePath;
		try {

			excelFileIS = new FileInputStream(flFile);
			workbook = new XSSFWorkbook(excelFileIS);
			excelFileIS.close();
			sheet = workbook.getSheetAt(0);
			intNoOfRow = sheet.getPhysicalNumberOfRows();
			System.out.println(intNoOfRow);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public boolean isDone() {
		if (intIndex < intNoOfRow) {
			return true;
		} else {
			return false;
		}
	}

	public String getColumn(int clmNo) {
		String strCellvalue = "";
		org.apache.poi.ss.usermodel.Row row = null;
		org.apache.poi.ss.usermodel.Cell cell = null;
		row = sheet.getRow(intIndex);
		intNoOfColumn = row.getPhysicalNumberOfCells();
		System.out.println(intNoOfColumn);
		try {
			cell = row.getCell(clmNo);
			if (cell == null)
				strCellvalue = "";
			else
				strCellvalue = cell.toString().trim();
			strCellvalue = cell.toString();
			return strCellvalue;
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return strCellvalue;
	}

	public void next() {
		intIndex++;
	}

	public XSSFSheet getSheet() {
		return sheet;
	}

	public int getCurrentRowNumber() {
		return intIndex;
	}

	public int getNumberOfRows() {
		return intNoOfRow;
	}

	public int getNumberOfColumnForCurrentRow() {
		System.out.println(intNoOfColumn);
		return intNoOfColumn;
	}

}
