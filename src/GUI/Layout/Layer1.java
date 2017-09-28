package GUI.Layout;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Canvas1 extends Canvas{

    GraphicsContext gc;

    Canvas1(double width, double height){

        this.gc=getGraphicsContext2D();
        gc.setFill(Color.BLUE);
        gc.fillRect(75,75,100,100);

    }





}