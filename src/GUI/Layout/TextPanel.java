package GUI.Layout;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class TextPanel extends VBox{


    public TextPanel(){

        TextField textField = new TextField("Hello");
        ObservableList<String> allFonts = FXCollections.observableList(Font.getFamilies());
        ComboBox<String> fontMenu= new ComboBox<String>(allFonts);
        fontMenu.setValue("Set Font");


        getChildren().add(textField);
        getChildren().add(fontMenu);
    }






}
