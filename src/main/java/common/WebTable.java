package common;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.TestException;

public class WebTable {
	
private WebElement table;

/** constructor to set webelement of the table **/
public WebTable(WebElement element) {
	this.table = element;
}

/** To get the row count of the table. it starts with 1 to n **/
public int getRowCount() {
	int rowCount = 0;
	try {
	 rowCount = table.findElements(By.xpath("./tbody/tr")).size();
	if(rowCount==0) {
	 rowCount = table.findElements(By.xpath(".//tr")).size();
	}
	}
	catch (NoSuchElementException e) {		
	}
	return rowCount;
}

/** To get the row count of the table **/
public int getColumnCount() {
	int colCount =0;
	try {
		 colCount = table.findElements(By.xpath(".//th")).size();
		 List<WebElement> rows = table.findElements(By.xpath(".//tr"));
		 if (!rows.isEmpty()) {
			 for(WebElement row : rows) {
				 List<WebElement> cols = row.findElements(By.xpath(".//td"));
				 if(!cols.isEmpty() && colCount < cols.size())
					 colCount = cols.size();
			 }
		 }
	}
	catch (NoSuchElementException e) {		
	}
	return colCount;	
}

/**To get the header count of the table **/
public int getHeaderCount() {
	int headerCount =0;
	try {
		headerCount = table.findElements(By.xpath("./thead/tr/th")).size();
		if(headerCount==0) {
			headerCount = table.findElements(By.xpath("./tbody/tr[1]/th")).size();
			}
	}
	catch (NoSuchElementException e) {		
	}
	return headerCount;
	
}

/**To get all the row values of particular column **/
public List<String> getRowValues(int col){
	List<String> values = new ArrayList<String>();
	int rowCount = getRowCount();
	for(int i=1;i<=rowCount;i++) {
		values.add(getText(i, col));
	}
	return values;
}

/**To get all the column values of particular row **/	
public List<String> getColumnValues(int row){
	List<String> values = new ArrayList<String>();
	int colCount = getColumnCount();
	for(int i=1;i<=colCount;i++) {
		values.add(getText(row,i));
	}
	return values;
}

/**To get all the Header values of particular table **/
public List<String> getHeaders(){
	List<String> col = null;
	int colCount = getColumnCount();
	for(int icell =1;icell <= colCount;icell++) {
		String colHeader = getText(1,icell);
		if(colHeader != null && !colHeader.trim().isEmpty()) {
			if(col == null)
				col.add(colHeader.trim());
		}
	}
	return col;
	
	
}


//gettext

public String getText(int row, int column) {
	List<WebElement> rows = getRows();
	List<WebElement> cols = null;
	int iRow =0;
	for(WebElement eachRow :rows) {
		++iRow;
		if(row == iRow) {
			cols = eachRow.findElements(By.xpath("./th"));
			if(cols.size()==0) {
				cols = eachRow.findElements(By.xpath("./td"));
			}
			int icol =0;
			for(WebElement eachCol : cols) {
				++icol;
				String s = null;
				if(column == icol) {
					s=eachCol.getText();
					if(s==null || s.trim().isEmpty())
						s= eachCol.getAttribute("textContent");
					return s;
				}
			}
			break;
		}
	}
	return null;
	
}

private List<WebElement> getRows(){
	List<WebElement> rows = this.table.findElements(By.xpath(".//tr"));
	List<WebElement> header = new ArrayList<>();
	List<WebElement> footer = new ArrayList<>();
	List<WebElement> body = new ArrayList<>();
	for(WebElement ele : rows) {
		String parent =null;
		boolean iserror = true;
		int iMaxTime = 10;
		int iTime = 0;
		while(iserror) {
			try {
				parent = ele.findElement(By.xpath("..")).getTagName();
				iserror = false;
			}
			catch (Exception e) {
			
			}
			if(++iTime > iMaxTime)
				return null;
		}
		if(parent.equalsIgnoreCase("thead")) {
			header.add(ele);
		}
		else if(parent.equalsIgnoreCase("tfoot")) {
			footer.add(ele);
			}
		else {
			body.add(ele);
		}
	}
	rows.clear();
	for(WebElement we : header) {
		rows.add(we);
	}
	for(WebElement we : body) {
		rows.add(we);
	}
	for(WebElement we : footer) {
		rows.add(we);
	}
	
	return rows;
	
}

}
