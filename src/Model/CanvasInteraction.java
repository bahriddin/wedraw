package Model;

import Data.*;

import GUI.Tools.Draw;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by zy on 27/09/2017.
 */
public class CanvasInteraction {

    private Canvas permanentCanvas;
    private Canvas temporaryCanvas;

    private CanvasLog log;

    private CanvasStatus status;

    private Draw draw;

    // determine if a pixel is near the selected area
    private static final int MAXIMUM_CLOSE_DISTANCE = 5;

    /**
     * initialize the model
     * @param permanentCanvas
     * @param temporaryCanvas
     */
    public CanvasInteraction(Canvas permanentCanvas, Canvas temporaryCanvas) {
        this.permanentCanvas = permanentCanvas;
        this.temporaryCanvas = temporaryCanvas;

        log = new CanvasLog(CanvasHelper.canvasToMatrix(permanentCanvas));

        status = new CanvasStatus();

        Canvas[] tmpCanvas = new Canvas[2];
        tmpCanvas[Draw.PERMANENT_LAYER] = permanentCanvas;
        tmpCanvas[Draw.TEMPORARY_LAYER] = temporaryCanvas;
        draw = new Draw(tmpCanvas);

    }

    /**
     * draw some pixels without adding new log
     * this method is used by undo
     * @param difference
     */
    public void drawFreeWithoutLogging(PixelsDifference difference) {
        draw.drawFree(difference.getPixels(), Draw.PERMANENT_LAYER);
    }

    /**
     * draw some pixels based on PixelsDifference
     * @param difference
     */
    public void drawFree(PixelsDifference difference) {
        drawFreeWithoutLogging(difference);
        updateLog();
    }

    /**
     * draw some pixels based on a Pixel list
     * @param pixels
     */
    public void drawFree(ArrayList<Pixel> pixels) {
        draw.drawFree(pixels, Draw.PERMANENT_LAYER);
        updateLog();
    }

    /**
     * start drawing (press the LMB, Left Mouse Button)
     * @param start
     * @param color
     * @param lineStyle
     */
    public void startDrawFree(Coord start, Color color , int lineStyle) {
        status.drawFree(start);
        System.out.print("startDrawFree"+start+color+"|"+lineStyle+"\n");
    }

    /**
     * continue drawing ( moving the mouse while the LMB is pressed)
     * @param current
     * @param color
     * @param lineStyle
     */
    public void continueDrawFree(Coord current, Color color, int lineStyle) {
        if (status.status() != CanvasStatus.DRAW_FREE)
            return;

        draw.drawLine(status.start(), current, color, lineStyle, Draw.PERMANENT_LAYER);
        status.drawFree(current);
        System.out.print("continueDrawFree"+current+color+"|"+lineStyle+"\n");
    }

    /**
     * finish drawing (release the LMB)
     * @param end
     * @param color
     * @param lineStyle
     */
    public void stopDrawFree(Coord end, Color color, int lineStyle) {
        if (status.status() != CanvasStatus.DRAW_FREE)
            return;
        draw.drawLine(status.start(), end, color, lineStyle, Draw.PERMANENT_LAYER);
        status.nothing();
        System.out.print("stopDrawFree"+end+color+"|"+lineStyle+"\n");
        updateLog();
    }

    /**
     * start drawing (press the LMB, Left Mouse Button)
     * @param start
     * @param end
     * @param color
     * @param lineStyle
     */
    public void startDrawLine(Coord start, Coord end, Color color, int lineStyle) {
        status.drawLine(start, end);
        draw.drawLine(start, end, color, lineStyle, Draw.TEMPORARY_LAYER);
    }

    /**
     * continue drawing ( moving the mouse while the LMB is pressed)
     * @param start
     * @param end
     * @param color
     * @param lineStyle
     */
    public void continueDrawLine(Coord start, Coord end, Color color, int lineStyle) {
        status.drawLine(start, end);
        draw.clearTemporaryLayer();
        draw.drawLine(start, end, color, lineStyle, Draw.TEMPORARY_LAYER);
    }

    /**
     * finish drawing (release the LMB)
     * @param start
     * @param end
     * @param color
     * @param lineStyle
     */
    public void stopDrawLine(Coord start, Coord end, Color color, int lineStyle) {
        status.nothing();
        draw.clearTemporaryLayer();
        draw.drawLine(start, end, color, lineStyle, Draw.PERMANENT_LAYER);
        updateLog();
    }

    /**
     * start drawing (press the LMB, Left Mouse Button)
     * @param start
     * @param end
     * @param color
     * @param lineStyle
     * @param isFilled
     */
    public void startDrawRectangle(Coord start, Coord end, Color color, int lineStyle, boolean
            isFilled) {
        status.drawRectangle(start, end);
        draw.drawRectangle(start, end, color, lineStyle, isFilled, Draw.TEMPORARY_LAYER);
    }

    /**
     * continue drawing ( moving the mouse while the LMB is pressed)
     * @param start
     * @param end
     * @param color
     * @param lineStyle
     * @param isFilled
     */
    public void continueDrawRectangle(Coord start, Coord end, Color color, int lineStyle, boolean
            isFilled) {
        status.drawRectangle(start, end);
        draw.clearTemporaryLayer();
        draw.drawRectangle(start, end, color, lineStyle, isFilled, Draw.TEMPORARY_LAYER);
    }

    /**
     * finish drawing (release the LMB)
     * @param start
     * @param end
     * @param color
     * @param lineStyle
     * @param isFilled
     */
    public void stopDrawRectangle(Coord start, Coord end, Color color, int lineStyle, boolean
            isFilled) {
        status.nothing();
        draw.clearTemporaryLayer();
        draw.drawRectangle(start, end, color, lineStyle, isFilled, Draw.PERMANENT_LAYER);
        updateLog();
    }

    /**
     * start drawing (press the LMB, Left Mouse Button)
     * @param start
     * @param end
     * @param color
     * @param lineStyle
     * @param isFilled
     */
    public void startDrawOval(Coord start, Coord end, Color color, int lineStyle, boolean
            isFilled) {
        status.drawOval(start, end);
        draw.drawOval(start, end, color, lineStyle, isFilled, Draw.TEMPORARY_LAYER);
    }

    /**
     * continue drawing ( moving the mouse while the LMB is pressed)
     * @param start
     * @param end
     * @param color
     * @param lineStyle
     * @param isFilled
     */
    public void continueDrawOval(Coord start, Coord end, Color color, int lineStyle, boolean
            isFilled) {
        status.drawOval(start, end);
        draw.clearTemporaryLayer();
        draw.drawOval(start, end, color, lineStyle, isFilled, Draw.TEMPORARY_LAYER);
    }

    /**
     * finish drawing (release the LMB)
     * @param start
     * @param end
     * @param color
     * @param lineStyle
     * @param isFilled
     */
    public void stopDrawOval(Coord start, Coord end, Color color, int lineStyle, boolean
            isFilled) {
        status.nothing();
        draw.clearTemporaryLayer();
        draw.drawOval(start, end, color, lineStyle, isFilled, Draw.PERMANENT_LAYER);
        updateLog();
    }

    /**
     * start drawing (press the LMB, Left Mouse Button)
     * @param start
     * @param end
     * @param color
     * @param lineStyle
     * @param isFilled
     */
    public void startDrawCircle(Coord start, Coord end, Color color, int lineStyle, boolean
            isFilled) {
        status.drawCircle(start, end);
        draw.drawCircle(start, end, color, lineStyle, isFilled, Draw.TEMPORARY_LAYER);
    }

    /**
     * continue drawing ( moving the mouse while the LMB is pressed)
     * @param start
     * @param end
     * @param color
     * @param lineStyle
     * @param isFilled
     */
    public void continueDrawCircle(Coord start, Coord end, Color color, int lineStyle, boolean
            isFilled) {
        status.drawCircle(start, end);
        draw.clearTemporaryLayer();
        draw.drawCircle(start, end, color, lineStyle, isFilled, Draw.TEMPORARY_LAYER);
    }

    /**
     * finish drawing (release the LMB)
     * @param start
     * @param end
     * @param color
     * @param lineStyle
     * @param isFilled
     */
    public void stopDrawCircle(Coord start, Coord end, Color color, int lineStyle, boolean
            isFilled) {
        status.nothing();
        draw.clearTemporaryLayer();
        draw.drawCircle(start, end, color, lineStyle, isFilled, Draw.PERMANENT_LAYER);
        updateLog();
    }

    /**
     * start selecting area, draw a dotted rectangle in the TEMPORARY_LAYER
     * @param start
     * @param end
     */
    public void startSelectArea(Coord start, Coord end) {


    }

    /**
     * continue selecting area, draw a dotted rectangle in the TEMPORARY_LAYER
     * @param start
     * @param end
     */
    public void continueSelectArea(Coord start, Coord end) {

    }

    /**
     * finish selecting area, call the actual selcetArea method
     * @param start
     * @param end
     */
    public void stopSelectArea(Coord start, Coord end) {
        status.selectArea(start, end);
    }

    /**
     * unselect area
     */
    public void unselectArea() {
        if (status.status() != CanvasStatus.AREA_SELECTED)
            return;
    }

    /**
     * check if currentMouse is in the selected area, or close to, or far away from
     * return 1 (FAR_FROM_SELECTED_AREA) when there is no selected area
     * @param currentMouse
     * @return [-1, 0, 1], see CanvasStatus.INSIDE_SELECTED_AREA and so on
     */
    public int getLocationStatus(Coord currentMouse) {
        if (status.status() != CanvasStatus.AREA_SELECTED)
            return CanvasStatus.FAR_FROM_SELECTED_AREA;

        return getLocationBasedOnArea(currentMouse, status.start(), status.end());
    }

    private int getLocationBasedOnArea(Coord current, Coord start, Coord end) {
        Coord max, min;

        min = new Coord(Math.min(start.x(), end.x()),
                Math.min(start.y(), end.y()));

        max = new Coord(Math.max(start.x(), end.x()),
                Math.max(start.y(), end.y()));

        if (current.x() < max.x() && current.y() < max.y()
                && current.x() > min.x() && current.y() > min.y())
            return CanvasStatus.INSIDE_SELECTED_AREA;

        if (current.x() < max.x() + MAXIMUM_CLOSE_DISTANCE
                && current.y() < max.y() + MAXIMUM_CLOSE_DISTANCE
                && current.x() > min.x() - MAXIMUM_CLOSE_DISTANCE
                && current.y() > min.y() - MAXIMUM_CLOSE_DISTANCE)
            return CanvasStatus.NEAR_SELECTED_AREA;

        return CanvasStatus.FAR_FROM_SELECTED_AREA;
    }

    /**
     * draw text
     * @param start
     * @param content
     * @param font
     * @param size
     * @param color
     */
    public void drawText(Coord start, String content, String font, int size, Color color) {
        draw.drawText(start, content, font, size, color, Draw.PERMANENT_LAYER);
        updateLog();
    }

    // TBD
    public void moveArea() {

    }

    // TBD
    public void resizeArea() {

    }

    /**
     * undo
     */
    public void undo() {
        PixelsDifference lastOperation = log.popLastOperation();

        if (lastOperation != null && lastOperation.size() > 0)
            drawFreeWithoutLogging(lastOperation);
    }

    private void updateLog(){
        log.updateCanvas(CanvasHelper.canvasToMatrix(permanentCanvas));
    }

    public static void main(String[] args) {

        System.out.println("test");

    }
}
