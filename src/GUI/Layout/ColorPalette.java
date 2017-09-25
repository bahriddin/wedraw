package Layout;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import DrawSettings.DrawSettings;


public class ColorPalette extends GridPane {

    private ColorPicker ColorPicker;



    public ColorPalette() {


        // Create  color picker.
        Label ColorLabel = new Label("Color");
        ColorLabel.setTextFill(Color.rgb(30, 60, 90));
        ColorPicker = new ColorPicker(Color.BLACK);
        ColorPicker.setPrefWidth(103.0);
        ColorPicker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                setColor(ColorPicker.getValue());
            }
        });

        // Add the components.
        add(ColorLabel, 1, 0);
        add(ColorPicker, 1, 1);

        // Center the labels.

        setHalignment(ColorLabel, HPos.CENTER);
    }


    public void setColor(Color color) {
        DrawSettings.setColor(color);
    }



}

