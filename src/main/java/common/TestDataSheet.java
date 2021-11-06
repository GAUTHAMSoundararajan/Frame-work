package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFName;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;


public class TestDataSheet {
//	static String PATH = "C:\\Users\\gautham\\Desktop\\testdata.xls";
//	private static HSSFWorkbook workbook;
//	private static HSSFSheet sheet;
//	public static boolean isSheetPresent() {
//		boolean ispresent = false;
//		try {
//			File file = new File(PATH);
//			if (!file.exists()) {
//				 return false;
//			}
//			System.out.println("im here test data sheet");
//			FileInputStream fi = new FileInputStream(PATH);
//			HSSFWorkbook workbook = new HSSFWorkbook(fi);
//			int sheetCount = workbook.getNumberOfSheets();
//			for(int isheet =0;isheet<sheetCount;isheet++) {
//				HSSFSheet sheet = workbook.getSheetAt(isheet);
//			if(sheet.getSheetName().equalsIgnoreCase(SHEET_NAME)) {
//					ispresent = true;
//					
//				}
//			}
//			workbook.close();
//			fi.close();
//			
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//		return ispresent;
//		
//	}
//	private static String getCellValue(HSSFCell cell) {
//		FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
//		switch(evaluator.evaluateInCell(cell).getCellType()) {
//		case Cell.CELL_TYPE_BLANK:
//			return null;
//		case Cell.CELL_TYPE_NUMERIC:
//			return cell.getNumericCellValue() +"";
//		case Cell.CELL_TYPE_STRING:
//			return cell.getStringCellValue();
//		case Cell.CELL_TYPE_BOOLEAN:
//			return cell.getBooleanCellValue()+"";
//		case Cell.CELL_TYPE_FORMULA:
//			return cell.getCellFormula();
//		}
//	
//		return null;
//		
//	}
//	private static String degenerateKey(String key) {
//		String keys = key.substring(key.lastIndexOf("_")+1);
//		if(!keys.equalsIgnoreCase(key)) {
//			try {
//				Integer.parseInt(keys);
//				key = key.substring(0, key.lastIndexOf("_"));
//			}
//			catch(NumberFormatException e) {
//				
//			}
//		}
//		return key;
//	}
//	
//public static boolean save(String key, HashMap<String,String> match,int mode,HashMap<String, String> testdata) {
//	FileOutputStream fo = null;
//	HSSFRow row =null;
//	int col = -1;
//	if(testdata.isEmpty() || testdata == null || testdata.size() == 0) {
//		return false;
//	}
//	try {
//		if(mode==0) {
//			if(sheet.getLastRowNum()<=0) {
//				row = sheet.createRow(0);
//				row.createRow(++col).setCellValue("key");
//				 row.createRow(++col).setCellValue(key + "_1");
//				}
//			else {
//				String newKey = genrateKey(key);
//				row = sheet.createRow(0);
//			col = row.getLastCellNum();
//			row.createCell(col).setCellValue(newKey);
//			}
//		}
//	}
//	return true;
//	
//}
//private static int getMaxCount(String Key) {
//	int count =0;
//	for(int icol = sheet.getRow(0).getFirstCellNum()+1;icol<=sheet.getRow(0).getLastCellNum();icol++) {
//		HSSFCell cell = sheet.getRow(0).getCell(icol);
//		if(cell == null)
//			continue;
//	}
//}
}

