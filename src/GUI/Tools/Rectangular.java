package GUI.Tools;

import Data.Coord;
import GUI.Layout.CanvasArea;

public class Rectangular extends Shape{
    public Rectangular(CanvasArea canvasArea) {
        super(canvasArea);
    }

    void startDraw(){
        this.model.startDrawRectangle(start,end,color,lineStyle,isFilled);
    }
    void continueDraw(){
        this.model.continueDrawRectangle(start,end,color,lineStyle,isFilled);
    }
    void endDraw(){
        this.model.stopDrawRectangle(start,end,color,lineStyle,isFilled);
    }
}
