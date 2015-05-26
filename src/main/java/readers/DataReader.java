package readers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import readers.exceptions.DataReaderException;
import readers.exceptions.EmptyDataReaderException;
import readers.exceptions.InvalidDataSheetException;
import readers.exceptions.InvalidHeaderDataReaderException;
import readers.exceptions.TypeDataReaderException;

public class DataReader {

    private XSSFWorkbook workbook;

    private DataSheet currentSheet;

    private HashMap<String, DataSheet> sheets;

    public DataReader(String filePath) {
        this.workbook = this.readWorkbookFromFile(filePath);
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

    public void setSheet(String name) {
        if (!this.sheets.containsKey(name)) {
            try {
                this.sheets.put(name, new DataSheet(this.workbook.getSheet(name)));
            } catch (InvalidDataSheetException e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }
        }
        this.currentSheet = this.sheets.get(name);
    }

    public void reset() {
        this.currentSheet.reset();
    }

    public void next() {
        assert this.currentSheet == null : "currentSheet not set, use setSheet before calling next";
        this.currentSheet.next();
    }

    public boolean hasNext() {
        return this.currentSheet.hasNext();
    }

    public List<String> getHeaders() {
        return this.currentSheet.getHeaders();
    }
    
    private Cell getCell(String name) throws EmptyDataReaderException {
        Cell cell = null;
        try {
            cell = this.currentSheet.getCell(this.currentSheet.getHeader(name));
        } catch (InvalidHeaderDataReaderException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        if (this.isEmptyCell(cell)) {
            throw new EmptyDataReaderException("The cell in row " + this.currentSheet.getRowNum() + " for header " + name + " is empty");
        }
        return cell;
    }

    public String getString(String name) throws TypeDataReaderException, EmptyDataReaderException {
        Cell cell = this.getCell(name);
        if (!this.isStringCell(cell)) {
            throw new TypeDataReaderException("Data in row " + this.currentSheet.getRowNum() + " for header " + name + " is not a String");
        }
        String value = cell.getStringCellValue();
        if (!this.checkStringFormat(value)) {
            throw new TypeDataReaderException("Incorrect String format in row " + this.currentSheet.getRowNum() + " for header " + name);
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

    public int getInt(String name) throws TypeDataReaderException, EmptyDataReaderException {
        Cell cell = this.getCell(name);
        if (this.isNumericCell(cell)) {
            double value = cell.getNumericCellValue();
            if ((value == Math.floor(value))) {
                return (int) value;
            }
        }
        throw new TypeDataReaderException("Data in row " + this.currentSheet.getRowNum() + " for header " + name + " is not an Integer");
    }

    public float getFloat(String name) throws TypeDataReaderException, EmptyDataReaderException {
        Cell cell = this.getCell(name);
        if (!this.isNumericCell(cell)) {
            throw new TypeDataReaderException("Data in row " + this.currentSheet.getRowNum() + " for header " + name + " is not a Float");
        }
        return (float) cell.getNumericCellValue();
    }

    public double getDouble(String name) throws TypeDataReaderException, EmptyDataReaderException {
        Cell cell = this.getCell(name);
        if (!this.isNumericCell(cell)) {
            throw new TypeDataReaderException("Data in row " + this.currentSheet.getRowNum() + " for header " + name + " is not a Double");
        }
        return cell.getNumericCellValue();
    }

    public boolean getBoolean(String name) throws TypeDataReaderException, EmptyDataReaderException {
        Cell cell = this.getCell(name);
        if (this.isStringCell(cell)) {
            if (cell.getStringCellValue().equalsIgnoreCase("true")) {
                return true;
            } else if (cell.getStringCellValue().equalsIgnoreCase("false")) {
                return false;
            } else {
                throw new TypeDataReaderException("The String in row " + this.currentSheet.getRowNum() + " for header " + name
                        + " is not \"true\" or \"false\"");
            }
        } else if (!this.isBooleanCell(cell)) {
            throw new TypeDataReaderException("Data in row " + this.currentSheet.getRowNum() + " for header " + name
                    + " is not a Boolean or a String with the values \"true\" or \"false\"");
        }
        return cell.getBooleanCellValue();
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
        return this.currentSheet.getRowNum();
    }

    public int getDataRowCount() {
        return this.currentSheet.getPhysicalNumberOfRows() - 1;
    }
}
