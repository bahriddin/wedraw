package Data;

import javafx.scene.paint.Color;

/**
 * Created by zy on 23/09/2017.
 */
public class Coord {

    private int xValue;
    private int yValue;

    public Coord(int xValue, int yValue) {
        this.xValue = xValue;
        this.yValue = yValue;
    }

    public Coord(double xValue, double yValue) {
        this.xValue = (int) xValue;
        this.yValue = (int) yValue;
    }

    @Override
    public String toString() {
        return " <"+xValue+","+yValue+"> ";
    }

    public int x() {
        return xValue;
    }

    public int y() {
        return yValue;
    }
}
