package GUI.Layout;

import GUI.DrawSettings.DrawSettings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import java.util.Arrays;
import java.util.List;


public class TextPanel extends VBox{


    public TextPanel(){

        TextArea textField = new TextArea(DrawSettings.DEAFAULT_TEXT_CONTENT);
        textField.setPrefRowCount(2);
        textField.setPrefHeight(50);

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

        List<String> sizeValue = Arrays.asList("8","9","10","11","12","14","18","24","30","36","48","60","72","96");

        ObservableList<String> fontSize = FXCollections.observableList(sizeValue);
        ComboBox<String> fontSizeMenu= new ComboBox<String>(fontSize);

        fontSizeMenu.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String s, String s1) {
                DrawSettings.setFontSize(Integer.parseInt(s1));
            }});

        fontMenu.setValue(DrawSettings.getFont());
        fontSizeMenu.setValue(String.valueOf(DrawSettings.getFontSize()));

        fontMenu.setPrefWidth(135);
        fontSizeMenu.setPrefWidth(65);

        HBox fontSetting = new HBox();
        fontSetting.getChildren().add(fontMenu);
        fontSetting.getChildren().add(fontSizeMenu);
        getChildren().add(fontSetting);
        getChildren().add(textField);

    }

}
