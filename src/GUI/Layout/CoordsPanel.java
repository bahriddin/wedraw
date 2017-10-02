package GUI.Layout;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;


public class CoordsPanel extends HBox {

    public CoordsPanel(final CanvasArea canvas) {
        super();

        final Label locationLbl = new Label("Location: (X: 0, Y: 0)");
        locationLbl.setTextFill(Color.rgb(30, 60, 90));

        canvas.addEventHandler(MouseEvent.ANY, e -> {
                int x =  (int)e.getX();
                int y = (int) e.getY();
                locationLbl.setText("Location: (X: " + x + ", Y: " + y + ")");
            });

        getChildren().add(locationLbl);
    }

}
