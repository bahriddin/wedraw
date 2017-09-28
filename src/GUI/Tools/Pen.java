package GUI.Tools;

import GUI.Layout.CanvasArea;


public class Pen extends FreeDraw {
    public Pen(CanvasArea canvasArea) {
        super(canvasArea);
    }


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
