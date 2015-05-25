package readers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import readers.exceptions.InvalidDataSheetException;
import readers.exceptions.InvalidHeaderDataReaderException;

public class DataSheet {

    private XSSFSheet sheet;
    
    private XSSFRow currentRow;

    private Iterator<Row> rowIterator;
    
    private HashMap<String, Integer> headers;

    public DataSheet(XSSFSheet sheet) throws InvalidDataSheetException {
        this.sheet = sheet;
        this.rowIterator = this.sheet.rowIterator();
        if (!this.hasNext()) {
            throw new InvalidDataSheetException("The excel sheet is empty and contains no headers");
        }
        this.next();
        this.fillHeaders();
        this.reset();
    }

    private void fillHeaders() {
        this.headers = new HashMap<String, Integer>();
        for (Cell cell : this.currentRow) {
            this.headers.put(cell.getStringCellValue(), cell.getColumnIndex());
        }
    }
    
    protected List<String> getHeaders() {
        return new ArrayList<String>(this.headers.keySet());
    }

    protected void next() {
        if (this.hasNext()) {
            this.currentRow = (XSSFRow) this.rowIterator.next();
        }
    }

    protected boolean hasNext() {
        assert this.rowIterator == null : "rowIterator not set";
        return this.rowIterator.hasNext();
    }

    public void reset() {
        assert this.rowIterator == null : "rowIterator not set";
        this.rowIterator = this.sheet.rowIterator();
        this.next();
        this.next();
    }
    
    public int getPhysicalNumberOfRows() {
        return this.sheet.getPhysicalNumberOfRows();
    }

    public Cell getCell(int column) {
        return this.currentRow.getCell(column);
    }

    public int getRowNum() {
        return this.getRowNum();
    }

    public int getHeader(String name) throws InvalidHeaderDataReaderException {
        if (!this.headers.containsKey(name)) {
            throw new InvalidHeaderDataReaderException("The header " + name + " does not exist");
        }
        return this.headers.get(name);
    }
}
