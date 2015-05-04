package spike;

import excel.DataReader;
import spike.error.EmptyDataReaderErrorException;
import spike.error.TypeDataReaderErrorException;

public abstract class TestDataReeader {

    private DataReader dataAccess;

    public TestDataReeader(String excelFile) {
        this.dataAccess = new DataReader(excelFile);
    }

    protected DataReader getDataReader() {
        return dataAccess;
    }

    public void reset() {
        dataAccess.reset();
    }

    public boolean next() {
        return dataAccess.next();
    }
    
    protected void emptyDataReaderErrorCatch() {
        this.next();        
    }

    protected void typeDataReaderErrorCatch(String message) {
        System.out.println(message);
        System.exit(0);
    }
    
    protected void invalidDataReaderErrorCatch(String message) {
        System.out.println(message);
        System.exit(0);        
    }
    
    protected int getInt(String columnName) {
        int result = 0;
        try {
            result = dataAccess.getInt(columnName);
        } catch (EmptyDataReaderErrorException e) {
            this.next();
        } catch (TypeDataReaderErrorException e) {
            System.out.println("Ha introducido un valor de un tipo diferente al esperado en la fila " + dataAccess.getRow() + " - columna "
                    + dataAccess.getColumn() + ". Se esperaba un número entero.");
        }
        return result;
    }

    protected float getFloat(String columnName) {
        float result = (float) 0.0;
        try {
            result = dataAccess.getFloat(columnName);
        } catch (EmptyDataReaderErrorException e) {
            this.next();
        } catch (TypeDataReaderErrorException e) {
            System.out.println("Ha introducido un valor de un tipo diferente al esperado en la fila " + dataAccess.getRow() + " - columna "
                    + dataAccess.getColumn() + ". Se esperaba un número decimal.");
        }
        return result;
    }

    protected double getDouble(String columnName) {
        double result = 0.0;
        try {
            result = dataAccess.getDouble(columnName);
        } catch (EmptyDataReaderErrorException e) {
            this.next();
        } catch (TypeDataReaderErrorException e) {
            System.out.println("Ha introducido un valor de un tipo diferente al esperado en la fila " + dataAccess.getRow() + " - columna "
                    + dataAccess.getColumn() + ". Se esperaba un número decimal.");
        }
        return result;
    }

    protected boolean getBoolean(String columnName) {
        boolean result = false;
        try {
            result = dataAccess.getBoolean(columnName);
        } catch (EmptyDataReaderErrorException e) {
            this.next();
        } catch (TypeDataReaderErrorException e) {
            System.out.println("Ha introducido un valor de un tipo diferente al esperado en la fila " + dataAccess.getRow() + " - columna "
                    + dataAccess.getColumn() + ". Se esperaba un valor booleano.");
        }
        return result;
    }

    protected String getString(String columnName) {
        String result = null;
        try {
            result = dataAccess.getString(columnName);
        } catch (EmptyDataReaderErrorException e) {
            this.next();
        } catch (TypeDataReaderErrorException e) {
            System.out.println("Ha introducido un valor de un tipo diferente al esperado en la fila " + dataAccess.getRow() + " - columna "
                    + dataAccess.getColumn() + ". Se esperaba un palabra, frase o texto.");
        }
        return result;
    }

    

}
