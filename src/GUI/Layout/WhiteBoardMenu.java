package Layout;



import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import javafx.stage.FileChooser;



public class WhiteBoardMenu extends MenuBar {

    private FileChooser fileChooser;


    public WhiteBoardMenu() {


        // Setup the File Menu.
        Menu file = new Menu("file");

        // Create the screenshot menu item.
        MenuItem save = new MenuItem("Save");


        // Fill the file Menu.
        file.getItems().add(save);

        // Fill the MenuBar.
        getMenus().add(file);
    }



}
