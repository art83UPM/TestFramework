package spike.generated;

import spike.Example;
import spike.TestDataReader;
import spike.error.DataReaderException;
import spike.error.EmptyDataReaderException;
import spike.error.InvalidDataReaderException;

public class ExampleTestDataReader extends TestDataReader {

    private Example example;

    public ExampleTestDataReader() {
        super("Example.xls");
    }

    public boolean hasNext() {
        // TODO: Select the first valid constructor in the same row
        return false;
    }

    public boolean hasNext(int constructMode) {
        boolean captured = false;
        switch (constructMode) {
        case 0:
            do {
                try {
                    captured = this.tryCase0();
                } catch (EmptyDataReaderException e) {
                    this.next();
                } catch (DataReaderException e) {
                    System.out.println(e.getMessage());
                    System.exit(0);
                }
            } while (!captured);
            return true;
        case 1:
            do {
                try {
                    captured = this.tryCase1();
                } catch (EmptyDataReaderException e) {
                    this.next();
                } catch (DataReaderException e) {
                    System.out.println(e.getMessage());
                    System.exit(0);
                }
            } while (!captured);
            return true;
        case 2:
            do {
                try {
                    captured = this.tryCase2();
                } catch (EmptyDataReaderException e) {
                    this.next();
                } catch (DataReaderException e) {
                    System.out.println(e.getMessage());
                    System.exit(0);
                }
            } while (!captured);
            return true;
        default:
            System.out.println("Construct mode error in hasnest(int)");
            return false;
        }
    }

    private boolean tryCase0() throws DataReaderException {
        String x = this.getDataReader().getString("getExample");
        if (!x.equalsIgnoreCase("x")) {
            throw new InvalidDataReaderException("Data under column \"getExample\" at row: " + this.getDataReader().getRow()
                    + " should be x or X");
        }
        this.example = new Example();
        return true;
    }

    private boolean tryCase1() throws DataReaderException {
        int value1 = this.getDataReader().getInt("getExampleIntValue1");
        this.example = new Example(value1);
        return true;
    }

    private boolean tryCase2() throws DataReaderException {
        int value1 = this.getDataReader().getInt("getExampleIntIntValue1");
        int value2 = this.getDataReader().getInt("getExampleIntIntValue2");
        this.example = new Example(value1, value2);
        return true;
    }

    public Example getExample() {
        return example;
    }

    public int getM1Result() {
        return this.getInt("getM1Result");
    }

    public int getM1IntX() {
        return this.getInt("getM1IntX");
    }

    public int getM1IntResult() {
        return this.getInt("getM1IntResult");
    }

    public int getM1FloatResult() {
        return this.getInt("getM1FloatResult");
    }

    public float getM1FloatX() {
        return this.getFloat("getM1FloatX");

    }

    public int getM1DoubleResult() {
        return this.getInt("getM1DoubleResult");
    }

    public double getM1DoubleX() {
        return this.getDouble("getM1DoubleX");

    }

    public int getM1BooleanResult() {
        return this.getInt("getM1BooleanResult");
    }

    public boolean getM1BooleanX() {
        return this.getBoolean("getM1BooleanX");

    }

    public int getM1StringResult() {
        return this.getInt("getM1StringResult");
    }

    public String getM1StringX() {
        return this.getString("getM1StringX");

    }

    public int getM1IntIntResult() {
        return this.getInt("getM1IntIntResult");
    }

    public int getM1IntIntX() {
        return this.getInt("getM1IntIntX");
    }

    public int getM1IntIntY() {
        return this.getInt("getM1IntIntY");
    }

}
