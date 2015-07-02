package readers;


import java.util.List;

import readers.exceptions.EmptyDataReaderException;
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
    
	private String getCurrentSheet() {
		return this.dataReader.getCurrentSheet().getName();
	}

	public String getLocationMessage() {
		return "Testing row: " + this.getCurrentRow() + " from sheet: " + this.getCurrentSheet();
	}

	public boolean hasNext() {
		this.setTestTarget("Constructors");
		return this.getDataReader().hasNext();
	}
	
    public void setTestTarget(String name) {
        this.getDataReader().setSheet(name);
    }
    
    public List<String> getConstructors() {
        this.setTestTarget("Constructors");
        return this.getDataReader().getHeaders();
    }
    
    protected int getInt(String columnName) throws EmptyDataReaderException {
        int result = 0;
        try {
        result = dataReader.getInt(columnName);
        } catch (TypeDataReaderException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return result;
    }

    protected float getFloat(String columnName) throws EmptyDataReaderException {
        float result = (float) 0.0;
        try {
        result = dataReader.getFloat(columnName);
        } catch (TypeDataReaderException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return result;
    }

    protected double getDouble(String columnName) throws EmptyDataReaderException {
        double result = 0.0;
        try {
        result = dataReader.getDouble(columnName);
        } catch (TypeDataReaderException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return result;
    }

    protected boolean getBoolean(String columnName) throws EmptyDataReaderException {
        boolean result = false;
        try {
        result = dataReader.getBoolean(columnName);
        } catch (TypeDataReaderException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return result;
    }

    protected String getString(String columnName) throws EmptyDataReaderException {
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
