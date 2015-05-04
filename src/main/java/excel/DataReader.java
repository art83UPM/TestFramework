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

import spike.error.EmptyDataSheetException;
import spike.error.EmptyDataReaderErrorException;
import spike.error.TypeDataReaderErrorException;

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

    public String getString(String name) throws EmptyDataReaderErrorException, TypeDataReaderErrorException {
        Cell cell = this.getCell(name);
        if (!this.isStringCell(cell)) {
            throw new TypeDataReaderErrorException();
        }
        String value = cell.getStringCellValue();
        if (!(value.startsWith("\"") && value.endsWith("\""))) {
            throw new TypeDataReaderErrorException();
        }
        return value.substring(1, value.length() - 1);
    }

    public int getInt(String name) throws EmptyDataReaderErrorException, TypeDataReaderErrorException {
        Cell cell = this.getCell(name);
        if (!this.isNumericCell(cell)) {
            throw new TypeDataReaderErrorException();
        }
        double value = cell.getNumericCellValue();
        if (value % (int) value != 0) {
            throw new TypeDataReaderErrorException();
        }
        return (int) value;
    }

    public float getFloat(String name) throws EmptyDataReaderErrorException, TypeDataReaderErrorException {
        Cell cell = this.getCell(name);
        if (!this.isNumericCell(cell)) {
            throw new TypeDataReaderErrorException();
        }
        return (float) cell.getNumericCellValue();
    }

    public double getDouble(String name) throws EmptyDataReaderErrorException, TypeDataReaderErrorException {
        Cell cell = this.getCell(name);
        if (!this.isNumericCell(cell)) {
            throw new TypeDataReaderErrorException();
        }
        return cell.getNumericCellValue();
    }

    public boolean getBoolean(String name) throws EmptyDataReaderErrorException, TypeDataReaderErrorException {
        Cell cell = this.getCell(name);
        if (!this.isStringCell(cell)) {
            throw new TypeDataReaderErrorException();
        }
        if (cell.getStringCellValue().equalsIgnoreCase("true")) {
            return true;
        } else if (cell.getStringCellValue().equalsIgnoreCase("false")) {
            return false;
        } else {
            throw new TypeDataReaderErrorException();
        }
    }

    private int getHeader(String name) {
        return this.headers.get(name);
    }

    private Cell getCell(String header) throws EmptyDataReaderErrorException {
        Cell cell = this.currentRow.getCell(this.getHeader(header));;
        if (this.isEmptyCell(cell)) {
            throw new EmptyDataReaderErrorException();
        }
        return cell;
    }

    private boolean isNumericCell(Cell cell) {
        return cell.getCellType() == Cell.CELL_TYPE_NUMERIC
                || (cell.getCellType() == Cell.CELL_TYPE_FORMULA && cell.getCachedFormulaResultType() == Cell.CELL_TYPE_NUMERIC);
    }
    
    private boolean isStringCell(Cell cell) {
        return cell.getCellType() == Cell.CELL_TYPE_STRING
                || (cell.getCellType() == Cell.CELL_TYPE_FORMULA && cell.getCachedFormulaResultType() == Cell.CELL_TYPE_STRING);
    }

    private boolean isEmptyCell(Cell cell) throws EmptyDataReaderErrorException {
        return (cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK);
    }
}
