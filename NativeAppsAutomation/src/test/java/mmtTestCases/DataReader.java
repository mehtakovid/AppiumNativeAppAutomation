package mmtTestCases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class DataReader {
	
	static Workbook book;
	static Sheet sheet;
	
	public static Object[][] readDataFromExcel(String FilePath, String SheetName) throws EncryptedDocumentException, IOException {
		
		FileInputStream fis = new FileInputStream(FilePath);
		book=WorkbookFactory.create(fis);
		sheet=book.getSheet(SheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		DataFormatter formatter = new DataFormatter();
		for(int i=0;i<sheet.getLastRowNum();i++) {
			for(int k=0;k<sheet.getRow(0).getLastCellNum();k++) {
				data[i][k]=formatter.formatCellValue(sheet.getRow(i+1).getCell(k));
				
			}
		}
		return data;
		
	}

}
