package package1;

public class Class1 {
    private int value1;

    private int value2;

    public Class1() {
        value1 = 0;
        value2 = 0;
    }

    public Class1(int value1) {
        this.value1 = value1;
        this.value2 = 0;
    }

    public Class1(int value1, int value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public int m1() {
        return value1;
    }

    public int m1(int x) {
        return value1 + value2 + x;
    }

    public int m1(float x) {
        return value1 + value2 + (int) x;
    }

    public int m1(double x) {
        return value1 + value2 + (int) x;
    }

    public int m1(boolean x) {
        return value1 + value2 + (x ? 1 : 0);
    }

    public int m1(String x) {
        return value1 + value2 + Integer.parseInt(x);
    }

    public int m1(int x, int y) {
        return value1 + value2 + x + y;
    }
    
    @Override
    public String toString() {
        return "[value1: " + value1 + ", value2: "+ value2 +"]";
    }
}
