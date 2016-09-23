package com.app.android;

import java.util.ArrayList;
import java.util.Iterator;

import org.testng.annotations.DataProvider;

import com.app.externaldata.ReadWriteExcel;

public class TestDataProvider {

	/**
	 * Provide user name and password from excel 
	 * @return
	 */
	@DataProvider(name="credentials")
	public static Object[][] provideUserPassword()
	{
		
		// Creating iterator object
		Iterator<ArrayList<String>> ParentIterator;
		
		// Variable to store retrieved object from the excel reader
		ArrayList<ArrayList<String>> temp = null;
		
		// Deceleration of array of object 
		Object[][] finalData = null;

		try {

			
			ReadWriteExcel rd = new ReadWriteExcel();

			temp = rd.readXLSXFile();

			ParentIterator = temp.iterator();

			/*
			 * Getting no of available in the excel
			 */
			int rowCount = temp.size();

			/*
			 * temporary row count to iterate and store the array list data into  out object array
			 */
			int tempRowCount = 0;

			/*
			 *  Getting no of the cells in the excel
			 *  Also it move to next row so that our header
			 *  from excel should get skipped
			 */
			int colCount =  ParentIterator.next().size();

			/*
			 * Creating an array of object to hold and pass
			 * rowCount-1, because oue Excel should have a header title so we are skipping those
			 */
			finalData = new Object[rowCount-1][colCount];
			
			while (ParentIterator.hasNext()) {
				
				/*
				 * Retrieve the rows as array list
				 */
				ArrayList<String> rowContents =  ParentIterator.next();
				
				/*
				 * converting an array list to an array
				 */
				Object[] tempObjArray = rowContents.toArray(new String[rowContents.size()]);
				
				/*
				 * Iterating over the row array list to store each data in two dimensional array
				 */
				for (int i=0; i<colCount; i++) 
				{
					System.out.println(tempObjArray[i].toString());
					
					/*
					 * Adding each element in the object array
					 */
					
					finalData[tempRowCount][i] = tempObjArray[i];
					
				}

				tempRowCount++ ;
			}
			
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return finalData;
	}
}
