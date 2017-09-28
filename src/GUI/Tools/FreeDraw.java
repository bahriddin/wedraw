package GUI.Tools;
import Data.Coord;
import GUI.DrawSettings.DrawSettings;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * Created by luanboheng on 28/09/2017.
 */
public class FreeDraw extends Tool{
    protected Coord coord;
    protected Color color;
    protected int lineStyle;

    Coord getCoord(MouseEvent e){
        return new Coord(e.getX(),e.getY());
    }

    void startDraw(){}
    void continueDraw(){}
    void endDraw(){}

    public void onMouseDown(MouseEvent e) {
        coord = getCoord(e);
        color = DrawSettings.getColor();
        lineStyle = (int) DrawSettings.getWidth();
        startDraw();
    }

//    Called when the left mouse is dragged.

    public void onMouseDrag(MouseEvent e) {
        coord = getCoord(e);
        continueDraw();
    }

//    Called when the left mouse is released.

    public void onMouseReleased(MouseEvent e) {
        coord = getCoord(e);
        endDraw();
    }

}
