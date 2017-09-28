package GUI.Tools;

import Data.Coord;
import GUI.DrawSettings.DrawSettings;
import GUI.Layout.CanvasArea;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Rectangular extends Shape{
    public Rectangular(CanvasArea canvasArea) {
        super(canvasArea);
    }

//    private double x1, y1, x2, y2;
//    Rectangle rect;
//
//    public Rectangular(CanvasArea anvasArea) {
//        super(anvasArea);
//    }
//
//    public void onMouseDown(MouseEvent e) {
//        super.onMouseDown(e);
//        x1 = e.getX();
//        y1 = e.getY();
//    }
//
//    @Override
//    public void onMouseDrag(MouseEvent e) {
//        super.onMouseDrag(e);
////        rect = new Rectangle(x1, y1, e.getX() - x1, e.getY() - y1);
//    }
//
//
//    @Override
//    public void onMouseReleased(MouseEvent e){
//        super.onMouseReleased(e);
//
//        rect = new Rectangle(x1, y1, e.getX() - x1, e.getY() - y1);
//        getCanvas().addShape(rect);
//    }
    //    Called when the left mouse is clicked down.

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
