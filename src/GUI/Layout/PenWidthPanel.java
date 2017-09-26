package GUI.Layout;

import GUI.DrawSettings.DrawSettings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;



public class PenWidthPanel extends VBox {

    Label widthLabel;
    Slider widthSlider;


    public PenWidthPanel() {
        // Create the GUI.
        setAlignment(Pos.CENTER);
        widthSlider = new Slider(0.1, 100.0, DrawSettings.getWidth());
        widthLabel = new Label("Width: " + (int) widthSlider.getValue());
        widthLabel.setTextFill(Color.rgb(30, 60, 90));


        widthSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number oldVal, Number newVal) {

                int val = newVal.intValue();
                widthLabel.setText("Width: " + val);
                DrawSettings.setWidth(val);
            }

        });

        // Add the Nodes.
        getChildren().add(widthLabel);
        getChildren().add(widthSlider);
    }

}