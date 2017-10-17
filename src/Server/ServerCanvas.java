package Server;

import Data.CanvasLog;

import java.util.ArrayList;

/**
 * Created by zy on 17/10/2017.
 */
public class ServerCanvas {

    private CanvasLog canvasLog;

    private String manager;

    // including manager
    private ArrayList<String> users;

    private String canvasName;

    ServerCanvas(String canvasName, String manager) {
        this.canvasName = canvasName;
        this.manager = manager;

        users = new ArrayList<>();
        users.add(manager);

        //canvasLog = new CanvasLog();
    }

    public boolean loadCanvas(String canvasName) {
        this.canvasName = canvasName;
        //canvasLog =
        return true;
    }

    public boolean saveCanvas() {
        return saveAsCanvas(this.canvasName);
    }

    public boolean saveAsCanvas(String canvasName) {
        return true;
    }

    public void updateCanvas() {

    }

}
