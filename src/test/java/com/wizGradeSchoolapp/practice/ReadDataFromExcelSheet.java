package com.wizGradeSchoolapp.practice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataFromExcelSheet {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		//Step-1:Read the file using file input stream
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\Data\\testdata.xlsx");
		//Step-2:Create workbook
		Workbook wb = WorkbookFactory.create(fis);
		//Step-3:Load the sheet
		Sheet st = wb.getSheet("Sheet1");
		//Step-4:Navigate to the row
		Row row = st.getRow(1);
		//Step-5:Read the value inside the cell
		Cell cel = row.getCell(2);
		//step-6: read the value inside the cellcel.getStringCellValue()
		System.out.println(cel.getStringCellValue());
		
	}

}
