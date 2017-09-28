package GUI.Tools;

import Data.Coord;
import GUI.DrawSettings.DrawSettings;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * Created by luanboheng on 28/09/2017.
 */

public class Shape extends Tool{


    protected Coord start,end;
    protected Color color;
    protected int lineStyle;
    protected boolean isFilled;

    Coord getCoord(MouseEvent e){
        return new Coord(e.getX(),e.getY());
    }

    void startDraw(){}
    void continueDraw(){}
    void endDraw(){}

    public void onMouseDown(MouseEvent e) {
        start = getCoord(e);
        end = getCoord(e);
        color = DrawSettings.getColor();
        lineStyle = (int) DrawSettings.getWidth();
        isFilled = (color != null);
        startDraw();
    }

//    Called when the left mouse is dragged.

    public void onMouseDrag(MouseEvent e) {
        end = getCoord(e);
        continueDraw();
    }

//    Called when the left mouse is released.

    public void onMouseReleased(MouseEvent e) {
        end = getCoord(e);
        endDraw();
    }


}
