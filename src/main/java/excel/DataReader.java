package excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import spike.error.EmptyDataAccessError;
import spike.error.TypeDataAccessError;

public class DataReader {

    private String fileName;

    private XSSFWorkbook workbook;

    private XSSFSheet currentSheet;

    private XSSFRow currentRow;

    private XSSFCell currentCell;

    private static final int STARTING_ROW = 0;

    private static final int STARTING_CELL = 0;

    public DataReader(String fileName) {
        this.fileName = fileName;
        try {
            this.workbook = new XSSFWorkbook(fileName);
        } catch (IOException e1) {
            System.out.println("Error en la lectura del fichero " + fileName);
            System.exit(0);
        }
        this.currentSheet = this.workbook.getSheetAt(0);
        this.currentRow = this.currentSheet.createRow(STARTING_ROW);
        this.currentCell = this.currentRow.createCell(STARTING_CELL);
        try {
            XSSFRow row;
            FileInputStream fis = new FileInputStream(new File("Example.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet spreadsheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = spreadsheet.iterator();
            while (rowIterator.hasNext()) {
                row = (XSSFRow) rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_NUMERIC:
                        System.out.print(cell.getNumericCellValue() + " \t\t ");
                        break;
                    case Cell.CELL_TYPE_STRING:
                        System.out.print(cell.getStringCellValue() + " \t\t ");
                        break;
                    }
                }
                System.out.println();
            }
            fis.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void reset() {
        // TODO Auto-generated method stub

    }

    public boolean next() {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean hasNext() {
        // TODO Auto-generated method stub
        return false;
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
