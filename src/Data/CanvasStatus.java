package Data;

/**
 * Created by zy on 29/09/2017.
 */
public class CanvasStatus {

    private Coord startValue;

    private Coord endValue;

    private int statusValue;

    public static final int NOTHING = 0;

    public static final int DRAW_FREE = 1;

    public static final int DRAW_LINE = 2;

    public static final int DRAW_RECTANGLE = 3;

    public static final int DRAW_OVAL = 4;

    public static final int DRAW_CIRCLE = 5;

    public static final int AREA_SELECTED = 6;

    public static final int INSIDE_SELECTED_AREA = -1;

    public static final int NEAR_SELECTED_AREA = 0;

    public static final int FAR_FROM_SELECTED_AREA = 1;

    public CanvasStatus() {

    }

    public Coord start() {
        return startValue;
    }

    public Coord end() {
        return endValue;
    }

    public int status() {
        return statusValue;
    }

    public void nothing() {
        startValue = null;
        endValue = null;
        statusValue = NOTHING;
    }

    public void drawFree(Coord start) {
        startValue = start;
        endValue = null;
        statusValue = DRAW_FREE;
    }

    public void drawLine(Coord start, Coord end) {
        startValue = start;
        endValue = end;
        statusValue = DRAW_LINE;
    }

    public void drawRectangle(Coord start, Coord end) {
        startValue = start;
        endValue = end;
        statusValue = DRAW_RECTANGLE;
    }

    public void drawOval(Coord start, Coord end) {
        startValue = start;
        endValue = end;
        statusValue = DRAW_OVAL;
    }

    public void drawCircle(Coord start, Coord end) {
        startValue = start;
        endValue = end;
        statusValue = DRAW_CIRCLE;
    }

    public void selectArea(Coord start, Coord end) {
        startValue = start;
        endValue = end;
        statusValue = AREA_SELECTED;
    }

}
