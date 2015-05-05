package spike.generated;

import spike.Example;
import spike.TestDataReader;
import spike.error.EmptyDataReaderErrorException;
import spike.error.InvalidDataReaderErrorException;
import spike.error.TypeDataReaderErrorException;

public class ExampleTestDataReader extends TestDataReader {

    private Example example;

    public ExampleTestDataReader() {
        super("Example.xls");
    }

    public boolean hasNext() {
        return this.getDataReader().hasNext();
    }

    public boolean hasNext(int constructMode) {
        boolean captured = false;
        switch (constructMode) {
        case 0:            
            do {
                try {
                    captured = this.tryCase0();                    
                } catch (EmptyDataReaderErrorException e) {
                    this.emptyDataReaderErrorCatch();                    
                } catch (TypeDataReaderErrorException e) {
                    this.typeDataReaderErrorCatch("Ha introducido un valor de un tipo diferente al esperado en la fila "
                            + this.getDataReader().getRow() + " - columna " + this.getDataReader().getColumn()
                            + ". Se esperaba un carácter 'x'.");
                }
            } while (!captured);
            return true;
        case 1:
            do {
                try {                    
                    captured = this.tryCase1();
                } catch (EmptyDataReaderErrorException e) {
                    this.emptyDataReaderErrorCatch();
                } catch (TypeDataReaderErrorException e) {
                    this.typeDataReaderErrorCatch("Ha introducido un valor de un tipo diferente al esperado en la fila "
                            + this.getDataReader().getRow() + " - columna " + this.getDataReader().getColumn()
                            + ". Se esperaba un número entero.");
                }
            } while (!captured);
            return true;
        case 2:
            do {
                try {                    
                    captured = this.tryCase2();                    
                } catch (EmptyDataReaderErrorException e) {
                    this.emptyDataReaderErrorCatch();
                } catch (TypeDataReaderErrorException e) {
                    this.typeDataReaderErrorCatch("Ha introducido un valor de un tipo diferente al esperado en la fila "
                            + this.getDataReader().getRow() + " - columna " + this.getDataReader().getColumn()
                            + ". Se esperaba un número entero.");
                }
            } while (!captured);
            return true;
        default:
            System.out.println("Construct mode error in hasnest(int)");
            return false;
        }
    }

    private boolean tryCase0() throws EmptyDataReaderErrorException, TypeDataReaderErrorException {
        String x = this.getDataReader().getString("getExample");
        this.example = new Example();
        if (x != "x" || x != "X") {
            try {
                throw new InvalidDataReaderErrorException();
            } catch (InvalidDataReaderErrorException e) {
                super.invalidDataReaderErrorCatch("Debe introducir 'x' o 'X' en la fila " + this.getDataReader().getRow() + " - columna "
                        + this.getDataReader().getColumn() + ".");
            }
        }
        return true;        
    }
    
    private boolean tryCase1() throws EmptyDataReaderErrorException, TypeDataReaderErrorException {
        int value1 = this.getDataReader().getInt("getExampleIntValue1");
        this.example = new Example(value1);
        return true;
    }
    
    private boolean tryCase2() throws EmptyDataReaderErrorException, TypeDataReaderErrorException {
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
