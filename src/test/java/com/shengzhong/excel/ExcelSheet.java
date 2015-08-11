package com.shengzhong.excel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelSheet {
	XSSFWorkbook wb;
	XSSFSheet sheet;
	FileOutputStream fileOut;
	public ExcelSheet(){}
	
	public Workbook createWorkBook(String fileLocation, String fileName) throws IOException {
		wb = new XSSFWorkbook();
	    fileOut = new FileOutputStream(fileLocation+fileName);
	    sheet = wb.createSheet("ErycSheet");
	    Cell cell = null;
	    XSSFRow row = sheet.createRow(0);
	    sheet.setColumnWidth(0,11000);
	    sheet.setColumnWidth(1,11000);
	    sheet.setColumnWidth(2,2500);
	    sheet.setColumnWidth(3,12000);
	    sheet.setColumnWidth(4,18000);
	    sheet.setColumnWidth(5,4000);
	    cell = row.createCell(0);
	    cell.setCellValue("Title");
	    cell = row.createCell(1);
	    cell.setCellValue("Publication");
	    cell = row.createCell(2);
	    cell.setCellValue("Year");
	    cell = row.createCell(3);
	    cell.setCellValue("Authors");
	    cell = row.createCell(4);
	    cell.setCellValue("Author Details");
	    cell = row.createCell(5);
	    cell.setCellValue("Country");
		return wb;
	}
	
	public void tearDownExcel() throws IOException {
		wb.write(fileOut);
	    fileOut.close();
	}
	
	public void addNewRow(String Title, String Publication, String Year, String Authors, String AuthorDetails, String Country) {
		int rownum = sheet.getLastRowNum();
		XSSFRow newRow = sheet.createRow(rownum+1);
		newRow.setHeight((short) 2000);
	    Cell cell = null;
		cell = newRow.createCell(0);
	    cell.setCellValue(Title);
	    cell = newRow.createCell(1);
	    cell.setCellValue(Publication);
	    cell = newRow.createCell(2);
	    cell.setCellValue(Year);
	    cell = newRow.createCell(3);
	    cell.setCellValue(Authors);
	    cell = newRow.createCell(4);
	    cell.setCellValue(AuthorDetails);
	    cell = newRow.createCell(5);
	    cell.setCellValue(Country);
	}
}
