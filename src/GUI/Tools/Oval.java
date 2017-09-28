package GUI.Tools;

import Data.Coord;
import GUI.DrawSettings.DrawSettings;
import GUI.Layout.CanvasArea;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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
