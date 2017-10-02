package GUI.Tools;

import Data.Coord;
import GUI.DrawSettings.DrawSettings;
import GUI.Layout.CanvasArea;
import javafx.scene.input.MouseEvent;

public class Select extends Shape{

    public Select(CanvasArea canvasArea) {
        super(canvasArea);
    }

    @Override
    public CanvasArea getCanvas() {
        return super.getCanvas();
    }

    void startDraw(){
        this.model.startSelectArea(start,end);
    }

    void continueDraw(){
        try {
            this.model.continueSelectArea(start, end);
        }catch (Exception e1){
            start = new Coord(end.x(),end.y());
            System.out.print(start);
            System.out.println(end);
            this.model.startSelectArea(start,end);
            this.model.continueSelectArea(start, end);
        }
    }


    void endDraw() {
        this.model.stopSelectArea(start, end);
        this.getCanvas().tools.setTool(new Move(getCanvas()));
    }
}








