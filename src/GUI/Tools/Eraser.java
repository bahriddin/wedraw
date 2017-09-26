package GUI.Tools;

import javafx.scene.paint.Color;


import GUI.Layout.Canvas;


public class Eraser extends Pen {


    public Eraser(Canvas canvas) {
        super(canvas);
    }

    @Override
    protected void startLine(double x, double y) {

        super.startLine(x, y);
        freeDraw.setStroke(Color.WHITE);

    }

    @Override
    protected void endLine(double x, double y) {
        freeDraw.setEndX(x);
        freeDraw.setEndY(y);
        freeDraw.setFill(Color.TRANSPARENT);
        getCanvas().addErase(freeDraw);
    }

}
