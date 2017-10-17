package GUI.Layout;

import GUI.Tools.Tool;
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
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;

import static GUI.DrawSettings.DrawSettings.color;
import static GUI.Layout.ToolsPanel.undoBtn;

public class Client extends WhiteBoard{

    VBox V_list = new VBox();

    HBox H_list = new HBox();

    HBox H_Print = new HBox();

    public static TextArea chatBox = new TextArea();

    public static String KICK_ICON = "/images/kickoff.png";

    String local_Host;

    int port;

    ServerSocket s;

    public String user_name = "UserName";


    public Client(String user_name,String canvas_id) {



        try {
            local_Host = InetAddress.getLocalHost().getHostAddress();
            try {
                s = new ServerSocket(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }


        int port = s.getLocalPort();


        Label label = new Label(" Canvas ID: "+canvas_id+"\n      User Name: "+user_name+"\n@ " + local_Host +" : "+port);


        V_list.getChildren().add(H_list);


        H_list.setAlignment(Pos.CENTER);


        H_Print.getChildren().add(label);
        topControls.getChildren().add(H_Print);


        ToolsPanel.hbox2.getChildren().remove(undoBtn);

        WhiteBoardMenu.file.getItems().remove(WhiteBoardMenu.load);
        WhiteBoardMenu.file.getItems().remove(WhiteBoardMenu.newCanvas);




//        getChildren().add(left_right);

        Label label1 = new Label("Current Users: ");
        V_list.getChildren().add(label1);

        Button btn1 = new Button();
        btn1.setText("User1");
        btn1.setPrefSize(300,40);
        V_list.getChildren().add(btn1);


        Button btn2 = new Button();
        btn2.setText("User2");
        btn2.setPrefSize(300,40);
        V_list.getChildren().add(btn2);

        Button btn3 = new Button();
        btn3.setText("User3");
        btn3.setPrefSize(300,40);
        V_list.getChildren().add(btn3);

        Button btn4 = new Button();
        btn4.setText("User4");
        btn4.setPrefSize(300,40);
        V_list.getChildren().add(btn4);
        V_list.setPadding(new Insets(5));

        Image kick = new Image(getClass().getResourceAsStream(KICK_ICON),30, 30, true, true);


        btn1.setOnMouseExited(new EventHandler<MouseEvent>
                () {

            @Override
            public void handle(MouseEvent t) {
                btn1.setGraphic(null);
            }
        });


        btn2.setOnMouseExited(new EventHandler<MouseEvent>
                () {

            @Override
            public void handle(MouseEvent t) {
                btn2.setGraphic(null);
            }
        });


        btn3.setOnMouseExited(new EventHandler<MouseEvent>
                () {

            @Override
            public void handle(MouseEvent t) {
                btn3.setGraphic(null);
            }
        });





        btn4.setOnMouseExited(new EventHandler<MouseEvent>
                () {

            @Override
            public void handle(MouseEvent t) {
                btn4.setGraphic(null);
            }
        });


        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                btn1.setText("User1");

            }
        });

        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                btn2.setText("User2");
            }
        });
        btn3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                btn3.setText("User3");
            }
        });
        btn4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                btn4.setText("User4");
            }
        });


        Label chat_label = new Label("Chat Box");


        V_list.getChildren().add(chat_label);


        chatBox.setPrefSize(300,400);
        chatBox.setEditable(false);

        TextArea inputTxt = new TextArea();
        inputTxt.setPrefSize(230,50);

        Button send_btn = new Button("Send");
        send_btn.setPrefSize(70,70);


        V_list.getChildren().add(chatBox);


        HBox chat_input = new HBox();

        chat_input.getChildren().add(inputTxt);
        chat_input.getChildren().add(send_btn);

        V_list.getChildren().add(chat_input);

        V_list.setMargin(chat_input,new Insets(15,0,0,0) );


        setRight(V_list);

        chatBox.setText("Hello! "+user_name+". Welcome to Canvas #"+canvas_id);



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
