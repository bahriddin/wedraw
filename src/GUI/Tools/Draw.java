package GUI.Tools;

import Data.Coord;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class Draw {
    public final static int PERMANENT_LAYER = 0;
    public final static int TEMPORARY_LAYER = 1;

    private GraphicsContext[] layers;

    /**
     * Constructing layers
     *
     * @param canvases 2 canvas should be provided
     */
    public Draw(Canvas[] canvases) {
        layers = new GraphicsContext[canvases.length];

        for (int i = 0; i < canvases.length; i++) {
            layers[i] = canvases[i].getGraphicsContext2D();
        }
    }

    private GraphicsContext initGC(int layerType, Color color) {
        GraphicsContext gc = layers[layerType];
        gc.setStroke(color);
        gc.setFill(color);

        return gc;
    }

    private GraphicsContext initLineStyle(GraphicsContext gc, int lineStyle) {
        if (lineStyle > 0) {
            gc.setLineWidth(lineStyle);
        }
        else {
            gc.setLineWidth(-lineStyle);
            gc.setLineDashes(-2 * lineStyle);
        }
        gc.setLineCap(StrokeLineCap.ROUND);

        return gc;
    }

    private int[] initFigureData(Coord start, Coord end) {
        int[] res = new int[4];
        res[0] = Math.min(start.x(), end.x());
        res[1] = Math.min(start.y(), end.y());
        res[2] = Math.max(start.x(), end.x()) - res[0];
        res[3] = Math.max(start.y(), end.y()) - res[1];

        return res;
    }


    /**
     * Negative lineStyle represents a dotted line, positive lifestyle represents
     * a solid line. The absolute value of lineStyle is the width of the line.
     *
     * @param start Starting coordinate
     * @param end Ending coordinate
     * @param color Color RGBa
     * @param lineStyle Width + dotted if negative
     * @param layerType Layer number (temp. or perm.)
     */
    public void drawLine(Coord start, Coord end, Color color, int lineStyle, int layerType) {
        GraphicsContext gc = initGC(layerType, color);
        initLineStyle(gc, lineStyle);

        gc.strokeLine(start.x(), start.y(), end.x(), end.y());
    }

    /**
     * Draws rectangle
     *
     * @param start Starting coordinate
     * @param end Ending coordinate
     * @param color Color RGBa
     * @param lineStyle Width + dotted if negative
     * @param isFilled should be filled figure with color
     * @param layerType Layer number (temp. or perm.)
     */
    public void drawRectangle(Coord start, Coord end, Color color, int lineStyle, boolean isFilled, int layerType) {
        GraphicsContext gc = initGC(layerType, color);
        initLineStyle(gc, lineStyle);
        int[] xywh = initFigureData(start, end);

        if (isFilled)
            gc.fillRect(xywh[0], xywh[1], xywh[2], xywh[3]);

        gc.strokeRect(xywh[0], xywh[1], xywh[2], xywh[3]);
    }

    public void drawOval(Coord start, Coord end, Color color, int lineStyle, boolean isFilled, int layerType) {
        GraphicsContext gc = initGC(layerType, color);
        initLineStyle(gc, lineStyle);
        int[] xywh = initFigureData(start, end);

        if (isFilled)
            gc.fillOval(xywh[0], xywh[1], xywh[2], xywh[3]);

        gc.strokeOval(xywh[0], xywh[1], xywh[2], xywh[3]);
    }

    public void drawCircle(Coord start, Coord end, Color color, int lineStyle, boolean isFilled, int layerType) {
        GraphicsContext gc = initGC(layerType, color);
        initLineStyle(gc, lineStyle);
        int[] xywh = initFigureData(start, end);

        xywh[2] = Math.max(xywh[2], xywh[3]);
        xywh[3] = xywh[2];

        if (isFilled)
            gc.fillOval(xywh[0], xywh[1], xywh[2], xywh[3]);

        gc.strokeOval(xywh[0], xywh[1], xywh[2], xywh[3]);
    }

    public void clearTemporaryLayer() {
        GraphicsContext gc = layers[TEMPORARY_LAYER];
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
    }

    public void drawFree(ArrayList<Coord> coordList, Color color, int layerType) {
        PixelWriter pixelWriter = layers[layerType].getPixelWriter();
        for (Coord coord: coordList) {
            pixelWriter.setColor(coord.x(), coord.y(), color);
        }
    }

    public void drawText(Coord start, String content, String font, int size, Color color, int layerType) {
        GraphicsContext gc = initGC(layerType, color);
        gc.setFont(new Font(font, size));
        gc.setFill(color);
        gc.fillText(content, start.x(), start.y());
    }
}
