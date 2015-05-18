package code;

public class Margin {

    private static Margin margin = new Margin();

    public static Margin instance() {
        return margin;
    }

    private int level;

    private Margin() {
        level = 0;
    }

    public void inc() {
        level++;
    }

    public void dec() {
        level--;
    }

    public String tabs() {
        String tabs = "";
        for (int i = 0; i < level; i++) {
            tabs += "   ";
        }
        return tabs;
    }
}
