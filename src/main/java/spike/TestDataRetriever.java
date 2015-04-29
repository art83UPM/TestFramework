package spike;

import excel.DataAccess;
import spike.error.EmptyDataAccessError;
import spike.error.TypeDataAccessError;

public abstract class TestDataRetriever {

    private DataAccess dataAccess;
    
    public TestDataRetriever(String excelFile) {
       // this.dataAccess = new DataAccess(excelFile);
    }

    protected DataAccess getDataAccess() {
        return dataAccess;
    }

    public void reset() {
        dataAccess.reset();
    }

    public boolean next() {
        return dataAccess.next();
    }    
    
    protected int getInt(String columnName) {
        int result = 0; 
        try {
            result = dataAccess.getInt(columnName);
        } catch (EmptyDataAccessError e) {
            this.next();
        } catch (TypeDataAccessError e) {
            System.out.println("Ha introducido un valor de un tipo diferente al esperado en la fila " + dataAccess.getRow() + " - columna " + dataAccess.getColumn() + ". Se esperaba un número entero.");
        }
        return result;
    }
    
    protected float getFloat(String columnName) {
        float result = (float) 0.0; 
        try {
            result = dataAccess.getFloat(columnName);
        } catch (EmptyDataAccessError e) {
            this.next();
        } catch (TypeDataAccessError e) {
            System.out.println("Ha introducido un valor de un tipo diferente al esperado en la fila " + dataAccess.getRow() + " - columna " + dataAccess.getColumn() + ". Se esperaba un número decimal.");
        }
        return result;
    }
    
    protected double getDouble(String columnName) {
        double result = 0.0; 
        try {
            result = dataAccess.getDouble(columnName);
        } catch (EmptyDataAccessError e) {
            this.next();
        } catch (TypeDataAccessError e) {
            System.out.println("Ha introducido un valor de un tipo diferente al esperado en la fila " + dataAccess.getRow() + " - columna " + dataAccess.getColumn() + ". Se esperaba un número decimal.");
        }
        return result;
    }
    
    protected boolean getBoolean(String columnName) {
        boolean result = false; 
        try {
            result = dataAccess.getBoolean(columnName);
        } catch (EmptyDataAccessError e) {
            this.next();
        } catch (TypeDataAccessError e) {
            System.out.println("Ha introducido un valor de un tipo diferente al esperado en la fila " + dataAccess.getRow() + " - columna " + dataAccess.getColumn() + ". Se esperaba un valor booleano.");
        }
        return result;
    }
    
    protected String getString(String columnName) {
        String result = null; 
        try {
            result = dataAccess.getString(columnName);
        } catch (EmptyDataAccessError e) {
            this.next();
        } catch (TypeDataAccessError e) {
            System.out.println("Ha introducido un valor de un tipo diferente al esperado en la fila " + dataAccess.getRow() + " - columna " + dataAccess.getColumn() + ". Se esperaba un palabra, frase o texto.");
        }
        return result;
    }
    

}
