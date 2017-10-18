package GUI.Layout;



import Data.CanvasLog;
import GUI.run.run;
import Model.CanvasInteraction;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import javafx.scene.control.TextInputDialog;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static GUI.run.TimerThread.admModel;


public class WhiteBoardMenu extends MenuBar {

    private FileChooser fileChooser;

    static Menu file;

    static MenuItem newCanvas;

    static MenuItem load;

    static MenuItem save_as;
    static MenuItem save;


    public WhiteBoardMenu(CanvasArea canvasArea) {


        // Setup the File Menu.
        file = new Menu("File");

        // Create the screenshot menu item.
        MenuItem export = new MenuItem("Export");

        export.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                FileChooser fileChooser = new FileChooser();

                //Set extension filter
                FileChooser.ExtensionFilter extFilter =
                        new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
                fileChooser.getExtensionFilters().add(extFilter);

                //Show save file dialog
                File file = fileChooser.showSaveDialog(null);

                if(file != null){
                    try {
                        WritableImage writableImage = new WritableImage(640, 690);
                        WhiteBoard.canvasArea.networkCanvas.snapshot(null, writableImage);
                        RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                        ImageIO.write(renderedImage, "png", file);
                    } catch (IOException ex) {
                        Logger.getLogger(run.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        });


        // Create the save logfile menu item.


        save = new MenuItem("Save");

        save.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {

                admModel.Send_SAVE_CANVAS(run.c.canvas_id.getText());
//                FileChooser fileChooser = new FileChooser();
//
//                //Set extension filter
//                FileChooser.ExtensionFilter extFilter =
//                        new FileChooser.ExtensionFilter("wedraw files (*.wedraw)", "*.wedraw");
//                fileChooser.getExtensionFilters().add(extFilter);
//
//                //Show save file dialog
//                File file = fileChooser.showSaveDialog(null);
//
//                if(file != null){
//                    try {
//                        FileOutputStream ostream = new FileOutputStream(file);
//                        ObjectOutputStream p = new ObjectOutputStream(ostream);
//                        p.writeObject(canvasArea.model.getLog());
//                        p.flush();
//                        ostream.close();
//                    } catch (IOException ex) {
//                        Logger.getLogger(run.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
            }

        });


        load = new MenuItem("Load");

        load.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {

                TextInputDialog dialog = new TextInputDialog(" Canvas name");
                dialog.setTitle("Load Canvas");
//                dialog.setHeaderText("Look, a Text Input Dialog");
                dialog.setContentText("Please enter Canvas name you want load:");

// Traditional way to get the response value.
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()){
                    admModel.Send_LOAD_CANVAS(result.get());
//                    System.out.println("Rename Canvas name: " + result.get());
                }

//                FileChooser fileChooser = new FileChooser();
//
//                //Set extension filter
//                FileChooser.ExtensionFilter extFilter =
//                        new FileChooser.ExtensionFilter("wedraw files (*.wedraw)", "*.wedraw");
//                fileChooser.getExtensionFilters().add(extFilter);
//
//                //Show save file dialog
//                File file = fileChooser.showOpenDialog(null);
//
//                if(file != null){
//                    try {
//                        FileInputStream fis = new FileInputStream(file);
//                        ObjectInputStream ois = new ObjectInputStream(fis);
//                        CanvasLog readLog = (CanvasLog) ois.readObject();
////                        canvasArea.model = new CanvasInteraction(canvasArea.permanentCanvas,canvasArea.temporaryCanvas,canvasArea.networkCanvas,readLog);
//                        ois.close();
//
//                    } catch (IOException ex) {
//                        Logger.getLogger(run.class.getName()).log(Level.SEVERE, null, ex);
//                    } catch (ClassNotFoundException e) {
//                        e.printStackTrace();
//                        Logger.getLogger(run.class.getName()).log(Level.SEVERE, null, e);
//                    }
//                }
            }

        });


        newCanvas = new MenuItem("New");

        newCanvas.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
//                System.out.print("new");
//                canvasArea.model = new CanvasInteraction(canvasArea.permanentCanvas,canvasArea.temporaryCanvas,canvasArea.networkCanvas);
                admModel.Send_NEW_CANVAS();
            }

        });

        save_as = new MenuItem("Save As");

        save_as.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
//                System.out.print("new");
                TextInputDialog dialog = new TextInputDialog("New Canvas name");
                dialog.setTitle("Save As");
//                dialog.setHeaderText("Look, a Text Input Dialog");
                dialog.setContentText("Please enter New Canvas name:");

// Traditional way to get the response value.
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()){
                    admModel.Send_SAVE_CANVAS(result.get());
                    System.out.println("Rename Canvas name: " + result.get());
                }

// The Java 8 way to get the response value (with lambda expression).
            }

        });




        // Fill the file Menu.
        file.getItems().add(newCanvas);
        file.getItems().add(load);

        file.getItems().add(save);
        file.getItems().add(save_as);

        file.getItems().add(export);



        // Fill the MenuBar.
        getMenus().add(file);
    }










}
