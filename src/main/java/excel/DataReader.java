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
        FileInputStream fis;
        try {
            fis = new FileInputStream(new File(filePath));
            this.workbook = new XSSFWorkbook(fis);
        } catch (FileNotFoundException e) {
            System.out.println("Fichero " + filePath + " no encontrado");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.currentSheet = this.workbook.getSheetAt(0);
        this.rowIterator = this.currentSheet.iterator();
        this.next();
        this.fillHeaders();
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
        boolean result;
        if (result = this.hasNext()) {
            this.currentRow = (XSSFRow) this.rowIterator.next();
        } 
        return result;
    }

    public boolean hasNext() {
        return this.rowIterator.hasNext();
    }

    public HashMap<String, Integer> getHeaders() {
        return headers;
    }

    public String getRow() {
        // TODO Auto-generated method stub
        return null;
    }

    public String getColumn() {
        // TODO Auto-generated method stub
        return null;
    }

    public int getConstructMode() {
        // TODO Auto-generated method stub
        return 0;
    }

    public String getString(String string) throws EmptyDataAccessError, TypeDataAccessError {
        // TODO Auto-generated method stub
        return null;
    }

    public int getInt(String string) throws EmptyDataAccessError, TypeDataAccessError {
        // TODO Auto-generated method stub
        return 0;
    }

    public float getFloat(String string) throws EmptyDataAccessError, TypeDataAccessError {
        // TODO Auto-generated method stub
        return 0;
    }

    public double getDouble(String string) throws EmptyDataAccessError, TypeDataAccessError {
        // TODO Auto-generated method stub
        return 0;
    }

    public boolean getBoolean(String string) throws EmptyDataAccessError, TypeDataAccessError {
        // TODO Auto-generated method stub
        return false;
    }

}
