package Server;

import Data.CanvasHelper;
import Data.CanvasLog;
import Data.PixelsDifference;

import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;

/**
 * Created by zy on 17/10/2017.
 */
public class ServerCanvas {

    private CanvasLog canvas;

    private String manager;

    // including manager
    private ArrayList<String> users;

    private String canvasName;

    ServerCanvas(String canvasName, String manager) {
        this.canvasName = canvasName;
        this.manager = manager;

        users = new ArrayList<>();
        users.add(manager);

        int[][] blankCanvas = new int[CanvasHelper.DEFAULT_CANVAS_WIDTH][]

        for (int x = 0; x < CanvasHelper.DEFAULT_CANVAS_WIDTH; x++) {
            blankCanvas[x] = new int[CanvasHelper.DEFAULT_CANVAS_HEIGHT];
            for (int y = 0; y < CanvasHelper.DEFAULT_CANVAS_HEIGHT; y++)
                blankCanvas[x][y] = 0;
        }

        canvasLog = new CanvasLog(blankCanvas);
    }

    public String getManager() {
        return manager;
    }

    public ArrayList<String> getUsers() {
        return users;
    }

    public String getCanvasName() {
        return canvasName;
    }

    public boolean loadCanvas(String canvasName) {
        this.canvasName = canvasName;
        //canvasLog =
        return true;
    }

    /*
    public boolean saveCanvas() {
        return saveAsCanvas(this.canvasName);
    }
    */

    public boolean saveAsCanvas(String canvasName) {
        return true;
    }

    public void updateCanvas(PixelsDifference operation) {
        canvas.updateCanvas(operation);
    }

    public PixelsDifference undoCanvas() {

        PixelsDifference lastOperation = canvas.popLastOperation();

        return lastOperation;
    }

    public void addUser(String username) {
        users.add(username);
    }

    public void removeUser(String username) {

        users.remove(username);

        if (username.equals(manager))
            manager = "";
    }

}
