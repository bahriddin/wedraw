package GUI.Layout;

import GUI.Tools.Tool;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.Optional;

import static GUI.DrawSettings.DrawSettings.color;
import static GUI.Layout.ToolsPanel.undoBtn;
import static GUI.run.TimerThread.admModel;

public class Client extends WhiteBoard{

    VBox V_list = new VBox();

    HBox H_list = new HBox();

    VBox V_Print = new VBox();

    public static TextArea chatBox = new TextArea();


    String local_Host;


    public String user_name = "UserName";


    public Client(String user_name,String canvas_id) {

        chatBox.setWrapText(true);


        Label label2 = new Label("       Canvas name: "+canvas_id);
        label2.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        Label label3 = new Label("       Your Name: " +user_name);
        label3.setFont(Font.font("Verdana", FontWeight.BOLD, 15));


        V_list.getChildren().add(H_list);
        V_Print.getChildren().add(label3);
        V_Print.getChildren().add(label2);
        V_Print.setAlignment(Pos.CENTER);


        H_list.setAlignment(Pos.CENTER);


        topControls.getChildren().add(V_Print);


        ToolsPanel.hbox2.getChildren().remove(undoBtn);

        WhiteBoardMenu.file.getItems().remove(WhiteBoardMenu.load);
        WhiteBoardMenu.file.getItems().remove(WhiteBoardMenu.newCanvas);




//        getChildren().add(left_right);

        Label label1 = new Label("Current Users: ");
        V_list.getChildren().add(label1);

        V_list.setPadding(new Insets(5));
        Pane spacePane = new Pane();

        V_list.setVgrow(spacePane, Priority.ALWAYS);

        V_list.getChildren().add(spacePane);



        Label chat_label = new Label("Chat Box");


        V_list.getChildren().add(chat_label);


        chatBox.setPrefSize(300,400);
        chatBox.setEditable(false);

        TextArea inputTxt = new TextArea();
        inputTxt.setPrefSize(230,50);

        Button send_btn = new Button("Send");
        send_btn.setPrefSize(70,70);

        send_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                admModel.Send_CHAT_MESSAGE(inputTxt.getText());
//                chatBox.appendText(inputTxt.getText());
                inputTxt.setText(null);
            }
        });



        V_list.getChildren().add(chatBox);


        HBox chat_input = new HBox();

        chat_input.getChildren().add(inputTxt);
        chat_input.getChildren().add(send_btn);

        V_list.getChildren().add(chat_input);

        V_list.setMargin(chat_input,new Insets(15,0,0,0) );


        setRight(V_list);

        chatBox.setText("Hello! "+user_name+". Welcome to Canvas "+canvas_id);

    }

    public void kicked(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
//        alert.setTitle("Warning Dialog");
//        alert.setHeaderText("Look, a Warning Dialog");
        alert.setContentText("Sorry, you've been kicked out by the manager");
        Optional<ButtonType> result = alert.showAndWait();
        ButtonType button = result.orElse(ButtonType.CANCEL);
        if (button == ButtonType.OK) {
            System.out.println("bye bye");
            Platform.exit();
            System.exit(0);
        } else {
            Platform.exit();
            System.exit(0);

        }
    }






}

//    ListView<String> list = new ListView<String>();
//    ObservableList<String> items = FXCollections.observableArrayList(
//            "Manager");
//        list.setItems(items);
//
//                list.setStyle("-fx-background-color: yellow");
//
//
//                HBox hBox = new HBox();
//                hBox.setSpacing(5);
//
//                list.setPrefWidth(100);
//                list.setPrefHeight(70);
//
//                Text name = new Text("Manager");
//                name.setFont(new Font("Arial", 20));
//                ImageView statusImageView = new ImageView();
//                Image statusImage = new Image("images/online.png", 16, 16, true, true);
//                statusImageView.setImage(statusImage);
//
//                hBox.getChildren().addAll(statusImageView, name);
//
//
//                left_right.getChildren().add(new WhiteBoard());
//                left_right.getChildren().add(list);
//
