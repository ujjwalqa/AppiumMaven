package com.app.externaldata;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadWriteExcel 
{
	ArrayList<ArrayList<String>> dataRow =  new ArrayList<ArrayList<String>>();
	
	public ArrayList<String> rowContents = new ArrayList<String>();
	
	public String[][] arraylist = new String[3][2];

	/*
	 * Read rows from xls file
	 */
	public  void readXLSFile() throws IOException
	{
		InputStream ExcelFileToRead = new FileInputStream("C:/Test.xls");
		HSSFWorkbook wb = new HSSFWorkbook(ExcelFileToRead);

		HSSFSheet sheet=wb.getSheetAt(0);
		HSSFRow row; 
		HSSFCell cell;

		Iterator<Row> rows = sheet.rowIterator();

		while (rows.hasNext())
		{
			row=(HSSFRow) rows.next();
			Iterator<Cell> cells = row.cellIterator();

			while (cells.hasNext())
			{
				cell=(HSSFCell) cells.next();

				if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
				{
					//dataRow.add(cell.getStringCellValue());
					System.out.print(cell.getStringCellValue()+" ");
				}
				else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
				{
					//dataRow.add((cell.getNumericCellValue()));
					System.out.print(cell.getNumericCellValue()+" ");
				}
				else
				{
					//U Can Handel Boolean, Formula, Errors
				}
			}
			System.out.println();
		}

	}

	/*
	 * Write rows from xls file
	 */
	public  void writeXLSFile() throws IOException {

		String excelFileName = "C:/Test.xls";//name of excel file

		String sheetName = "Sheet1";//name of sheet

		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(sheetName) ;

		//iterating r number of rows
		for (int r=0;r < 5; r++ )
		{
			HSSFRow row = sheet.createRow(r);

			//iterating c number of columns
			for (int c=0;c < 5; c++ )
			{
				HSSFCell cell = row.createCell(c);

				cell.setCellValue("Cell "+r+" "+c);
			}
		}

		FileOutputStream fileOut = new FileOutputStream(excelFileName);

		//write this workbook to an Outputstream.
		wb.write(fileOut);
		fileOut.flush();
		fileOut.close();
	}

	/*
	 * Read rows from xlsx file
	 */

	public  ArrayList<ArrayList<String>> readXLSXFile()

	{
		InputStream ExcelFileToRead =null;
		XSSFWorkbook  wb = null;
		XSSFSheet sheet = null;;
		XSSFRow row = null; 
		XSSFCell cell;
		
		try {
			ExcelFileToRead = new FileInputStream("TestData\\credentials.xlsx");
			wb = new XSSFWorkbook(ExcelFileToRead);
			sheet = wb.getSheetAt(0);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		Iterator<Row> rows = sheet.rowIterator();
		while (rows.hasNext())
		{
			row=(XSSFRow) rows.next();
			Iterator<Cell> cells = row.cellIterator();
			
			while (cells.hasNext())
			{
				cell=(XSSFCell) cells.next();

				if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING)
				{
					rowContents.add(cell.getStringCellValue()+"");
				}
				else if(cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC)
				{
					rowContents.add(cell.getNumericCellValue()+"");
				}
				
			}
			dataRow.add(rowContents);
			
			rowContents = new ArrayList<String>();
		}
		return dataRow;
	}

	public  String[][] readXLSXFileTest() throws IOException

	{
		InputStream ExcelFileToRead = new FileInputStream("C:/1ExcelData/Test.xlsx");
		XSSFWorkbook  wb = new XSSFWorkbook(ExcelFileToRead);


		XSSFSheet sheet = wb.getSheetAt(0);
		XSSFRow row; 
		XSSFCell cell;
		int rowCount = 0;
		int columnCount = 0;
		Iterator<Row> rows = sheet.rowIterator();

		while (rows.hasNext())
		{
			row=(XSSFRow) rows.next();
			Iterator<Cell> cells = row.cellIterator();
			while (cells.hasNext())
			{
				cell=(XSSFCell) cells.next();

				if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING)
				{
					arraylist[rowCount][columnCount] = cell.getStringCellValue()+" ";
					//System.out.print(cell.getStringCellValue()+" ");
				}
				else if(cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC)
				{
					arraylist[rowCount][columnCount] = cell.getNumericCellValue()+" ";
					//System.out.print(cell.getNumericCellValue()+" ");
				}
				else
				{
					//U Can Handel Boolean, Formula, Errors
				}
				columnCount++;
			}
			columnCount=0;
			rowCount++;
			//System.out.println();
		}
		rowCount=0;
		return arraylist;
	}
	
	/*
	 * Write rows from xlsx file
	 */
	public  void writeXLSXFile() throws IOException {

		String excelFileName = "C:/Test.xlsx";//name of excel file

		String sheetName = "Sheet1";//name of sheet

		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet(sheetName) ;

		//iterating r number of rows
		for (int r=0;r < 5; r++ )
		{
			XSSFRow row = sheet.createRow(r);

			//iterating c number of columns
			for (int c=0;c < 5; c++ )
			{
				XSSFCell cell = row.createCell(c);

				cell.setCellValue("Cell "+r+" "+c);
			}
		}

		FileOutputStream fileOut = new FileOutputStream(excelFileName);

		//write this workbook to an Outputstream.
		wb.write(fileOut);
		fileOut.flush();
		fileOut.close();
	}

	public static void main(String[] args) throws IOException {

		//		writeXLSFile();
		//		readXLSFile();
		//
		//		writeXLSXFile();
		//ReadWriteExcel rd = new ReadWriteExcel();
		//rd.readXLSXFile();
//		for (String[] string : rd.readXLSXFile()) {
//			for (String string2 : string) {
//				System.out.print(string2+ " ");
//			}
//			System.out.println();
//		}
		

	}

}