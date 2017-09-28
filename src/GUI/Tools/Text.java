package GUI.Tools;


import Data.Coord;
import GUI.DrawSettings.DrawSettings;
import GUI.Layout.CanvasArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Text extends Tool{

    protected Coord coord;
    protected String content;
    protected String font;
    protected Color color;
    public Text(CanvasArea canvasArea) {
        super(canvasArea);
    }

    Coord getCoord(MouseEvent e){
        return new Coord(e.getX(),e.getY());
    }

    void startDraw(){}

    public void onMouseDown(MouseEvent e) {
        coord = getCoord(e);
        color = DrawSettings.getColor();
        font = "dsa";
        startDraw();
    }

//    Called when the left mouse is dragged.



//    Called when the left mouse is released.


}
