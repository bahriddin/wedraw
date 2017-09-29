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

    public void drawFree(ArrayList<Coord> coordList, Color color, int lineStyle) {



    }

    public void drawFree(PixelsDifference pixels) {

    }

    public void startDrawFree(Coord start, Color color , int lineStyle) {
        status.drawFree(start);
        System.out.print("startDrawFree"+start+color+"|"+lineStyle+"\n");
    }

    public void continueDrawFree(Coord current, Color color, int lineStyle) {
        if (status.status() != CanvasStatus.DRAW_FREE)
            return;

        draw.drawLine(status.stratCoord(), current, color, lineStyle, Draw.PERMANENT_LAYER);

        status.drawFree(current);

        System.out.print("continueDrawFree"+current+color+"|"+lineStyle+"\n");
    }

    public void stopDrawFree(Coord end, Color color, int lineStyle) {
        if (status.status() != CanvasStatus.DRAW_FREE)
            return;

        draw.drawLine(status.stratCoord(), end, color, lineStyle, Draw.PERMANENT_LAYER);

        status.nothing();

        log.updateCanvas(CanvasHelper.canvasToMatrix(permanentCanvas));

        System.out.print("stopDrawFree"+end+color+"|"+lineStyle+"\n");
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
        GraphicsContext gc = temporaryCanvas.getGraphicsContext2D();
        gc.rect(start.x(),start.y(),end.x()-start.x(),end.y()-start.y());
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
        System.out.print("drawText"+start+": '"+content+"' in font: "+font+ " | "+ color +"\n");

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

        // todo: based on PixelsDifference and drawFree instead of replacing the entire canvas

        int[][] lastCanvas = log.undo();

        if (lastCanvas != null)
            permanentCanvas = CanvasHelper.matrixToCanvas(lastCanvas);
    }

    public static void main(String[] args) {

        System.out.println("test");

    }
}
