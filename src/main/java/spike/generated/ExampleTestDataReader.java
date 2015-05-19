package spike.generated;

import exceptions.DataReaderException;
import exceptions.EmptyDataReaderException;
import exceptions.InvalidDataReaderException;
import readers.TestDataReader;
import spike.Example;

public class ExampleTestDataReader extends TestDataReader {

    private Example example;

    public ExampleTestDataReader() {
        super("C:\\Users\\nyuron\\Desktop\\Example.xlsx");
    }

    public boolean hasNext() {
        this.example = null;
        return this.getDataReader().hasNext();
    }
    
    public Example next() {
        this.getDataReader().next();
      //TODO: Improve this code
        try {
            tryCase0();
        } catch (EmptyDataReaderException e) {
            try { 
                tryCase1();
            } catch (EmptyDataReaderException e1) {
                try {
                    tryCase2();
                } catch (EmptyDataReaderException e2) {
                    System.out.println("No constructor defined at row "+this.getDataReader().getRow());
                    System.exit(0);
                } catch (DataReaderException e2) {
                    System.out.println(e2.getMessage());
                }
            } catch (DataReaderException e1) {
                System.out.println(e1.getMessage());
            }
        } catch (DataReaderException e) {
            e.printStackTrace();
        }
        return this.example;
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
        String x = this.getString("getExample");
        if (!x.equalsIgnoreCase("x")) {
            throw new InvalidDataReaderException("Data under column \"getExample\" at row: " + this.getDataReader().getRow()
                    + " should be x or X");
        }
        this.example = new Example();
        return true;
    }

    private boolean tryCase1() throws DataReaderException {
        int value1 = this.getInt("getExampleIntValue1");
        this.example = new Example(value1);
        return true;
    }

    private boolean tryCase2() throws DataReaderException {
        int value1 = this.getInt("getExampleIntIntValue1");
        int value2 = this.getInt("getExampleIntIntValue2");
        this.example = new Example(value1, value2);
        return true;
    }

    public Example getExample() {
        return example;
    }

    public int getM1Result() {
        int result = 0;
        try {
            return this.getInt("getM1Result");
        } catch (DataReaderException e) {
            System.out.println("Error in getM1Result");
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return result;
    }

    public int getM1IntX() {
        int result = 0;
        try {
            return this.getInt("getM1IntX");
        } catch (DataReaderException e) {
            System.out.println("Error in getM1IntX");
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return result;
    }

    public int getM1IntResult() {
        int result = 0;
        try {
            return this.getInt("getM1IntResult");
        } catch (DataReaderException e) {
            System.out.println("Error in getM1IntResult");
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return result;
    }

    public int getM1FloatResult() {
        int result = 0;
        try {
            return this.getInt("getM1FloatResult");
        } catch (DataReaderException e) {
            System.out.println("Error in getM1FloatResult");
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return result;
    }

    public float getM1FloatX() {
        float result = 0;
        try {
            return this.getFloat("getM1FloatX");
        } catch (DataReaderException e) {
            System.out.println("Error in getM1FloatX");
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return result;
    }

    public int getM1DoubleResult() {
        int result = 0;
        try {
            return this.getInt("getM1DoubleResult");
        } catch (DataReaderException e) {
            System.out.println("Error in getM1DoubleResult");
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return result;
    }

    public double getM1DoubleX() {
        double result = 0;
        try {
            return this.getDouble("getM1DoubleX");
        } catch (DataReaderException e) {
            System.out.println("Error in getM1DoubleX");
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return result;

    }

    public int getM1BooleanResult() {
        int result = 0;
        try {
            return this.getInt("getM1BooleanResult");
        } catch (DataReaderException e) {
            System.out.println("Error in getM1BooleanResult");
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return result;
    }

    public boolean getM1BooleanX() {
        boolean result = false;
        try {
            return this.getBoolean("getM1BooleanX");
        } catch (DataReaderException e) {
            System.out.println("Error in getM1BooleanX");
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return result;
    }

    public int getM1StringResult() {
        int result = 0;
        try {
            return this.getInt("getM1StringResult");
        } catch (DataReaderException e) {
            System.out.println("Error in getM1StringResult");
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return result;
    }

    public String getM1StringX() {
        String result = null;
        try {
            return this.getString("getM1StringX");
        } catch (DataReaderException e) {
            System.out.println("Error in getM1StringX");
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return result;
    }

    public int getM1IntIntResult() {
        int result = 0;
        try {
            return this.getInt("getM1IntIntResult");
        } catch (DataReaderException e) {
            System.out.println("Error in getM1IntIntResult");
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return result;
    }

    public int getM1IntIntX() {
        int result = 0;
        try {
            return this.getInt("getM1IntIntX");
        } catch (DataReaderException e) {
            System.out.println("Error in getM1IntIntX");
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return result;
    }

    public int getM1IntIntY() {
        int result = 0;
        try {
            return this.getInt("getM1IntIntY");
        } catch (DataReaderException e) {
            System.out.println("Error in getM1IntIntY");
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return result;
    }

}
