package Layout;

import java.util.LinkedList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;


public class Canvas extends Pane  {

    private List<Shape> erase;
    double locationX = 0.0;
    double locationY = 0.0;

//      Create a Canvas object.

    public Canvas() {
        erase = new LinkedList<Shape>();
    }

//    Set the background color of this Canvas
    void setBackground(Color color) {
        setBackground(new Background(new BackgroundFill(
                color, new CornerRadii(0), new Insets(0))));
    }

//    Add a Shape to this Canvas.
    public void addShape(Shape s) {
        getChildren().add(s);
    }

//    add  eraser  to this Canvas.
    public void addErase(Shape s) {
        getChildren().add(s);
        erase.add(s);
    }


    /**
     * Get the X distance this Canvas is from the origin
     */
    public double getLocationX() {
        return locationX;
    }

    /**
     * Get the Y distance this Canvas is from the origin
     */
    public double getLocationY() {
        return locationY;
    }


    public void setLocation(double x, double y) {
        locationX = x;
        locationY = y;
    }


}