package spikeTest;

import excel.DataAccess;
import spike.Example;

public class ExampleTestData {

    private DataAccess dataAccess;

    private String excel;

    private Example example;

    public ExampleTestData() {
        excel = "Example.xls";
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

    public boolean hasNext(int constructMode) {
        switch (constructMode){
        case 1:
        boolean captured = false;
        do {
            try {
                int value1  = dataAccess.getInt("getExampleIntValue1");
                captured = true;
            } catch (EmptyDataAccessError e) {
                this.next();
            } catch (TypeDataAccessError e) {
                // mensaje de error
                System.exit(0);
            }
        } while (!captured);
        return true;
        case 2:
            return false;
    }

    public Example getExample() {
        return example;
    }

    public int getConstructMode() {
        return dataAccess.getConstructMode();
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
