package spike;

import spike.error.DataReaderException;
import spike.error.InvalidDataSheetException;
import excel.DataReader;

public abstract class TestDataReader {

    private DataReader dataReader;

    public TestDataReader(String excelFile) {
        try {
            this.dataReader = new DataReader(excelFile);
        } catch (InvalidDataSheetException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    protected DataReader getDataReader() {
        return dataReader;
    }

    public void reset() {
        dataReader.reset();
    }

    protected int getInt(String columnName) {
        int result = 0;
        try {
            result = dataReader.getInt(columnName);
        } catch (DataReaderException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    protected float getFloat(String columnName) {
        float result = (float) 0.0;
        try {
            result = dataReader.getFloat(columnName);
        } catch (DataReaderException e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    protected double getDouble(String columnName) {
        double result = 0.0;
        try {
            result = dataReader.getDouble(columnName);
        } catch (DataReaderException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    protected boolean getBoolean(String columnName) {
        boolean result = false;
        try {
            result = dataReader.getBoolean(columnName);
        } catch (DataReaderException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    protected String getString(String columnName) {
        String result = null;
        try {
            result = dataReader.getString(columnName);
        } catch (DataReaderException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    

}
