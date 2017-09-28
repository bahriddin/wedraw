package GUI.Tools;

import GUI.Layout.CanvasArea;

/**
 * Created by luanboheng on 29/09/2017.
 */
public class Line extends Shape{
    public Line(CanvasArea canvasArea) {
        super(canvasArea);
    }

    void startDraw(){
        this.model.startDrawLine(start,end,color,lineStyle);
    }
    void continueDraw(){
        this.model.continueDrawLine(start,end,color,lineStyle);
    }
    void endDraw(){
        this.model.stopDrawLine(start,end,color,lineStyle);
    }
}
