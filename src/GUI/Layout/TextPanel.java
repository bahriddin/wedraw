package GUI.Layout;

import GUI.DrawSettings.DrawSettings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class TextPanel extends VBox{


    public TextPanel(){

        TextField textField = new TextField();
        textField.textProperty().addListener((obs, oldText, newText) -> {
            DrawSettings.setContent(newText);
        });
        ObservableList<String> allFonts = FXCollections.observableList(Font.getFamilies());
        ComboBox<String> fontMenu= new ComboBox<String>(allFonts);
        fontMenu.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String s, String s1) {
                DrawSettings.setFont(s1);
        }});

        fontMenu.setPromptText("Set Font");


        getChildren().add(textField);
        getChildren().add(fontMenu);
    }






}
