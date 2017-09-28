package Model;

import Data.Coord;
import Data.PixelsDifference;
import javafx.scene.paint.Color;

import java.awt.Canvas;
import java.util.ArrayList;

/**
 * Created by zy on 27/09/2017.
 */
public class CanvasInteraction {

    private Canvas permanentCanvas;

    private Canvas temporaryCanvas;

    public CanvasInteraction(Canvas permanentCanvas, Canvas temporaryCanvas) {
        this.permanentCanvas = permanentCanvas;
        this.temporaryCanvas = temporaryCanvas;
    }

    public void drawFree(ArrayList<Coord> coordList, Color color) {


    }

    public void startDrawFree(Coord start, Color color) {

    }

    public void continueDrawFree(Coord current, Color color) {

    }

    public void stopDrawFree(Coord end, Color color) {

    }

    public void drawFree(PixelsDifference pixels) {

    }

    public void drawLine(Coord start, Coord end, Color color, int lineStyle) {

    }

    public void drawRectangle(Coord start, Coord end, Color color, int lineStyle, boolean isFilled) {

    }

    public void drawOval(Coord start, Coord end, Color color, int lineStyle, boolean isFilled) {

    }

    public void drawCircle(Coord start, Coord end, Color color, int lineStyle, boolean isFilled) {

    }

    public void selectArea(Coord start, Coord end) {

    }

    public void unselectArea() {

    }
}
