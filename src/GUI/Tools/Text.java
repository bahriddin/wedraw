package GUI.Tools;
/**
 * Created by Maoyuan Xue on 29/09/2017.
 */
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
    protected int fontSize;

    public Text(CanvasArea canvasArea) {
        super(canvasArea);
    }

    Coord getCoord(MouseEvent e){
        return new Coord(e.getX(),e.getY());
    }

    //todo: add size
    void startDraw(){this.model.drawText(coord,content,font,fontSize,color);}

    public void onMouseDown(MouseEvent e) {
        coord = getCoord(e);
        color = DrawSettings.getColor();
        font = DrawSettings.getFont();
        content = DrawSettings.getContent();
        fontSize = DrawSettings.getFontSize();
        startDraw();
    }

}
