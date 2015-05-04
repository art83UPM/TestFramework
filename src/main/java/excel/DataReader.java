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
import spike.error.EmptyDataSheetException;
import spike.error.TypeDataAccessError;

public class DataReader {

    private XSSFWorkbook workbook;

    private XSSFSheet currentSheet;

    private XSSFRow currentRow;

    private Iterator<Row> rowIterator;

    private HashMap<String, Integer> headers;

    public DataReader(String filePath) throws EmptyDataSheetException {
        this.workbook = this.readWorkbookFromFile(filePath);
        this.currentSheet = this.workbook.getSheetAt(0);
        this.rowIterator = this.currentSheet.iterator();
        this.next();
        this.fillHeaders();
        if (!this.hasNext()) {
            throw new EmptyDataSheetException();
        }
        this.next();
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

    public void next() {
        if (this.hasNext()) {
            this.currentRow = (XSSFRow) this.rowIterator.next();
        } 
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
        Cell cell = this.getCell(name);
        this.checkCellErrors(cell, Cell.CELL_TYPE_STRING);
        String value = cell.getStringCellValue();
        if (!(value.startsWith("\"") && value.endsWith("\""))) {
            throw new TypeDataAccessError();
        }
        return value.substring(1, value.length()-1);
    }

    public int getInt(String name) throws EmptyDataAccessError, TypeDataAccessError {
        Cell cell = this.getCell(name);
        this.checkCellErrors(cell, Cell.CELL_TYPE_NUMERIC);
        double value = cell.getNumericCellValue();
        if (value % (int)value != 0) {
            throw new TypeDataAccessError();
        }
        return (int)value;
    }

    public float getFloat(String name) throws EmptyDataAccessError, TypeDataAccessError {
        Cell cell = this.getCell(name);
        this.checkCellErrors(cell, Cell.CELL_TYPE_NUMERIC);
        return (float)cell.getNumericCellValue();
    }

    public double getDouble(String name) throws EmptyDataAccessError, TypeDataAccessError {
        Cell cell = this.getCell(name);
        this.checkCellErrors(cell, Cell.CELL_TYPE_NUMERIC);
        return cell.getNumericCellValue();
    }

    public boolean getBoolean(String name) throws EmptyDataAccessError, TypeDataAccessError {
        Cell cell = this.getCell(name);
        this.checkCellErrors(cell, Cell.CELL_TYPE_STRING);
        if (cell.getStringCellValue().equalsIgnoreCase("true")) {
            return true;
        } else if (cell.getStringCellValue().equalsIgnoreCase("false")) {
            return false;
        } else {
            throw new TypeDataAccessError();
        }
    }
    
    private int getHeader(String name) {
        return this.headers.get(name);
    }
    
    private Cell getCell(String header) {
        return this.currentRow.getCell(this.getHeader(header));
    }
    
    private void checkCellErrors(Cell cell, int cellType) throws EmptyDataAccessError, TypeDataAccessError {
        if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
            throw new EmptyDataAccessError();
        } else if (cell.getCellType() != cellType) {
            throw new TypeDataAccessError();
        }
    }
}
