package GUI.Tools;

import GUI.Layout.Canvas;
import javafx.event.EventHandler;
import javafx.scene.Cursor;

import javafx.scene.input.MouseEvent;


public class Tool implements EventHandler<MouseEvent> {

    private Canvas canvas;
    public Tool tool;
    private boolean leftClick;

    public Tool() {}

    public Tool(Canvas canvas) {
        this.canvas = canvas;
    }

    public Canvas getCanvas() {
        return canvas;
    }


//    Called when the left mouse is clicked down.

    public void onMouseDown(MouseEvent e) {}

//    Called when the left mouse is dragged.

    public void onMouseDrag(MouseEvent e) {}

//    Called when the left mouse is released.

    public void onMouseReleased(MouseEvent e) {}


    /*
     Set the current Tool
     */
    public void setTool(Tool tool) {
        // Setup the tool.
        this.tool = tool;
        tool.getCanvas().setCursor(tool.getCursor());
        tool.getCanvas().addEventHandler(MouseEvent.ANY, tool);
    }


    public Cursor getCursor() {
        return Cursor.DEFAULT;
    }

    @Override
    public void handle(MouseEvent e) {
        // If left click.
        if (e.isPrimaryButtonDown()) {
            leftClick = true;
            if (e.getEventType().equals(MouseEvent.MOUSE_DRAGGED))
                onMouseDrag(e);
            else if (e.getEventType().equals(MouseEvent.MOUSE_PRESSED))
                onMouseDown(e);
        }

        // If releasing and was left click.
        else if (leftClick) {
            if (e.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {
                onMouseReleased(e);
            }
        }
    }

}




