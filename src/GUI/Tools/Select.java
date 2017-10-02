package GUI.Tools;

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
        this.model.continueSelectArea(start,end);
    }
    void endDraw() {
        this.model.stopSelectArea(start, end);
        setTool(new Move(getCanvas()));

}
}








