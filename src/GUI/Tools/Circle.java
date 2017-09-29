package GUI.Tools;

import GUI.Layout.CanvasArea;

/**
 * Created by luanboheng on 29/09/2017.
 */
public class Circle extends Shape{
    public Circle(CanvasArea canvasArea) {
        super(canvasArea);
    }

    void startDraw(){
        this.model.startDrawCircle(start,end,color,lineStyle,isFilled);
    }
    void continueDraw(){
        this.model.continueDrawCircle(start,end,color,lineStyle,isFilled);
    }
    void endDraw(){
        this.model.stopDrawCircle(start,end,color,lineStyle,isFilled);
    }
}