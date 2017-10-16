package GUI.Layout;

import Data.Users;
import GUI.Tools.Tool;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static GUI.DrawSettings.DrawSettings.color;

public class Manager extends WhiteBoard{


    VBox V_list = new VBox();

    HBox H_list = new HBox();

    VBox Button_list = new VBox();

    TextArea chatBox = new TextArea();

    public static String KICK_ICON = "/images/kickoff.png";

    Image kick = new Image(getClass().getResourceAsStream(KICK_ICON),30, 30, true, true);

    String local_Host;

    int port;

    ServerSocket s;

    List<Users> user_list = new LinkedList<Users>();
    List<Button> user_button_list = new LinkedList<Button>();

    int user_number = 0;
//    int max_user_number = 6;


    public String manager_name = "Manager";


    public Manager(Users user) {


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


        Label label = new Label(" Canvas ID: #\n      Manager Name: "+manager_name+"\n@ " + local_Host +" : "+port);


        V_list.getChildren().add(H_list);


        H_list.setAlignment(Pos.CENTER);


        topControls.getChildren().add(label);


//        getChildren().add(left_right);


        Label label1 = new Label("Current Users: ");
        V_list.getChildren().add(label1);

//        class BottomList extends ArrayList<Button>{
//            public Button getfromName(String Name){}
//        }
//
////        BottonList.add();
////        BottonList.remove();



        V_list.getChildren().add(Button_list);
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
                generate_user(user);
            }
        });


        V_list.getChildren().add(chatBox);


        HBox chat_input = new HBox();

        chat_input.getChildren().add(inputTxt);
        chat_input.getChildren().add(send_btn);

        V_list.getChildren().add(chat_input);

        V_list.setMargin(chat_input,new Insets(15,0,0,0) );


        setRight(V_list);

        V_list.toFront();

        chatBox.setText("Hello! "+manager_name);



    }

    public void generate_user(Users user){

            user_button_list.add(new Button());
            user_button_list.get(user_number).setText("User1");
            user_button_list.get(user_number).setPrefSize(300,40);
            Button_list.getChildren().add(user_button_list.get(user_number));

            Button btn = user_button_list.get(user_number);

            btn.setOnMouseEntered(new EventHandler<MouseEvent>
                        () {
                    @Override
                    public void handle(MouseEvent t) {
    //                btn4.setStyle("-fx-background-color:#dae7f3;");
                        btn.setGraphic(new ImageView(kick));
                    }
                });

                 btn.setOnMouseExited(new EventHandler<MouseEvent>
                    () {
                @Override
                public void handle(MouseEvent t) {
                    btn.setGraphic(null);
                }
            });

            btn.setOnAction(new EventHandler<ActionEvent>() {

                        public void handle(ActionEvent arg0) {
                            Button_list.getChildren().remove(btn);


                        }
                    });

            user_number+=1;

    }



//            user_button_list.get(user_number).setOnAction(new EventHandler<ActionEvent>() {
//
//                public void handle(ActionEvent arg0) {
//                    V_list.getChildren().remove(user_button_list.get(user_number));
//
//
//                }
//            });


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
