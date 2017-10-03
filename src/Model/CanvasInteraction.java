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
    private static final int MAXIMUM_CLOSE_DISTANCE = 10;

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
        //System.out.print("startDrawFree"+start+color+"|"+lineStyle+"\n");
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
        //System.out.print("continueDrawFree"+current+color+"|"+lineStyle+"\n");
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
        //System.out.print("stopDrawFree"+end+color+"|"+lineStyle+"\n");
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

        // if there is an area that has already been selected, do unselect first
        unselectArea();

        // temporarily draw selection border
        draw.drawSelection(start, end);
    }

    /**
     * continue selecting area, draw a dotted rectangle in the TEMPORARY_LAYER
     * @param start
     * @param end
     */
    public void continueSelectArea(Coord start, Coord end) {
        draw.clearTemporaryLayer();
        // temporarily draw selection border
        startSelectArea(start, end);
    }

    /**
     * finish selecting area, call the actual selcetArea method
     * @param start
     * @param end
     */
    public void stopSelectArea(Coord start, Coord end) {
        draw.clearTemporaryLayer();


        // actually Select the area
        draw.selectArea(start, end);
        status.selectArea(start, end);
    }

    /**
     * unselect area
     */
    public void unselectArea() {
        if (status.status() != CanvasStatus.AREA_SELECTED)
            return;

        draw.unselectArea(status.start(), status.end());
        updateLog();
        status.nothing();
    }

    /**
     * start moving area
     * @param current
     */
    public void startMoveArea(Coord current) {
        if (getLocationStatus(current) != CanvasStatus.INSIDE_SELECTED_AREA)
            return;

        status.moveArea(status.start(), status.end(), current);
    }

    /**
     * continue moving area
     * @param current
     */
    public void continueMoveArea(Coord current) {
        if (status.status() != CanvasStatus.AREA_SELECTED || status.last() == null)
            return;

        Coord diff = diffCoord(status.last(), current);

        Coord newStart = plusCoord(status.start(), diff),
             newEnd = plusCoord(status.end(), diff);

        draw.moveArea(status.start(), status.end(), newStart, newEnd);

        status.moveArea(newStart, newEnd, current);
    }

    /**
     * stop moving area
     * @param current
     */
    public void stopMoveArea(Coord current) {
        continueMoveArea(current);

        status.selectArea(status.start(), status.end());

        // do not undate log atm, only update when unselect
        // updateLog();
    }

    /**
     * start resizing area
     * @param current
     * @param resizeDirection see CanvasStatus.VERTICALLY_NEAR etc.
     */
    public void startResizeArea(Coord current, int resizeDirection) {
        if (status.status() != CanvasStatus.AREA_SELECTED)
            return;

        int resizeType = 0;

        Coord min = minCoord(status.start(), status.end()),
              max = maxCoord(status.start(), status.end());

        switch (resizeDirection) {

            case CanvasStatus.VERTICALLY_NEAR:
                if (current.y() <= min.y())
                    resizeType = CanvasStatus.FIXED_BOTTOM;
                else if (current.y() >= max.y())
                    resizeType = CanvasStatus.FIXED_TOP;
                else
                    return;
                break;

            case CanvasStatus.HORIZONTALLY_NEAR:
                if (current.x() <= min.x())
                    resizeType = CanvasStatus.FIXED_RIGHT;
                else if (current.x() >= max.x())
                    resizeType = CanvasStatus.FIXED_LEFT;
                else
                    return;
                break;

            case CanvasStatus.JUST_NEAR:
                if (current.x() <= min.x() && current.y() <= min.y())
                    resizeType = CanvasStatus.FIXED_BOTTOMRIGHT;
                else if (current.x() <= min.x() && current.y() >= max.y())
                    resizeType = CanvasStatus.FIXED_TOPRIGHT;
                else if (current.x() >= max.x() && current.y() <= min.y())
                    resizeType = CanvasStatus.FIXED_BOTTOMLEFT;
                else if (current.x() >= max.x() && current.y() >= max.y())
                    resizeType = CanvasStatus.FIXED_TOPLEFT;
                else
                    return;
                break;

            default:
                return;
        }

        status.resizeArea(min, max, resizeType);
    }

    /**
     * continue resizing area
     * @param current
     * @param resizeDirection Deprecated, only useful when start resizing
     */
    public void continueResizeArea(Coord current, int resizeDirection) {
        if (status.status() != CanvasStatus.AREA_SELECTED)
            return;

        Coord newStart, newEnd ;

        Coord min = minCoord(status.start(), status.end()),
                max = maxCoord(status.start(), status.end());

        switch (status.resizeType()) {

            case CanvasStatus.FIXED_TOP:
                newStart = new Coord(min.x(), min.y());
                newEnd = new Coord(max.x(), current.y());
                break;

            case CanvasStatus.FIXED_BOTTOM:
                newStart = new Coord(min.x(), current.y());
                newEnd = new Coord(max.x(), max.y());
                break;

            case CanvasStatus.FIXED_LEFT:
                newStart = new Coord(min.x(), min.y());
                newEnd = new Coord(current.x(), max.y());
                break;

            case CanvasStatus.FIXED_RIGHT:
                newStart = new Coord(current.x(), min.y());
                newEnd = new Coord(max.x(), max.y());
                break;

            case CanvasStatus.FIXED_TOPLEFT:
                newStart = new Coord(min.x(), min.y());
                newEnd = new Coord(current.x(), current.y());
                break;

            case CanvasStatus.FIXED_TOPRIGHT:
                newStart = new Coord(current.x(), min.y());
                newEnd = new Coord(max.x(), current.y());
                break;

            case CanvasStatus.FIXED_BOTTOMLEFT:
                newStart = new Coord(min.x(), current.y());
                newEnd = new Coord(current.x(), max.y());
                break;

            case CanvasStatus.FIXED_BOTTOMRIGHT:
                newStart = new Coord(current.x(), current.y());
                newEnd = new Coord(max.x(), max.y());
                break;

            default:
                return;
        }

        if (newStart.x() >= newEnd.x() || newStart.y() >= newEnd.y())
            return;

        draw.resizeArea(status.start(), status.end(), newStart, newEnd);

        status.resizeArea(newStart, newEnd, status.resizeType());
    }

    /**
     * stop resizing area
     * @param current
     * @param resizeDirection Deprecated, only useful when start Resizing
     */
    public void stopResizeArea(Coord current, int resizeDirection) {
        continueResizeArea(current, resizeDirection);

        status.selectArea(status.start(), status.end());

        // do not update log atm, only update when unselect()
    }

    /**
     * check if currentMouse is in the selected area, or close to, or far away from
     * return (CanvasStatus.FAR_FROM_SELECTED_AREA) when there is no selected area
     * @param currentMouse
     * @return see CanvasStatus.INSIDE_SELECTED_AREA and so on
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

        if (current.x() < max.x() && current.x() > min.x()
                &&  current.y() < max.y() && current.y() > min.y())
            return CanvasStatus.INSIDE_SELECTED_AREA;

        if (current.x() < max.x() && current.x() > min.x()
                && current.y() < max.y() + MAXIMUM_CLOSE_DISTANCE
                && current.y() > min.y() - MAXIMUM_CLOSE_DISTANCE)
            return CanvasStatus.VERTICALLY_NEAR;

        if (current.y() < max.y() && current.y() > min.y()
                && current.x() < max.x() + MAXIMUM_CLOSE_DISTANCE
                && current.x() > min.x() - MAXIMUM_CLOSE_DISTANCE)
            return CanvasStatus.HORIZONTALLY_NEAR;

        if (current.x() < max.x() + MAXIMUM_CLOSE_DISTANCE
                && current.y() < max.y() + MAXIMUM_CLOSE_DISTANCE
                && current.x() > min.x() - MAXIMUM_CLOSE_DISTANCE
                && current.y() > min.y() - MAXIMUM_CLOSE_DISTANCE)
            return CanvasStatus.JUST_NEAR;

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

    /**
     * undo
     */
    public void undo() {
        // if there is an area selected, unselct first
        if (status.status() == CanvasStatus.AREA_SELECTED)
            unselectArea();

        PixelsDifference lastOperation = log.popLastOperation();

        if (lastOperation != null && lastOperation.size() > 0)
            drawFreeWithoutLogging(lastOperation);
    }

    private void updateLog(){
        log.updateCanvas(CanvasHelper.canvasToMatrix(permanentCanvas));
    }

    public Coord diffCoord(Coord last, Coord current) {
        return new Coord(current.x() - last.x(), current.y() - last.y());
    }

    public Coord plusCoord(Coord old, Coord diff) {
        return new Coord(old.x() + diff.x(), old.y() + diff.y());
    }

    private Coord minCoord(Coord start, Coord end) {
        return new Coord(Math.min(start.x(), end.x()),
                Math.min(start.y(), end.y()));
    }

    private Coord maxCoord(Coord start, Coord end) {
        return new Coord(Math.max(start.x(), end.x()),
                Math.max(start.y(), end.y()));
    }

}
