package GUI.Tools;

import GUI.Layout.CanvasArea;
import javafx.scene.paint.Color;


public class Eraser extends FreeDraw {
    public Eraser(CanvasArea canvasArea) {
        super(canvasArea);
    }


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
