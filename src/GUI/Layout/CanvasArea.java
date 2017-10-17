package GUI.Layout;


import GUI.Tools.Tool;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import Model.CanvasInteraction;


public class CanvasArea extends Pane  {

    double locationX = 0.0;
    double locationY = 0.0;

    Canvas temporaryCanvas = new Canvas(640,1000);
    Canvas permanentCanvas = new Canvas(640,1000);
    Canvas networkCanvas = new Canvas(640,1000);

    public Tool tools;

    CanvasInteraction model = new CanvasInteraction(permanentCanvas, temporaryCanvas,networkCanvas);

//      Create a Canvas object.

    public CanvasInteraction getModel(){
        return model;
    }


    public CanvasArea(Tool tools) {

        this.tools= tools;


        getChildren().add(networkCanvas);
        getChildren().add(permanentCanvas);
        getChildren().add(temporaryCanvas);


//        //////////test
//        GraphicsContext gc1 = temporaryCanvas.getGraphicsContext2D();
//        GraphicsContext gc2 = permanentCanvas.getGraphicsContext2D();
//
//        gc1.setFill(Color.BLUE);
//        gc1.fillRect(90,90,100,100);
//        gc2.setFill(Color.RED);
//        gc2.fillRect(90,90,100,100);


        temporaryCanvas.toFront();

    }

//    Set the background color of this Canvas
//    void setBackground(Color color) {
//        setBackground(new Background(new BackgroundFill(
//                color, new CornerRadii(0), new Insets(0))));
//    }

//    Add a Shape to this Canvas.
    public void addShape(Shape s) {
        getChildren().add(s);
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