package GUI.run;

import Data.Users;
import GUI.Layout.Client;
import GUI.Layout.Manager;
import GUI.Layout.WhiteBoard;
import Model.CanvasInteraction;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
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

public class LoadingPageController implements Initializable {

    @FXML
    private StackPane rootPane;

    @FXML
    private TextField username;

    @FXML
    private TextField canvas_id;

    public  static Boolean is_connected = false;

    private int alert_once = 0;

    public LoadingPageController(){

    }

    Users this_manager ;


    public void create() throws IOException {


        if (username.getText() == null || username.getText().trim().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter a user name");
            alert.showAndWait();
        }

        if(!username.getText().trim().isEmpty()){
            this_manager = new Users(username.getText());
            WhiteBoard root = new Manager(this_manager);

            run.timerThread = new TimerThread(root.getCanvasArea().getModel(),username.getText());
            run.timerThread.start();



            while (admModel==null){
                try {
                    Thread.currentThread().sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }



            admModel.Send_CREATE_CANVAS(canvas_id.getText());

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



//            if(!is_connected){
//
//                    dialog_fail();
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
//
//                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                        alert.setTitle("warning");
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
//                    }
//                });
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

            WhiteBoard root = new Client(username.getText(),canvas_id.getText());
            run.timerThread = new TimerThread(root.getCanvasArea().getModel(),username.getText());
            run.timerThread.start();

            while (admModel==null){System.out.print(" ");}
            admModel.Send_JOIN_REQUEST(canvas_id.getText());

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

    public void dialog_fail(){

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
//        alert.setHeaderText("Look, an Error Dialog");
        alert.setContentText("Connection Error! Try again later");

        alert.showAndWait();
    }

    public void dialog_success(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle("Error");
//        alert.setHeaderText("Look, an Error Dialog");
        alert.setContentText("connected TO Canvas "+canvas_id.getText());

        alert.showAndWait();

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        new LoadingPag().start();



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
//
//
//        }
//
//    }


}
