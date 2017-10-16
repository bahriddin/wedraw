package GUI.run;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import GUI.Layout.WhiteBoard;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class run extends Application {

    public static TimerThread timerThread;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

//        Parent root = new WhiteBoard();

        Parent root = FXMLLoader.load(getClass().getResource("LoadingPage.fxml"));
        primaryStage.setTitle("Whiteboard");
//        primaryStage.setScene(new Scene(root, 1000, 1000));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                System.out.println("Stage is closing");
            }
        });







    }
}
