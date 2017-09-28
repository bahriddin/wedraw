package GUI.Tools;

import Data.Coord;
import GUI.Layout.CanvasArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import GUI.DrawSettings.DrawSettings;

import GUI.Layout.CanvasArea;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;


public class Pen extends FreeDraw {

//    public Line freeDraw;
//
//    public Pen(CanvasArea canvasArea) {
//        super();
//    }
//
//    protected void startLine(double x, double y) {
//
//        freeDraw = new Line();
//        freeDraw.setStartX(x);
//        freeDraw.setStartY(y);
//        freeDraw.setStroke(;
//        freeDraw.setStrokeWidth(DrawSettings.getWidth());
//        freeDraw.setStrokeLineCap(StrokeLineCap.ROUND);
//        freeDraw.setStrokeLineJoin(StrokeLineJoin.BEVEL);
//
//    }
//
//    protected void endLine(double x, double y) {
//        // End the line and add it to the Canvas.
//        freeDraw.setEndX(x);
//        freeDraw.setEndY(y);
//        getCanvas().addShape(freeDraw);
//    }

    @Override
    void startDraw() {
        this.model.startDrawFree(coord,color,lineStyle);
    }

    @Override
    void continueDraw() {
        this.model.continueDrawFree(coord,color,lineStyle);
    }

    @Override
    void endDraw() {
        this.model.stopDrawFree(coord,color,lineStyle);
    }
}
