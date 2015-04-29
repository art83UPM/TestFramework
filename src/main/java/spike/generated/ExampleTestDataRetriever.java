package spike.generated;

import spike.Example;
import spike.TestDataRetriever;
import spike.error.EmptyDataAccessError;
import spike.error.InvalidDataAccessError;
import spike.error.TypeDataAccessError;

public class ExampleTestDataRetriever extends TestDataRetriever {

    private Example example;

    public ExampleTestDataRetriever() {
        super("Example.xls");
    }

    public boolean hasNext() {
        return this.getDataAccess().hasNext();
    }

    public boolean hasNext(int constructMode) {
        boolean captured = false;
        switch (constructMode) {
        case 0:
            do {
                try {
                    String x = this.getDataAccess().getString("getExample");
                    this.example = new Example();
                    if (x != "x" || x != "X") {
                        throw new InvalidDataAccessError();
                    }
                    captured = true;
                } catch (EmptyDataAccessError e) {
                    this.next();
                } catch (TypeDataAccessError e) {
                    System.out.println("Ha introducido un valor de un tipo diferente al esperado en la fila "
                            + this.getDataAccess().getRow() + " - columna " + this.getDataAccess().getColumn()
                            + ". Se esperaba un carácter 'x'.");
                    System.exit(0);
                } catch (InvalidDataAccessError e) {
                    System.out.println("Debe introducir 'x' o 'X' en la fila " + this.getDataAccess().getRow() + " - columna "
                            + this.getDataAccess().getColumn() + ".");
                    System.exit(0);
                }
            } while (!captured);
            return true;
        case 1:
            do {
                try {
                    int value1 = this.getDataAccess().getInt("getExampleIntValue1");
                    this.example = new Example(value1);
                    captured = true;
                } catch (EmptyDataAccessError e) {
                    this.next();
                } catch (TypeDataAccessError e) {
                    System.out.println("Ha introducido un valor de un tipo diferente al esperado en la fila "
                            + this.getDataAccess().getRow() + " - columna " + this.getDataAccess().getColumn()
                            + ". Se esperaba un número entero.");
                    System.exit(0);
                }
            } while (!captured);
            return true;
        case 2:
            do {
                try {
                    int value1 = this.getDataAccess().getInt("getExampleIntIntValue1");
                    int value2 = this.getDataAccess().getInt("getExampleIntIntValue2");
                    this.example = new Example(value1, value2);
                    captured = true;
                } catch (EmptyDataAccessError e) {
                    this.next();
                } catch (TypeDataAccessError e) {
                    System.out.println("Ha introducido un valor de un tipo diferente al esperado en la fila "
                            + this.getDataAccess().getRow() + " - columna " + this.getDataAccess().getColumn()
                            + ". Se esperaba un número entero.");
                    System.exit(0);
                }
            } while (!captured);
            return true;
        default:
            System.out.println("Construct mode error in hasnest(int)");
            return false;
        }

    }

    public Example getExample() {
        return example;
    }

    public int getConstructMode() {
        return this.getDataAccess().getConstructMode();
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
