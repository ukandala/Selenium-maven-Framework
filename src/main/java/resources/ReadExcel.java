package resources;
import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	static XSSFWorkbook book;
	static XSSFSheet sheet;
	
	public ReadExcel(String excelPath) {
		try {
			File src = new File(excelPath);
			FileInputStream fls = new FileInputStream(src);
		    book = new XSSFWorkbook(fls);
		} 
			catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		}

    public String getData(String sheetName, int row, int column) {
    	sheet = book.getSheet(sheetName);
	    String allValues = sheet.getRow(row).getCell(column).getStringCellValue();
	    return allValues;
    }
    
    public int getRowCount(String name) {
    	//int numRows = book.getSheetAt(sheetIndex).getLastRowNum();
    	int numRows = book.getSheet(name).getLastRowNum();
    	return numRows;
    }

}
