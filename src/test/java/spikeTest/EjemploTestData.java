package spikeTest;

import spike.DataAccess;
import spike.Example;

public class EjemploTestData {
    private DataAccess dataAccess;

    private String excel;

    public EjemploTestData() {
        excel = "EjemploTestData.xls";
    }
    
    public void reset() {
        dataAccess.reset();
    }

    public boolean next() {
        return dataAccess.next();
    }

    public boolean hasNext() {
        return dataAccess.hasNext();
    }
    
    public Example getEjemplo() {
        return null;
    }

    public int getM1Result() {
        return dataAccess.getInt("getM1Result");
    }

    public int getM1IntX() {
        return dataAccess.getInt("getM1IntX");
    }       

    public int getM1IntResult() {
        return dataAccess.getInt("getM1IntResult");
    }

    public int getM1FloatResult() {
        return dataAccess.getInt("getM1FloatResult");
    }

    public float getM1FloatX() {
        return dataAccess.getFloat("getM1FloatX");
    }

    public int getM1DoubleResult() {
        return dataAccess.getInt("getM1DoubleResult");
    }

    public double getM1DoubleX() {
        return dataAccess.getDouble("getM1DoubleX");
    }

    public int getM1BooleanResult() {
        return dataAccess.getInt("getM1BooleanResult");
    }

    public boolean getM1BooleanX() {
        return dataAccess.getBoolean("getM1BooleanX");
    }

    public int getM1StringResult() {
        return dataAccess.getInt("getM1BooleanResult");
    }

    public String getM1StringX() {
        return dataAccess.getString("getM1StringX");
    }

    public int getM1IntIntResult() {
        return dataAccess.getInt("getM1IntIntResult");
    }

    public int getM1IntIntX() {
        return dataAccess.getInt("getM1IntIntX");
    }

    public int getM1IntIntY() {
        return dataAccess.getInt("getM1IntIntY");
    }

}
