package com.qa.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil
{

	private static String TEST_DATA_SHEET_QA = "./src/test/resources/testdata/LensKartTestDataSheet_qa.xlsx";
	private static String TEST_DATA_SHEET_DEV = "./src/test/resources/testdata/LensKartTestDataSheet_dev.xlsx";
	private static String TEST_DATA_SHEET_PRODUCTION = "./src/test/resources/testdata/LensKartTestDataSheet_production.xlsx";
	private static String TEST_DATA_SHEET_STAGING = "./src/test/resources/testdata/LensKartTestDataSheet_staging.xlsx";
	private static Workbook book;
	private static Sheet sheet;

	public static Object[][] getTestData(String sheetName)
	{

		Object data[][] = null;
		String excelSheetString = System.getProperty("excel");
		FileInputStream fileInputStream = null;

		try
		{
			
			if (excelSheetString == null)
			{
				System.out.println("Taking data from production excel sheet ....");
				fileInputStream = new FileInputStream(TEST_DATA_SHEET_PRODUCTION);
			}
			else
			{
				System.out.println("Taking data from" + excelSheetString + " excel sheet ....");
				switch (excelSheetString)
				{
				case "qa":
					fileInputStream = new FileInputStream(TEST_DATA_SHEET_QA);
					break;
				case "dev":
					fileInputStream = new FileInputStream(TEST_DATA_SHEET_DEV);
					break;
				case "staging":
					fileInputStream = new FileInputStream(TEST_DATA_SHEET_STAGING);
					break;
				case "production":
					fileInputStream = new FileInputStream(TEST_DATA_SHEET_PRODUCTION);
					break;

				default:
					System.out.println("No excel sheet found.....");
				}
			}
			
			
			book = WorkbookFactory.create(fileInputStream);
			sheet = book.getSheet(sheetName);
			
			data = new Object[sheet.getLastRowNum()][sheet.getRow(1).getLastCellNum()];

			for (int i = 0; i < sheet.getLastRowNum(); i++)
			{
				for (int j = 0; j < sheet.getRow(1).getLastCellNum(); j++)
				{
					data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
				}
			}

		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return data;
	}

}
