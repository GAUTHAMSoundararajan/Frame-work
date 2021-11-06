package common;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.DataFormatter;
//import org.apache.poi.ss.usermodel.FormulaEvaluator;
//import org.testng.TestException;
//import org.testng.internal.PropertiesFile;
//
public class DataFactory {
//	
	public static String generate(String key) {
		return null;
		
	}
//
//	public synchronized static HashMap<String, String> parseGherkinInput(String input){
//		LinkedHashMap<String,String> fieldMap = new LinkedHashMap<>();
//		try {
//			if(input == null || input.trim().isEmpty() || input.contains(":=")== false)
//				return null;
//			
//		String[] elements;
//		char seperator = 0;
//		if(input.contains("#")) {
//			seperator = '#';
//			elements = input.split("#");
//			input = input.replace("\\#", "@ESCAPE@");
//			}
//		else if(input.contains(",")) {
//			seperator = ',';
//			elements = input.split(",");
//			input = input.replace("\\,", "@ESCAPE@");
//		}
//		else {
//			elements = new String[1];
//			elements[0] =input;
//		}
//		/////////////////////////////////////
//		for(String data : elements) {
//			String key = null;
//			String value = null;
//			
//			String delimeter = null;; 
//			if(data == null || data.trim().length()<=0)
//				continue;
//			if(data.contains(":="))
//				delimeter = ":=";
//			else if(data.contains("="))
//				data.contains("=");
//			else {
//				fieldMap.put(data, "");
//				continue;
//			}
//		///////////////////////////////////////////////
//			String tempvalue =";";
//			try {
//				tempvalue = data.split(delimeter)[1].trim();
//				}
//			catch(ArrayIndexOutOfBoundsException e) {
//				tempvalue = "";
//			}
//		//	String temp = vv;
//			key = data.split(delimeter)[0].trim();
//			value = tempvalue;
//			
//			key = data.replace("@ESCAPE@", seperator+"");
//			value = data.replace("@ESCAPE@", seperator+"");
//			
//			//check for runtime data
//			Pattern pattern = Pattern.compile("\\[(.*?)\\]");
//			Matcher match = pattern.matcher(value);
//			int group = 1;
//			String runTimeValue = value;
//			while(match.find()) {
//				runTimeValue = null;
//				String parameter = match.group(group++);
//				FOUND:{
//			////////////////////////////////need to type		
//				}
//				
//			}
//			
//		try {
//			TestDa
//		}
//		}
//		}
//		catch(Exception e) {
//			
//		}
//		return null;
//		
//	}
//	
//	
//	
//	
//	
// public String excelPath() {
//	
//	 PropertiesHandler handler = new PropertiesHandler("config");
//	 String fileName  = handler.get("TEST_DATA_FILE_NAME");
//	    if(fileName == null)
//	     throw new TestException("Failed While Reading Testdata, value for 'TEST_DATA_FILE_NAME' is missing in config.properties file");
//	 String environment  = handler.get("ENVIRONMENT");
//	    if(environment == null)
//	    	  throw new TestException("Failed While Reading Testdata, value for 'ENVIRONMENT' is missing in config.properties file");
//	 
//	    
//	    String path = System.getProperty("user.dir")+ File.separator + "src"+ File.separator+"test"+ File.separator + "resources"
//                   + File.separator+"testdata"+ File.separator + handler.get("TEST_DATA_FILE_NAME");
//  System.out.println("125"+path);
//	 File file = new File(path);
//	 if(file.exists()) {
//		path =  file.getAbsolutePath();
//		 System.out.println("129"+path);
//		break found;
//	 }
//	 else{
//		 throw new TestException("Test Data File is missing in path : "+ file.getAbsolutePath());
//	 }
//	 HSSFWorkbook workbook  = new HSSFWorkbook(new FileInputStream(file));
//	 String sheetName  = handler.get("SHEET_NAME");
//	 HSSFSheet sheet = null;
//	 if(sheetName == null) {
//		System.out.println("Sheet Name not mentioned in config.properties file. so proceeding with sheet Number ");
//	  sheet = workbook.getSheetAt(0);
//	 }
//	 else {
//		 try {
//		 sheet = workbook.getSheet(sheetName); 
//		 }
//		 catch (Exception e) {
//			throw new TestException("Could not find any Sheet with name in '"+sheetName+"' in Test Data Excel '"+fileName+"'");
//		}
//	 }
//	
//	HSSFRow row = sheet.getRow(0);
//	 for(int col =0; col < row.getLastCellNum();col++) {
//		 HSSFCell cell = row.getCell(col);
//		 cell.setCellType(cell.CELL_TYPE_STRING);
//		 String colName = cell.getStringCellValue();
//		 if(colName.equalsIgnoreCase("key"))
//			 int keyCol = col;
//		 else if(colName.equalsIgnoreCase(environment))
//			 Integer envCol = col;
//	 }
//	 if(envCol == -1)
//		 throw new TestException("could not find environment : '"+environment+"' in header column in Test Data Sheet");
// }
//	
//	
//	public List<String> getter(String input){
//		String sheet = null;
//		DataFormatter obj = new DataFormatter();
//		FormulaEvaluator objEval = new HSSFFormulaEvaluator((HSSFWorkbook)this.workbook);
//		
//		List<String> values = new ArrayList<>();
//		for(int rowCount =1; rowCount <=  )
//		return null;
//		
//		
//		
//	}
//	
//	
//	
//	
//	
//	
}
