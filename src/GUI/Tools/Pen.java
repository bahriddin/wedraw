package GUI.Tools;

import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;

import GUI.DrawSettings.DrawSettings;

import GUI.Layout.Canvas;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;


public class Pen extends Tool {

    public Line freeDraw;

    public Pen(Canvas canvas) {
        super(canvas);
    }

    protected void startLine(double x, double y) {

        freeDraw = new Line();
        freeDraw.setStartX(x);
        freeDraw.setStartY(y);
        freeDraw.setStroke(DrawSettings.getColor());
        freeDraw.setStrokeWidth(DrawSettings.getWidth());
        freeDraw.setStrokeLineCap(StrokeLineCap.ROUND);
        freeDraw.setStrokeLineJoin(StrokeLineJoin.BEVEL);

    }

    protected void endLine(double x, double y) {
        // End the line and add it to the Canvas.
        freeDraw.setEndX(x);
        freeDraw.setEndY(y);
        getCanvas().addShape(freeDraw);
    }

    @Override
    public void onMouseDown(MouseEvent e) {
        super.onMouseDown(e);
        // start of  line.
        startLine(e.getX(), e.getY());
    }

    @Override
    public void onMouseDrag(MouseEvent e) {
        super.onMouseDrag(e);
        // End the line and start a new one.
        endLine(e.getX(), e.getY());
        startLine(e.getX(), e.getY());
    }

    @Override
    public void onMouseReleased(MouseEvent e) {
        // End the line
        endLine(e.getX(), e.getY());
    }




}
