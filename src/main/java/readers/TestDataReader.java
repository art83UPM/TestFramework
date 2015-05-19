package readers;

import exceptions.DataReaderException;
import exceptions.InvalidDataSheetException;
import exceptions.TypeDataReaderException;

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

    public int getCurrentRow() {
        return this.getDataReader().getRow();
    }
    
    protected int getInt(String columnName) throws DataReaderException {
        int result = 0;
        try {
        result = dataReader.getInt(columnName);
        } catch (TypeDataReaderException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return result;
    }

    protected float getFloat(String columnName) throws DataReaderException {
        float result = (float) 0.0;
        try {
        result = dataReader.getFloat(columnName);
        } catch (TypeDataReaderException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return result;
    }

    protected double getDouble(String columnName) throws DataReaderException {
        double result = 0.0;
        try {
        result = dataReader.getDouble(columnName);
        } catch (TypeDataReaderException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return result;
    }

    protected boolean getBoolean(String columnName) throws DataReaderException {
        boolean result = false;
        try {
        result = dataReader.getBoolean(columnName);
        } catch (TypeDataReaderException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return result;
    }

    protected String getString(String columnName) throws DataReaderException {
        String result = null;
        try {
            result = dataReader.getString(columnName);
        } catch (TypeDataReaderException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return result;
    }

    

}
