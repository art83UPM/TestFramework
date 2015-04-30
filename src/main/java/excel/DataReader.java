package excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import spike.error.EmptyDataAccessError;
import spike.error.TypeDataAccessError;

public class DataReader {

    private XSSFWorkbook workbook;

    private XSSFSheet currentSheet;

    private XSSFRow currentRow;

    private Iterator<Row> rowIterator;

    private HashMap<String, Integer> headers;

    public DataReader(String filePath) {
        this.workbook = this.readWorkbookFromFile(filePath);
        this.currentSheet = this.workbook.getSheetAt(0);
        this.rowIterator = this.currentSheet.iterator();
        this.next();
        this.fillHeaders();
    }

    private XSSFWorkbook readWorkbookFromFile(String filePath) {
        XSSFWorkbook workbook = null;
        try {
            FileInputStream fis = new FileInputStream(new File(filePath));
            workbook = new XSSFWorkbook(fis);
        } catch (FileNotFoundException e) {
            System.out.println("Fichero " + filePath + " no encontrado");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workbook;
    }
    
    private void fillHeaders() {
        this.headers = new HashMap<String, Integer>();
        for (Cell cell : this.currentRow) {
            this.headers.put(cell.getStringCellValue(), cell.getColumnIndex());
        }
    }

    public void reset() {
        this.rowIterator = this.currentSheet.iterator();
    }

    public boolean next() {
        boolean hasNext =  this.hasNext();
        if (hasNext) {
            this.currentRow = (XSSFRow) this.rowIterator.next();
        } 
        return hasNext;    
    }

    public boolean hasNext() {
        return this.rowIterator.hasNext();
    }

    public HashMap<String, Integer> getHeaders() {
        return headers;
    }

    public int getRow() {
        return this.currentRow.getRowNum();
    }

    public String getString(String name) throws EmptyDataAccessError, TypeDataAccessError {
        return this.getCell(name).getStringCellValue();
    }

    public int getInt(String name) throws EmptyDataAccessError, TypeDataAccessError {
        return (int)this.getCell(name).getNumericCellValue();
    }

    public float getFloat(String name) throws EmptyDataAccessError, TypeDataAccessError {
        return (float)this.getCell(name).getNumericCellValue();
    }

    public double getDouble(String name) throws EmptyDataAccessError, TypeDataAccessError {
        return this.getCell(name).getNumericCellValue();
    }

    public boolean getBoolean(String name) throws EmptyDataAccessError, TypeDataAccessError {
        return this.getCell(name).getBooleanCellValue();
    }
    
    private int getHeader(String name) {
        return this.headers.get(name);
    }
    
    private Cell getCell(String header) {
        return this.currentRow.getCell(this.getHeader(header));
    }
}
