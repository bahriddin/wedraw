package GUI.Tools;

import GUI.Layout.CanvasArea;
import javafx.scene.paint.Color;


public class Eraser extends FreeDraw {
    public Eraser(CanvasArea canvasArea) {
        super(canvasArea);
    }


    @Override
    void startDraw() {
        this.model.startDrawFree(coord,Color.rgb(255,255,254),lineStyle);
    }

    @Override
    void continueDraw() {
        this.model.continueDrawFree(coord,Color.rgb(255,255,254),lineStyle);
    }

    @Override
    void endDraw() {
        this.model.stopDrawFree(coord,Color.rgb(255,255,254),lineStyle);
    }

}
