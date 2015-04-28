package spike;

public class Example {
    private int value;

    public Example(int value) {
        this.value = value;
    }

    public int m1() {
        return value;
    }

    public int m1(int x) {
        return value + x;
    }

    public int m1(float x) {
        return value + (int) x;
    }

    public int m1(double x) {
        return value + (int) x;
    }

    public int m1(boolean x) {
        return value + (x ? 1 : 0);
    }

    public int m1(String x) {
        return value + Integer.parseInt(x);
    }

    public int m1(int x, int y) {
        return value + x + y;
    }
}
