package Data;

/**
 * Created by zy on 20/09/2017.
 */
public class Pixel {

    private int coordinateX;
    private int coordinateY;
    private int colorValue;

    Pixel(int coordinateX, int coordinateY, int colorValue) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.colorValue = colorValue;
    }

    public int x() {
        return coordinateX;
    }

    public int y() {
        return coordinateY;
    }

    public int color() {
        return colorValue;
    }

    @Override
    public String toString() {
        return "(" + coordinateX + ","
                   + coordinateY + ","
                   + colorValue + ")";
    }
}
