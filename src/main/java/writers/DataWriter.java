package writers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataWriter {

    private String fileName;

    private XSSFWorkbook workbook;

    private XSSFSheet currentSheet;

    private XSSFRow currentRow;

    private XSSFCell currentCell;

    private static final int STARTING_ROW = 1;

    private static final int STARTING_CELL = 1;

    public DataWriter(String fileName) {
        this.fileName = fileName;
        this.workbook = new XSSFWorkbook();
    }

    public void save() {
        try {
            File file = new File(fileName);
            file.getParentFile().mkdirs();
            file.createNewFile();
            if (file.exists()) {
                file.delete();
            }
            FileOutputStream out = new FileOutputStream(file);
            workbook.write(out);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(String text) {
        assert this.currentSheet == null : "currentSheet not set, use setSheet before calling write";
        this.currentCell.setCellValue(text);
        this.nextCell();
    }

    private void nextCell() {
        this.setCell(this.currentCell.getColumnIndex() + 1);
    }

    public void setSheet(String name) {
        this.currentSheet = this.workbook.getSheet(name);
        if (this.currentSheet == null) {
            this.currentSheet = this.workbook.createSheet(name);
        }
        this.setRow(STARTING_ROW);
        this.setLastCell();
    }

    private void setRow(int row) {
        this.currentRow = this.currentSheet.getRow(row);
        if (this.currentRow == null) {
            this.currentRow = this.currentSheet.createRow(row);
        }
    }

    private void setCell(int cell) {
        this.currentCell = this.currentRow.getCell(cell, Row.CREATE_NULL_AS_BLANK);
    }

    private void setLastCell() {
        if (this.currentRow.getLastCellNum() < 0) {
            this.setCell(STARTING_CELL);
        } else {
            this.setCell(this.currentRow.getLastCellNum());
        }
    }

}
