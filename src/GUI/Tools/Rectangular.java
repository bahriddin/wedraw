package GUI.Tools;

import GUI.Layout.CanvasArea;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Rectangular extends Tool{

    private double x1, y1, x2, y2;
    Rectangle rect;

    public Rectangular(CanvasArea anvasArea) {
        super(anvasArea);
    }

    public void onMouseDown(MouseEvent e) {
        super.onMouseDown(e);
        x1 = e.getX();
        y1 = e.getY();
    }

    @Override
    public void onMouseDrag(MouseEvent e) {
        super.onMouseDrag(e);
//        rect = new Rectangle(x1, y1, e.getX() - x1, e.getY() - y1);
    }


    @Override
    public void onMouseReleased(MouseEvent e){
        super.onMouseReleased(e);

        rect = new Rectangle(x1, y1, e.getX() - x1, e.getY() - y1);
        getCanvas().addShape(rect);
    }


}
