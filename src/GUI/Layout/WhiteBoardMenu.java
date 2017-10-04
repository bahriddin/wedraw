package GUI.Layout;



import GUI.run.run;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;


public class WhiteBoardMenu extends MenuBar {

    private FileChooser fileChooser;


    public WhiteBoardMenu(CanvasArea canvasArea) {


        // Setup the File Menu.
        Menu file = new Menu("File");

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
                        WritableImage writableImage = new WritableImage(1000, 1000);
                        canvasArea.permanentCanvas.snapshot(null, writableImage);
                        RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                        ImageIO.write(renderedImage, "png", file);
                    } catch (IOException ex) {
                        Logger.getLogger(run.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        });


        // Create the save logfile menu item.


        MenuItem save = new MenuItem("Save");

        save.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                FileChooser fileChooser = new FileChooser();

                //Set extension filter
                FileChooser.ExtensionFilter extFilter =
                        new FileChooser.ExtensionFilter("png files (*.log)", "*.log");
                fileChooser.getExtensionFilters().add(extFilter);

                //Show save file dialog
                File file = fileChooser.showSaveDialog(null);

                if(file != null){
                    try {
                        FileOutputStream ostream = new FileOutputStream(file);
                        ObjectOutputStream p = new ObjectOutputStream(ostream);
                        p.writeObject(canvasArea.model.getLog());
                        p.flush();
                        ostream.close();
                    } catch (IOException ex) {
                        Logger.getLogger(run.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        });


        // Fill the file Menu.
        file.getItems().add(export);
        file.getItems().add(save);

        // Fill the MenuBar.
        getMenus().add(file);
    }










}
