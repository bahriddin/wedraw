package GUI.Tools;

import Data.CanvasHelper;
import Data.Coord;
import Data.Pixel;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class Draw {
    public final static int PERMANENT_LAYER = 0;
    public final static int TEMPORARY_LAYER = 1;
    public final static int NETWORK_LAYER = 2;

    private Image image;
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

    /**
     * Helper method to get GraphicsContext object with colors set
     *
     * @param layerType Layer number (temp. or perm.)
     * @param color     Color RGBa
     */
    private GraphicsContext initGC(int layerType, Color color) {
        GraphicsContext gc = layers[layerType];
        gc.setStroke(color);
        gc.setFill(color);

        return gc;
    }

    /**
     * Helper method to initialize line style (dashed if negative lineStyle provided)
     *
     * @param gc        GraphicsContext object
     * @param lineStyle Width + dotted if negative
     */
    private GraphicsContext initLineStyle(GraphicsContext gc, int lineStyle) {
        if (lineStyle > 0) {
            gc.setLineWidth(lineStyle);
        } else {
            gc.setLineWidth(-lineStyle);
            gc.setLineDashes(-2 * lineStyle);
        }
        gc.setLineCap(StrokeLineCap.ROUND);
        return gc;
    }

    /**
     * Helper method to initialize correct coordinates and width, height
     *
     * @param start     Starting coordinate
     * @param end       Ending coordinate
     */
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
     * @param start     Starting coordinate
     * @param end       Ending coordinate
     * @param color     Color RGBa
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
     * @param start     Starting coordinate
     * @param end       Ending coordinate
     * @param color     Color RGBa
     * @param lineStyle Width + dotted if negative
     * @param isFilled  should be filled figure with color
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

    /**
     * Draw oval
     *
     * @param start     Starting coordinate
     * @param end       Ending coordinate
     * @param color     Color RGBa
     * @param lineStyle Width + dotted if negative
     * @param isFilled  should be filled figure with color
     * @param layerType Layer number (temp. or perm.)
     */
    public void drawOval(Coord start, Coord end, Color color, int lineStyle, boolean isFilled, int layerType) {
        GraphicsContext gc = initGC(layerType, color);
        initLineStyle(gc, lineStyle);
        int[] xywh = initFigureData(start, end);

        if (isFilled)
            gc.fillOval(xywh[0], xywh[1], xywh[2], xywh[3]);

        gc.strokeOval(xywh[0], xywh[1], xywh[2], xywh[3]);
    }

    /**
     * Draw Circle (oval with equal width and height)
     *
     * @param start     Starting coordinate
     * @param end       Ending coordinate
     * @param color     Color RGBa
     * @param lineStyle Width + dotted if negative
     * @param isFilled  should be filled figure with color
     * @param layerType Layer number (temp. or perm.)
     */
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

    /**
     * Clear Temporary layer
     */
    public void clearTemporaryLayer() {
        GraphicsContext gc = layers[TEMPORARY_LAYER];
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
    }

    public void clearPermanentLayer() {
        GraphicsContext gc = layers[PERMANENT_LAYER];
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
    }

    public void clearNetworkLayer() {
        GraphicsContext gc = layers[NETWORK_LAYER];
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
    }

    /**
     * Free drawing tool
     *
     * @param pixels    Arraylist of Pixel objects
     * @param layerType Layer number (temp. or perm.)
     */
    public void drawFree(ArrayList<Pixel> pixels, int layerType) {
        PixelWriter pixelWriter = layers[layerType].getPixelWriter();
        for (Pixel pixel: pixels) {
            pixelWriter.setColor(pixel.x(), pixel.y(), CanvasHelper.intToColor(pixel.color()));
        }
    }

    /**
     * Draw Text on the canvas
     *
     * @param start     Starting coordinate
     * @param content   Text content
     * @param font      Font name
     * @param size      Font size
     * @param color     Color RGBa
     * @param layerType Layer number (temp. or perm.)
     */
    public void drawText(Coord start, String content, String font, int size, Color color, int layerType) {
        GraphicsContext gc = initGC(layerType, color);
        gc.setFont(new Font(font, size));
        gc.setFill(color);
        gc.fillText(content, start.x(), start.y());
    }

    /**
     * Select area method
     *
     * @param start Starting coordinate
     * @param end   Ending coordinate
     */
    public void selectArea(Coord start, Coord end) {
        GraphicsContext gc = layers[TEMPORARY_LAYER];
        int[] xywh = initFigureData(start, end);

        // copy image from the network layer first
        updateImage(NETWORK_LAYER);

        gc.drawImage(image, xywh[0], xywh[1], xywh[2], xywh[3],
                xywh[0], xywh[1], xywh[2], xywh[3]);

        // then copy image from the permanent layer
        updateImage(PERMANENT_LAYER);

        gc.drawImage(image, xywh[0], xywh[1], xywh[2], xywh[3],
                xywh[0], xywh[1], xywh[2], xywh[3]);

        // no longer clear the selected area in the method
        // Clear selected area from permanent layer
        //layers[PERMANENT_LAYER].clearRect(xywh[0] + 1, xywh[1] + 1, xywh[2] - 1, xywh[3] - 1);

        // Draw selection border
        drawSelection(xywh[0], xywh[1], xywh[2], xywh[3]);
    }

    /**
     * Update image property of the object using given layer
     *
     * @param layerType Layer number (temp. or perm.)
     */
    private void updateImage(int layerType) {
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        image = layers[layerType].getCanvas().snapshot(params, null);
    }

    /**
     * called when selecting area (when the mouse is pressed)
     * @param start
     * @param end
     */
    public void drawSelection(Coord start, Coord end) {
        int[] xywh = initFigureData(start, end);

        // Draw selection border
        drawSelection(xywh[0], xywh[1], xywh[2], xywh[3]);
    }

    /**
     * Draw selection border (dashed rectangle with 4 dots on the corners)
     *
     * @param x initial x coordinate
     * @param y initial y coordinate
     * @param w width
     * @param h height
     */
    private void drawSelection(int x, int y, int w, int h) {

        Color color = Color.DARKGRAY;
        int dotWidth = 4;
        drawRectangle(new Coord(x, y), new Coord(x + w, y + h), color, -2, false, TEMPORARY_LAYER);

        GraphicsContext gc = layers[TEMPORARY_LAYER];
        gc.setFill(color);
        gc.fillRect(x - dotWidth, y - dotWidth, dotWidth, dotWidth);
        gc.fillRect(x - dotWidth, y + h, dotWidth, dotWidth);
        gc.fillRect(x + w, y - dotWidth, dotWidth, dotWidth);
        gc.fillRect(x + w, y + h, dotWidth, dotWidth);

    }

    /**
     * Move Selection area
     *
     * @param oldStart old starting coordinate
     * @param oldEnd   old ending coordinate
     * @param newStart new starting coordinate
     * @param newEnd   new ending coordinate
     */
    public void moveArea(Coord oldStart, Coord oldEnd, Coord newStart, Coord newEnd) {
        changeArea(oldStart, oldEnd, newStart, newEnd);
    }

    /**
     * Resize Selection area
     *
     * @param oldStart old starting coordinate
     * @param oldEnd   old ending coordinate
     * @param newStart new starting coordinate
     * @param newEnd   new ending coordinate
     */
    public void resizeArea(Coord oldStart, Coord oldEnd, Coord newStart, Coord newEnd) {
        changeArea(oldStart, oldEnd, newStart, newEnd);
    }

    /**
     * Change selection area (move, resize or both)
     *
     * @param oldStart old starting coordinate
     * @param oldEnd   old ending coordinate
     * @param newStart new starting coordinate
     * @param newEnd   new ending coordinate
     */
    private void changeArea(Coord oldStart, Coord oldEnd, Coord newStart, Coord newEnd) {
        GraphicsContext gc = layers[TEMPORARY_LAYER];
        int[] oldXYWH = initFigureData(oldStart, oldEnd);
        int[] newXYWH = initFigureData(newStart, newEnd);

        // Draw new looking in the temporary layer
        clearTemporaryLayer();
        gc.drawImage(image, oldXYWH[0], oldXYWH[1], oldXYWH[2], oldXYWH[3],
                newXYWH[0], newXYWH[1], newXYWH[2], newXYWH[3]);
        // get temporary layer image
        updateImage(TEMPORARY_LAYER);

        // Draw selection border
        drawSelection(newXYWH[0], newXYWH[1], newXYWH[2], newXYWH[3]);
    }

    /**
     * Unselect Area
     *
     * @param start     Starting coordinate
     * @param end       Ending coordinate
     */
    public void unselectArea(Coord start, Coord end) {
        GraphicsContext gc = layers[PERMANENT_LAYER];
        int[] xywh = initFigureData(start, end);

        clearTemporaryLayer();

        // Draw image to the permanent layer
        gc.drawImage(image, xywh[0], xywh[1], xywh[2], xywh[3],
                xywh[0], xywh[1], xywh[2], xywh[3]);
    }
}
