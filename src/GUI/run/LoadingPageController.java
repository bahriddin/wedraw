package GUI.run;

import GUI.Layout.Client;
import GUI.Layout.Manager;
import GUI.Layout.WhiteBoard;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;

public class LoadingPageController implements Initializable {

    @FXML
    private StackPane rootPane;

    @FXML
    private TextField username;

    @FXML
    private TextField canvas_id;



    public void create() throws IOException {


        if (username.getText() == null || username.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter a user name");
            alert.showAndWait();
        }

        if(!username.getText().trim().isEmpty()){
            Parent root = new Manager(username.getText());
            Stage stage = new Stage();
            stage.setTitle("Whiteboard");
            stage.setScene(new Scene(root, 950, 1000));
            stage.show();
            rootPane.getScene().getWindow().hide();

            run.timerThread.start();
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
            Parent root = new Client(username.getText(),canvas_id.getText());
            Stage stage = new Stage();
            stage.setTitle("Whiteboard");
            stage.setScene(new Scene(root, 950, 1000));
            stage.show();
            rootPane.getScene().getWindow().hide();
            run.timerThread.start();
        }


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
