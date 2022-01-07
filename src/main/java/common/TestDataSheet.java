package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFName;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.velocity.runtime.log.Log;
import org.testng.TestException;


public class TestDataSheet {
	private String PATH = System.getProperty("user.dir")+ File.separator + "src" + File.separator + "test"+ File.separator+"resources"+ File.separator+"testdata"+ File.separator+"Testdata.xls";
	private HSSFWorkbook workbook;
	private HSSFSheet sheet;
	private int iEnvCol = -1;
	private int iCommonCol = -1;
	private String sheetName ;
	private String environment;
	
	public TestDataSheet() throws TestException{
		initialize();
	
	}
	
	
	public  boolean isSheetPresent() {
		boolean ispresent = false;
		try {
			File file = new File(this.PATH);
			if (!file.exists()) {
				 return false;
			}
			System.out.println("im here test data sheet");
			FileInputStream fi = new FileInputStream(PATH);
			HSSFWorkbook workbook = new HSSFWorkbook(fi);
			int sheetCount = workbook.getNumberOfSheets();
			for(int isheet =0;isheet<sheetCount;isheet++) {
				HSSFSheet sheet = workbook.getSheetAt(isheet);
			if(sheet.getSheetName().equalsIgnoreCase(sheetName)) {
					ispresent = true;
					
				}
			}
			workbook.close();
			fi.close();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return ispresent;
		
	}
	private  String getCellValue(HSSFCell cell) {
		FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
		switch(evaluator.evaluateInCell(cell).getCellType()) {
		case Cell.CELL_TYPE_BLANK:
			return null;
		case Cell.CELL_TYPE_NUMERIC:
			return cell.getNumericCellValue() +"";
		case Cell.CELL_TYPE_STRING:
			return cell.getStringCellValue();
		case Cell.CELL_TYPE_BOOLEAN:
			return cell.getBooleanCellValue()+"";
		case Cell.CELL_TYPE_FORMULA:
			return cell.getCellFormula();
		}
	
		return null;
		
	}
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
	
/* this method returns value for the given key . if duplicate key found then it will return the first occurance */

public String get(String parameter) throws TestException {
	String value = null;
	DataFormatter objData = new DataFormatter();
	FormulaEvaluator objEval = new HSSFFormulaEvaluator((HSSFWorkbook)this.workbook);
	
	String result = null;
	for(int rowCount =1; rowCount <= this.sheet.getLastRowNum(); rowCount++  ) {
		HSSFRow row = this.sheet.getRow(rowCount);
		if(row == null)
			continue;
		HSSFCell keyCell = row.getCell(0);
		System.out.println(keyCell);
		if(keyCell == null)
			continue;
		String key = keyCell.getStringCellValue();
		System.out.println("keyssss"+key);
		boolean found = false;
		HSSFCell valueCell = row.getCell(this.iEnvCol);
	
		if( valueCell != null && key != null) {
			objEval.evaluate(valueCell);
			value = objData.formatCellValue(valueCell, objEval);
			
			if(value!= null && !value.trim().isEmpty())
				found =true;
			}
		value = valueCell.getStringCellValue();
		System.out.println("XZsaz"+this.iCommonCol);
		System.out.println("XZsazzzzz"+this.iEnvCol);
		if(!found) {
			valueCell = row.getCell(this.iCommonCol);
			if(valueCell!= null) {
				valueCell.setCellType(Cell.CELL_TYPE_STRING);
				value = valueCell.getStringCellValue();
				if(value == null || value.trim().isEmpty())
					value="";
				else
					found = true;
			}
			else {
				value = "";
			}
				
		}
		System.out.println("vaLUESSV"+value);
		
		if(key!= null && !key.trim().isEmpty() && (key.trim().equalsIgnoreCase(parameter.trim()) || key.trim().replace(" ", "_").equalsIgnoreCase(parameter.trim().replace(" ", "_")))) {
			result = value;
			break;
		}
	}
	
	if(result==null) {
		throw new TestException(parameter + " parameter not found in test data sheet'"+this.sheetName +"'");
	}
	try {
		workbook.close();
		workbook = null;
	}
	catch(Exception e){		
	}
	return result;
	
}

private void initialize() throws TestException {

	File file = null;
	found:{
		String tempath  = this.PATH;
		file = new File(tempath);
		
		if(file.exists()) 
			break found;
		
		PropertiesHandler properties = new PropertiesHandler("config");
		try {
			properties.get("TEST_DATA_FILE_NAME");
		}
		catch(TestException e) {
			throw new TestException("Test data file is missing in default path or enter your test data excel name in config.properties using key TEST_DATA_FILE_NAME");
		}
		file = new File(tempath);
		if(file.exists()){
			this.PATH = file.getAbsolutePath();
			break found;
		}
		
		tempath = System.getProperty("user.dir")+ File.separator + "src" + File.separator + "test"+ File.separator+"resources"+ File.separator+"testdata"+ File.separator+properties.get("TEST_DATA_FILE_NAME");
		System.out.println("asdsada");
		file = new File(tempath);
		if(file.exists()){
			this.PATH = file.getAbsolutePath();
			break found;
		}
		else {
			throw new TestException("Test data excel is missinf in path :"+ file.getAbsolutePath());
		}
	}
		if(this.environment==null) {
			PropertiesHandler properties = new PropertiesHandler("config");
			this.environment = properties.get("ENVIRONMENT");
		}
		if(this.environment==null) {
			throw new TestException("enter your Environment name in config.properties using key ENVIRONMENT");
			}
		if(this.workbook==null || this .sheet == null) {
			try {
				System.out.println("sdfsdfds: "+this.PATH);
				File fs = new File(this.PATH);
				FileInputStream inputStream = new FileInputStream(fs);
				this.workbook = new HSSFWorkbook(inputStream);
			}
			catch(Exception e) {
				e.printStackTrace();
				throw new TestException("Error occured while trying to load excel sheet from path:"+ this.PATH);
			}
			HSSFSheet tempSheet = null;
			if(this.sheetName==null) {
				this.sheet = this.workbook.getSheetAt(0);
			}
			else {
				boolean isFound = false;
				for(int i =0; i<this.workbook.getNumberOfSheets();i++) {
					tempSheet = this.workbook.getSheetAt(i);
					if(tempSheet.getSheetName().trim().equalsIgnoreCase(this.sheetName)) {
						this.sheet= tempSheet;
						isFound=true;
						break;
					}
				}
				
				if(isFound)
					throw new TestException("Could not able to find '"+ this.sheetName +"' sheet in Test Data Excel");
			}
				HSSFRow row = this.sheet.getRow(0);
				for(int i = 0; i<row.getLastCellNum();i++) {
					HSSFCell cell = row.getCell(i);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					String colName = cell.getStringCellValue();
					if(colName.equalsIgnoreCase("common"))
						this.iCommonCol = i;
					if(colName.equalsIgnoreCase(this.environment))
						this.iEnvCol= i;
				}
				this.sheetName = this.sheet.getSheetName();
				if(iEnvCol==-1) {
					throw new TestException("Could not able to find environment name '"+this.environment+"' as header in Test Data Excel");
				}
			}
		if(this.workbook==null || this .sheet == null) 
			throw new TestException("Could not able to load '"+ this.sheetName +"' sheet in Test Data Excel");	
		}


public void set(String parameter, String value) throws TestException {
	
	int LastEmptyRow = -1;
	boolean isFound = false;
	for(int i =1;i<=this.sheet.getLastRowNum();i++) {
		HSSFRow row = this.sheet.getRow(i);
		HSSFCell keyCell = null;
		try {
			keyCell = row.getCell(0);
			if(keyCell==null)
				continue;
		}
		catch(NullPointerException e) {
			continue;
		}
		keyCell.setCellType(Cell.CELL_TYPE_STRING);
		HSSFCell valueCell = row.getCell(this.iEnvCol);
		if(valueCell == null)
			valueCell = row.createCell(this.iEnvCol);
		valueCell.setCellType(Cell.CELL_TYPE_STRING);
		
		String key = keyCell.getStringCellValue();
		
		if(key==null || key.trim().isEmpty() && LastEmptyRow == -1)
			LastEmptyRow =i;
		
		if(key != null && !key.trim().isEmpty() &&(key.trim().equalsIgnoreCase(parameter.trim())|| key.trim().replace(" ", "_").equalsIgnoreCase(parameter.trim().replace(" ", "_")))) {
			keyCell.setCellValue(key.trim().replace(" ", "_"));
			valueCell.setCellValue(value);
			isFound = true;
			break;
		}
	}
		if(!isFound) {
			HSSFRow newData = null;
			if(LastEmptyRow!=-1) {
				newData= this.sheet.createRow(LastEmptyRow);
			}
			else {
				newData= this.sheet.createRow(this.sheet.getLastRowNum()+1);
			}
			
			HSSFCell keyCell = newData.createCell(0,Cell.CELL_TYPE_STRING);
			 keyCell.setCellValue(parameter.replace(" ", "_"));
			 int idestCol = -1;
			 HSSFRow header = this.sheet.getRow(0);
			 for(int j = 0;j<header.getLastCellNum();j++) {
				 HSSFCell cell = header.getCell(j);
				 if(cell == null)
					 continue;
				 
				 cell.setCellType(cell.CELL_TYPE_STRING);
				 String colName = cell .getStringCellValue();
				 if(colName.equalsIgnoreCase(this.environment)) {
					 this.iEnvCol= j;
				 }
				 else if(colName.equalsIgnoreCase("common")) {
					 this.iCommonCol = j;
				 }
			 }
			 if(this.iEnvCol>-1)
				 idestCol=iEnvCol;
			 else if(this.iCommonCol>-1)
				 idestCol=iCommonCol;
			 else
				 idestCol=1;
			 HSSFCell valueCell = newData.createCell(idestCol,Cell.CELL_TYPE_STRING);
			  valueCell.setCellValue(value);
		}
		System.out.println("Test data is updated with parameter key as : " +parameter+ " and value as : "+value);
		
		try {
			FileOutputStream fo = new FileOutputStream(new File(this.PATH));
			workbook.write(fo);
			fo.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new TestException("Error occured while writting paramters in Test dada");
		}
		finally{
			try {
				workbook.close();
				workbook= null;
			}
			catch(Exception e){
				
			}
		}
	
}

//private static int getMaxCount(String Key) {
//	int count =0;
//	for(int icol = sheet.getRow(0).getFirstCellNum()+1;icol<=sheet.getRow(0).getLastCellNum();icol++) {
//		HSSFCell cell = sheet.getRow(0).getCell(icol);
//		if(cell == null)
//			continue;
//	}
//}
}

