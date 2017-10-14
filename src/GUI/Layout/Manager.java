package GUI.Layout;

import GUI.Tools.Tool;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
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

public class Manager extends WhiteBoard{

    VBox V_list = new VBox();

    HBox H_list = new HBox();

    TextArea chatBox = new TextArea();

    public static String KICK_ICON = "/images/kickoff.png";

    String local_Host;

    int port;

    ServerSocket s;


    public Manager() {



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


        Label label = new Label(" Canvas ID: #\n      You're the Manager : \n@ " + local_Host +" : "+port);


        V_list.getChildren().add(H_list);




        H_list.setAlignment(Pos.CENTER);



        topControls.getChildren().add(label);






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

        btn1.setOnMouseEntered(new EventHandler<MouseEvent>
                () {

            @Override
            public void handle(MouseEvent t) {
//                btn4.setStyle("-fx-background-color:#dae7f3;");
                btn1.setGraphic(new ImageView(kick));

            }
        });

        btn1.setOnMouseExited(new EventHandler<MouseEvent>
                () {

            @Override
            public void handle(MouseEvent t) {
                btn1.setGraphic(null);
            }
        });
        btn2.setOnMouseEntered(new EventHandler<MouseEvent>
                () {

            @Override
            public void handle(MouseEvent t) {
//                btn4.setStyle("-fx-background-color:#dae7f3;");
                btn2.setGraphic(new ImageView(kick));

            }
        });

        btn2.setOnMouseExited(new EventHandler<MouseEvent>
                () {

            @Override
            public void handle(MouseEvent t) {
                btn2.setGraphic(null);
            }
        });
        btn3.setOnMouseEntered(new EventHandler<MouseEvent>
                () {

            @Override
            public void handle(MouseEvent t) {
//                btn4.setStyle("-fx-background-color:#dae7f3;");
                btn3.setGraphic(new ImageView(kick));

            }
        });

        btn3.setOnMouseExited(new EventHandler<MouseEvent>
                () {

            @Override
            public void handle(MouseEvent t) {
                btn3.setGraphic(null);
            }
        });



        btn4.setOnMouseEntered(new EventHandler<MouseEvent>
                () {

            @Override
            public void handle(MouseEvent t) {
//                btn4.setStyle("-fx-background-color:#dae7f3;");
                btn4.setGraphic(new ImageView(kick));

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



        V_list.getChildren().add(chatBox);




        setRight(V_list);











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
