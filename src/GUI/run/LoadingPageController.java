package GUI.run;

import Data.Users;
import GUI.Layout.Client;
import GUI.Layout.Manager;
import GUI.Layout.WhiteBoard;
import Model.CanvasInteraction;
import Network.Network;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Timer;

import static GUI.run.TimerThread.admModel;
import static javafx.scene.AccessibleAttribute.FONT;

public class LoadingPageController implements Initializable {

    @FXML
    private StackPane rootPane;

    @FXML
    private TextField username;

    @FXML
    private TextField canvas_id;

    @FXML
    public  Label logs;

    @FXML
    public Text title;

    public  static Boolean is_connected = false;

    private int alert_once = 0;

    public LoadingPageController(){

    }

    Users this_manager ;

    public static Client client_whiteboard;
    public static Manager manager_whiteboard;



    public void create() throws IOException {


        if (username.getText() == null || username.getText().trim().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter a user name");
            alert.showAndWait();
        }

        if(!username.getText().trim().isEmpty()){
            logs.setText("connecting to server...");
            this_manager = new Users(username.getText());
            manager_whiteboard = new Manager(this_manager,canvas_id.getText());
            System.out.println(this_manager);


            Network net = new Network("localhost", 3000);
            run.timerThread = new TimerThread(manager_whiteboard.getCanvasArea().getModel(),username.getText(),net);
            run.timerThread.start();

            while (admModel==null){
                try {
                    Thread.currentThread().sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }



            admModel.Send_CREATE_CANVAS(canvas_id.getText());
            admModel.setChatbox(manager_whiteboard.chatBox);

            logs.setFont(new Font(20));
            logs.setTextFill(Color.rgb(255,255,255));



//            if(!is_connected){
//
//                    dialog_fail();
//            }
//            else
//
//            {
//            }

        }

    }

    public void join() throws IOException {


        if (canvas_id.getText() == null || canvas_id.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter the Canvas ID you're joining");
            alert.showAndWait();
        }

        if (username.getText() == null || username.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter a user name");
            alert.showAndWait();
        }


        if(!canvas_id.getText().trim().isEmpty() && !username.getText().trim().isEmpty()){


            Network net = new Network("localhost", 3000);
            client_whiteboard = new Client(username.getText(),canvas_id.getText());
            run.timerThread = new TimerThread(client_whiteboard.getCanvasArea().getModel(),username.getText(),net);
            run.timerThread.start();

            while (admModel==null){
                try {
                    Thread.currentThread().sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            admModel.Send_JOIN_REQUEST(canvas_id.getText());
            admModel.setChatbox(client_whiteboard.chatBox);

            logs.setText("Waiting for manager response...");
            logs.setFont(new Font(15));
            logs.setTextFill(Color.rgb(255,255,255));


//            if(!is_connected){
//
//                dialog_fail();
//
//            }
//            else
//
//            {
//                dialog_success();
//                Stage stage = new Stage();
//                stage.setTitle("Whiteboard");
//                stage.setScene(new Scene((Parent) root, 950, 1000));
//                stage.show();
//                rootPane.getScene().getWindow().hide();
//
//                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//                    @Override
//                    public void handle(WindowEvent t) {
//                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                        alert.setTitle("warning");
////        alert.setHeaderText("This is a test.");
//                        alert.setResizable(false);
//                        alert.setContentText("Are you sure to Exit?");
//
//                        Optional<ButtonType> result = alert.showAndWait();
//                        ButtonType button = result.orElse(ButtonType.CANCEL);
//
//                        if (button == ButtonType.OK) {
//                            System.out.println("bye bye");
//                            Platform.exit();
//                            System.exit(0);
//                        } else {
//
//                        }
//
//                    }
//                });

        }

    }

    public void show_whiteboard(WhiteBoard root){
        Stage stage = new Stage();
        stage.setTitle("Whiteboard");
        stage.setScene(new Scene((Parent) root, 950, 1000));

        stage.show();
        rootPane.getScene().getWindow().hide();

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("warning");
//        alert.setHeaderText("This is a test.");
                alert.setResizable(false);
                alert.setContentText("Are you sure to Exit?");

                Optional<ButtonType> result = alert.showAndWait();
                ButtonType button = result.orElse(ButtonType.CANCEL);

                if (button == ButtonType.OK) {
                    System.out.println("bye bye");
                    Platform.exit();
                    System.exit(0);
                } else {

                }
            }
        });




    }

    public void dialog_fail(){

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
//        alert.setHeaderText("Look, an Error Dialog");
        alert.setContentText("Connection Error! Try again later");

        ButtonType buttonTypeOne = new ButtonType("OK");

        alert.getButtonTypes().setAll(buttonTypeOne);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
            Platform.exit();
            System.exit(0);
        }

        else  {
            // ... uPlatform.exit();
            Platform.exit();
            System.exit(0);


    }}

    public void dialog_success(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle("Error");
//        alert.setHeaderText("Look, an Error Dialog");
        alert.setContentText("connected TO Canvas "+canvas_id.getText());

        alert.showAndWait();

    }


    public String join_request_dialog(String client_name){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setContentText(client_name +" want to join");

        ButtonType buttonTypeOne = new ButtonType("Allow");
        ButtonType buttonTypeTwo = new ButtonType("Reject");

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne) {
            return "T";
        }
        else if (result.get() == buttonTypeTwo) {
            return "F";

        }
        else
            return "F";

    }


    public void save_success(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Save successfully!");
        alert.showAndWait();

    }

    public void client_join_rejected(){

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
//        alert.setHeaderText("Look, an Error Dialog");
        alert.setContentText("You're not allowed to join");

        alert.showAndWait();

    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        new LoadingPag().start();

        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0f);
        ds.setColor(Color.color(0.4f, 0.4f, 0.4f));

        title.setEffect(ds);
        title.setCache(true);
        title.setFont(Font.font(null, FontWeight.BOLD, 56));


    }

//    class LoadingPag extends Thread{
//
//        public void run(){
//            try {
//                Thread.sleep(3000);
//
//                Platform.runLater(new Runnable() {
//                    @Override
//                    public void run() {
//                        Parent root = new WhiteBoard();
//                        Stage stage = new Stage();
//                        stage.setTitle("Whiteboard");
//                        stage.setScene(new Scene(root, 1000, 1000));
//                        stage.show();
//                    }
//                });
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

//        }
//
//    }


}
