package Model;

import Data.Coord;
import Data.PixelsDifference;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
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

    public void drawFree(ArrayList<Coord> coordList, Color color, int lineStyle) {

    }

    public void startDrawFree(Coord start, Color color , int lineStyle) {
        System.out.print("startDrawFree"+start+color+"|"+lineStyle+"\n");
    }

    public void continueDrawFree(Coord current, Color color, int lineStyle) {
        System.out.print("continueDrawFree"+current+color+"|"+lineStyle+"\n");
    }

    public void stopDrawFree(Coord end, Color color, int lineStyle) {
        System.out.print("stopDrawFree"+end+color+"|"+lineStyle+"\n");
    }

    public void drawFree(PixelsDifference pixels) {

    }

    public void startDrawLine(Coord start, Coord end, Color color, int lineStyle) {

    }

    public void continueDrawLine(Coord start, Coord end, Color color, int lineStyle) {

    }

    public void stopDrawLine(Coord start, Coord end, Color color, int lineStyle) {

    }

    public void startDrawRectangle(Coord start, Coord end, Color color, int lineStyle, boolean
            isFilled) {

    }

    public void continueDrawRectangle(Coord start, Coord end, Color color, int lineStyle, boolean
            isFilled) {

    }

    public void stopDrawRectangle(Coord start, Coord end, Color color, int lineStyle, boolean
            isFilled) {

    }

    public void startDrawOval(Coord start, Coord end, Color color, int lineStyle, boolean
            isFilled) {

    }

    public void continueDrawOval(Coord start, Coord end, Color color, int lineStyle, boolean
            isFilled) {

    }

    public void stopDrawOval(Coord start, Coord end, Color color, int lineStyle, boolean
            isFilled) {

    }

    public void startDrawCircle(Coord start, Coord end, Color color, int lineStyle, boolean
            isFilled) {

    }

    public void cotinueDrawCircle(Coord start, Coord end, Color color, int lineStyle, boolean
            isFilled) {

    }

    public void stopDrawCircle(Coord start, Coord end, Color color, int lineStyle, boolean
            isFilled) {

    }

    public void selectArea(Coord start, Coord end) {

    }

    public void unselectArea() {

    }

    // check if currentMouse is in the selected area, or close to, or far away from
    public int getLocationStatus(Coord currentMouse) {
        return 0;
    }

    // TBD
    public void drawText(Coord start, String content, String font, Color color) {

    }

    // TBD
    public void moveArea() {

    }

    // TBD
    public void resizeArea() {

    }

    public static void main(String[] args) {
        System.out.println("test");
    }
}
