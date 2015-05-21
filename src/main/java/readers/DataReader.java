package readers;

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

import readers.exceptions.DataReaderException;
import readers.exceptions.EmptyDataReaderException;
import readers.exceptions.InvalidDataSheetException;
import readers.exceptions.InvalidHeaderDataReaderException;
import readers.exceptions.TypeDataReaderException;

public class DataReader {

    private XSSFWorkbook workbook;

    private XSSFSheet currentSheet;

    private XSSFRow currentRow;

    private Iterator<Row> rowIterator;

    private HashMap<String, Integer> headers;

    public DataReader(String filePath) {
        this.workbook = this.readWorkbookFromFile(filePath);
    }

    public void setSheet(String name) throws InvalidDataSheetException {
        this.currentSheet = this.workbook.getSheet(name);
        this.rowIterator = this.currentSheet.rowIterator();
        if (!this.hasNext()) {
            throw new InvalidDataSheetException("The excel sheet is empty and contains no headers");
        }
        this.next();
        this.fillHeaders();
        if (this.hasNext()) {
            this.next();
        } else {
            this.currentRow = null;
        }
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
        assert this.rowIterator == null : "rowIterator not set";
        this.rowIterator = this.currentSheet.rowIterator();
        this.next();
        this.next();
    }

    public void next() {
        assert this.currentSheet == null : "currentSheet not set, use setSheet before calling next";
        if (this.hasNext()) {
            this.currentRow = (XSSFRow) this.rowIterator.next();
        }
    }

    public boolean hasNext() {
        assert this.rowIterator == null : "rowIterator not set";
        return this.rowIterator.hasNext();
    }

    public String getString(String name) throws DataReaderException {
        Cell cell = this.getCell(name);
        if (!this.isStringCell(cell)) {
            throw new TypeDataReaderException("Data in row " + this.currentRow.getRowNum() + " for header " + name + " is not a String");
        }
        String value = cell.getStringCellValue();
        if (!this.checkStringFormat(value)) {
            throw new TypeDataReaderException("Incorrect String format in row " + this.currentRow.getRowNum() + " for header " + name);
        }
        return value.substring(1, value.length() - 1);
    }

    private boolean checkStringFormat(String string) {
        boolean check = true;
        if (!(string.startsWith("\"") && string.endsWith("\""))) {
            check = false;
        }
        char[] charArray = string.toCharArray();
        for (int i = 1; i < charArray.length - 1; i++) {
            if (charArray[i] == '"') {
                check = charArray[i - 1] == '\\';
            }
        }
        return check;
    }

    public int getInt(String name) throws DataReaderException {
        Cell cell = this.getCell(name);
        if (this.isNumericCell(cell)) {
            double value = cell.getNumericCellValue();
            if ((value == Math.floor(value))) {
                return (int) value;
            }
        }
        throw new TypeDataReaderException("Data in row " + this.currentRow.getRowNum() + " for header " + name + " is not an Integer");
    }

    public float getFloat(String name) throws DataReaderException {
        Cell cell = this.getCell(name);
        if (!this.isNumericCell(cell)) {
            throw new TypeDataReaderException("Data in row " + this.currentRow.getRowNum() + " for header " + name + " is not a Float");
        }
        return (float) cell.getNumericCellValue();
    }

    public double getDouble(String name) throws DataReaderException {
        Cell cell = this.getCell(name);
        if (!this.isNumericCell(cell)) {
            throw new TypeDataReaderException("Data in row " + this.currentRow.getRowNum() + " for header " + name + " is not a Double");
        }
        return cell.getNumericCellValue();
    }

    public boolean getBoolean(String name) throws DataReaderException {
        Cell cell = this.getCell(name);
        if (this.isStringCell(cell)) {
            if (cell.getStringCellValue().equalsIgnoreCase("true")) {
                return true;
            } else if (cell.getStringCellValue().equalsIgnoreCase("false")) {
                return false;
            } else {
                throw new TypeDataReaderException("The String in row " + this.currentRow.getRowNum() + " for header " + name
                        + " is not \"true\" or \"false\"");
            }
        } else if (!this.isBooleanCell(cell)) {
            throw new TypeDataReaderException("Data in row " + this.currentRow.getRowNum() + " for header " + name
                    + " is not a Boolean or a String with the values \"true\" or \"false\"");
        }
        return cell.getBooleanCellValue();
    }

    private int getHeader(String name) throws InvalidHeaderDataReaderException {
        if (!this.headers.containsKey(name)) {
            throw new InvalidHeaderDataReaderException("The header " + name + " does not exist");
        }
        return this.headers.get(name);
    }

    private Cell getCell(String name) throws DataReaderException {
        Cell cell = this.currentRow.getCell(this.getHeader(name));
        if (this.isEmptyCell(cell)) {
            throw new EmptyDataReaderException("The cell in row " + this.currentRow.getRowNum() + " for header " + name + " is empty");
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

    private boolean isBooleanCell(Cell cell) {
        return cell.getCellType() == Cell.CELL_TYPE_BOOLEAN
                || (cell.getCellType() == Cell.CELL_TYPE_FORMULA && cell.getCachedFormulaResultType() == Cell.CELL_TYPE_BOOLEAN);
    }

    private boolean isEmptyCell(Cell cell) {
        return (cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK);
    }

    public int getRow() {
        return this.currentRow.getRowNum();
    }

    public int getDataRowCount() {
        return this.currentSheet.getPhysicalNumberOfRows() - 1;
    }
}
