package readers;

import readers.exceptions.DataReaderException;
import readers.exceptions.InvalidDataSheetException;
import readers.exceptions.TypeDataReaderException;

public abstract class TestDataReader {

    private DataReader dataReader;

    public TestDataReader(String excelFile) {
        this.dataReader = new DataReader(excelFile);
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
    
    public void setMethodToTest(String name) {
        try {
            this.getDataReader().setSheet(name);
        } catch (InvalidDataSheetException e) {
            System.out.println("The sheet \""+name+"\" does not exist");
            System.exit(0);
        }
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
