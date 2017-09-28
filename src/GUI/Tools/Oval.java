package GUI.Tools;

import GUI.Layout.CanvasArea;

public class Oval extends Shape{
    public Oval(CanvasArea canvasArea) {
        super(canvasArea);
    }

    void startDraw(){
        this.model.startDrawOval(start,end,color,lineStyle,isFilled);
    }
    void continueDraw(){
        this.model.continueDrawOval(start,end,color,lineStyle,isFilled);
    }
    void endDraw(){
        this.model.stopDrawOval(start,end,color,lineStyle,isFilled);
    }
}
