package Data;

/**
 * Created by zy on 29/09/2017.
 */
public class CanvasStatus {

    private Coord startCoordValue;

    private Coord endCoordValue;

    private int statusValue;

    public static final int NOTHING = 0;

    public static final int DRAW_FREE = 1;

    public static final int DRAW_LINE = 2;

    public static final int DRAW_RECTANGLE = 3;

    public static final int DRAW_OVAL = 4;

    public static final int DRAW_CIRCLE = 5;

    public static final int SELECT_AREA = 6;

    public CanvasStatus() {

    }

    public Coord stratCoord() {
        return startCoordValue;
    }

    public Coord endCoord() {
        return endCoordValue;
    }

    public int status() {
        return statusValue;
    }

    public void nothing() {
        startCoordValue = null;
        endCoordValue = null;
        statusValue = NOTHING;
    }

    public void drawFree(Coord start) {
        startCoordValue = start;
        statusValue = DRAW_FREE;
    }

}
