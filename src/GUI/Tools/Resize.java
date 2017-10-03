package GUI.Tools;

import GUI.Layout.CanvasArea;

/**
 * Created by luanboheng on 03/10/2017.
 */
public class Resize extends Selected{
    int TypeOfResize;

    @Override
    public String toString() {
        return "resize("+TypeOfResize+")";
    }

    public Resize(CanvasArea canvas, int TypeOfResize) {
        super(canvas);
        this.TypeOfResize = TypeOfResize;
    }

    @Override
    public void startDraw() {
        System.out.print("startDraw"+coord+TypeOfResize+"\n");
        this.model.startResizeArea(coord,TypeOfResize);
    }

    @Override
    public void continueDraw() {
        System.out.print("continueResizeArea"+coord+TypeOfResize+"\n");
        this.model.continueResizeArea(coord,TypeOfResize);
    }
    @Override
    public void endDraw() {
        System.out.print("endDraw"+coord+TypeOfResize+"\n");
        this.model.stopResizeArea(coord,TypeOfResize);
        this.getCanvas().tools.setTool(new Selected(getCanvas()));
    }
}
