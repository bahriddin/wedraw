package GUI.Tools;

import Data.Coord;
import GUI.DrawSettings.DrawSettings;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


import GUI.Layout.CanvasArea;


public class Eraser extends FreeDraw {
    public Eraser(CanvasArea canvasArea) {
        super(canvasArea);
    }


//    public Eraser(CanvasArea canvas) {
//        super(canvas);
//    }
//
//    @Override
//    protected void startLine(double x, double y) {
//
//        super.startLine(x, y);
//        freeDraw.setStroke(Color.WHITE);
//
//    }
//
//    @Override
//    protected void endLine(double x, double y) {
//        freeDraw.setEndX(x);
//        freeDraw.setEndY(y);
//        getCanvas().addShape(freeDraw);
//    }

    @Override
    void startDraw() {
        this.model.startDrawFree(coord,Color.WHITE,lineStyle);
    }

    @Override
    void continueDraw() {
        this.model.continueDrawFree(coord,Color.WHITE,lineStyle);
    }

    @Override
    void endDraw() {
        this.model.stopDrawFree(coord,Color.WHITE,lineStyle);
    }

}
