package GUI.run;

import GUI.Layout.Manager;
import GUI.Layout.WhiteBoard;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ResourceBundle;

public class LoadingPageController implements Initializable {

    @FXML
    private StackPane rootPane;



    public void create() throws IOException {



        Parent root = new Manager();
        Stage stage = new Stage();
        stage.setTitle("Whiteboard");
        stage.setScene(new Scene(root, 950, 1000));
        stage.show();
        rootPane.getScene().getWindow().hide();

    }


    public void join(){


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
