package excel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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

    private static final String SHEET_NAME = "DATA";

    private static final int STARTING_ROW = 1;

    private static final int STARTING_CELL = 1;

    public DataWriter(String fileName) {
        this.fileName = fileName;
        this.workbook = new XSSFWorkbook();
        this.currentSheet = this.workbook.createSheet(SHEET_NAME);
        this.currentRow = this.currentSheet.createRow(STARTING_ROW);
    }

    public void save() {
        try {
            File file = new File(fileName);
            if (file.exists()) {
                file.delete();
            }
            FileOutputStream out = new FileOutputStream(file);
            workbook.write(out);
            out.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void write(String text) {
        this.nextCell();
        this.currentCell.setCellValue(text);
    }

    private void nextCell() {
        if (this.currentCell == null) {
            this.currentCell = this.currentRow.createCell(STARTING_CELL);
        } else {
        this.currentCell = this.currentRow.createCell(this.currentCell.getColumnIndex() + 1);
        }
    }

    public void nextRow() {
        this.currentRow = this.currentSheet.createRow(this.currentRow.getRowNum() + 1);
        this.currentCell = this.currentRow.createCell(STARTING_CELL);
    }

}
