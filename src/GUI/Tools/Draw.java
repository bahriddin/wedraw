package GUI.Tools;

import Data.Coord;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

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
        GraphicsContext gc = layers[layerType];
        gc.setStroke(color);

        if (lineStyle > 0) {
            gc.setLineWidth(lineStyle);
        }
        else {
            gc.setLineWidth(-lineStyle);
            gc.setLineDashes(-2 * lineStyle);
        }

        gc.strokeLine(start.x(), start.y(), end.x(), end.y());
    }

    /**
     * Draw dashed line
     *
     * @param start Starting coordinate
     * @param end Ending coordinate
     * @param color Color RGBa
     * @param width width of the line
     * @param layerType Layer number (temp. or perm.)
     */
    private void drawDashed(Coord start, Coord end, Color color, int width, int layerType) {
        GraphicsContext gc = layers[layerType];
        gc.setStroke(color);
        gc.setLineWidth(width);
        gc.setLineDashes(2*width);
        gc.strokeLine(start.x(), start.y(), end.x(), end.y());
    }


}
