package Data;

/**
 * Created by zy on 29/09/2017.
 */
public class CanvasStatus {

    private Coord startValue;

    private Coord endValue;

    private int resizeTypeValue;

    private int statusValue;

    private Coord lastValue;

    public static final int NOTHING = 0;

    public static final int DRAW_FREE = 1;

    public static final int DRAW_LINE = 2;

    public static final int DRAW_RECTANGLE = 3;

    public static final int DRAW_OVAL = 4;

    public static final int DRAW_CIRCLE = 5;

    public static final int AREA_SELECTED = 6;

    // ready for moving
    public static final int INSIDE_SELECTED_AREA = 0;

    // ready for vertically resizing
    public static final int VERTICALLY_NEAR = 1;

    // ready for horizontally resizing
    public static final int HORIZONTALLY_NEAR = 2;

    // ready for resizing in all directions
    public static final int JUST_NEAR = 3;

    // not ready for moving or resizing
    public static final int FAR_FROM_SELECTED_AREA = 4;

    public static final int FIXED_TOP = 0;

    public static final int FIXED_BOTTOM = 1;

    public static final int FIXED_LEFT = 2;

    public static final int FIXED_RIGHT = 3;

    public static final int FIXED_TOPLEFT = 4;

    public static final int FIXED_TOPRIGHT = 5;

    public static final int FIXED_BOTTOMLEFT = 6;

    public static final int FIXED_BOTTOMRIGHT = 7;

    public CanvasStatus() {

    }

    public Coord start() {
        return startValue;
    }

    public Coord end() {
        return endValue;
    }

    public int resizeType() {
        return resizeTypeValue;
    }

    public Coord last() {
        return lastValue;
    }

    public int status() {
        return statusValue;
    }

    public void nothing() {
        startValue = null;
        endValue = null;
        lastValue = null;
        resizeTypeValue = NOTHING;
        statusValue = NOTHING;
    }

    public void drawFree(Coord start) {
        startValue = start;
        endValue = null;
        lastValue = null;
        resizeTypeValue = NOTHING;
        statusValue = DRAW_FREE;
    }

    public void drawLine(Coord start, Coord end) {
        startValue = start;
        endValue = end;
        lastValue = null;
        resizeTypeValue = NOTHING;
        statusValue = DRAW_LINE;
    }

    public void drawRectangle(Coord start, Coord end) {
        startValue = start;
        endValue = end;
        lastValue = null;
        resizeTypeValue = NOTHING;
        statusValue = DRAW_RECTANGLE;
    }

    public void drawOval(Coord start, Coord end) {
        startValue = start;
        endValue = end;
        resizeTypeValue = NOTHING;
        statusValue = DRAW_OVAL;
    }

    public void drawCircle(Coord start, Coord end) {
        startValue = start;
        endValue = end;
        lastValue = null;
        resizeTypeValue = NOTHING;
        statusValue = DRAW_CIRCLE;
    }

    public void selectArea(Coord start, Coord end) {
        startValue = start;
        endValue = end;
        lastValue = null;
        resizeTypeValue = NOTHING;
        statusValue = AREA_SELECTED;
    }

    public void moveArea(Coord start, Coord end, Coord last) {
        selectArea(start, end);
        lastValue = last;
    }

    public void resizeArea(Coord start, Coord end, int moveType) {
        selectArea(start, end);
        resizeTypeValue = moveType;
    }

}
