package com.wizGradeSchoolapp.generic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * its developed using Apache poi libraries, which used to handle Microsoft excel sheet
 * @author Asmita
 *
 */
public class ExcelUtilitityLibraries {
/**
 * its used to read data from excel workbook based on below
 * @param sheetName
 * @param rowNum
 * @param celNum
 * @return String data
 * @throws IOException 
 * @throws EncryptedDocumentException 
 * @throws Throwable
 */
	public String getExcelData(String sheetName, int rowNum, int celNum) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream(IConstants.EXCELPATH);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		Row row = sh.getRow(rowNum);
		String data = row.getCell(celNum).getStringCellValue();
		return data;
		
	}
	/**
	 * used to get the last used row number on specified sheet
	 * @param sheetName
	 * @return  
	 * @throws IOException 
	 * @throws EncryptedDocumentException 
	 * @throws Throwable
	 */
		public int getRowCount(String sheetName) throws EncryptedDocumentException, IOException {
			FileInputStream fis=new FileInputStream(IConstants.EXCELPATH);
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sh = wb.getSheet(sheetName);
			wb.close();
			return sh.getLastRowNum();
		}
		/**
		 * used to write data back to excel based on below parameter
		 * @param sheetName
		 * @param rowNum
		 * @param celNum
		 * @param data
		 * @throws Throwable
		 */
		public void setDataExcel(String sheetName, int rowNum, int celNum, String data) throws Throwable {
			FileInputStream fis=new FileInputStream(IConstants.EXCELPATH);
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sh = wb.getSheet(sheetName);
			Row row = sh.getRow(rowNum);
			Cell cel = row.createCell(celNum);
			cel.setCellValue(data);
			FileOutputStream fos=new FileOutputStream(IConstants.EXCELPATH);
			wb.write(fos);
			wb.close();
		}
}
