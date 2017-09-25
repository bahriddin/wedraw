package run;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import Layout.WhiteBoard;

public class run extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    Parent root = new WhiteBoard();

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Whiteboard");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }
}
