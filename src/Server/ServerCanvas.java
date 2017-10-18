package Server;

import Data.CanvasHelper;
import Data.CanvasLog;
import Data.Pixel;
import Data.PixelsDifference;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.io.*;

/**
 * Created by zy on 17/10/2017.
 */
public class ServerCanvas {

    private CanvasLog canvas;

    private String manager;

    // including manager
    private ArrayList<String> users;

    private String canvasName;

    private static int[][] blankCanvas;

    ServerCanvas(String canvasName, String manager) {
        this.canvasName = canvasName;
        this.manager = manager;

        users = new ArrayList<>();
        users.add(manager);

        if (blankCanvas == null) {

            blankCanvas = new int[CanvasHelper.DEFAULT_CANVAS_WIDTH][];

            for (int x = 0; x < CanvasHelper.DEFAULT_CANVAS_WIDTH; x++) {
                blankCanvas[x] = new int[CanvasHelper.DEFAULT_CANVAS_HEIGHT];
                for (int y = 0; y < CanvasHelper.DEFAULT_CANVAS_HEIGHT; y++)
                    blankCanvas[x][y] = CanvasHelper.colorToInt(Color.WHITE);
            }

        }

        canvas = new CanvasLog(blankCanvas);
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

    public PixelsDifference getCanvasAsPixelDifference() {

        PixelsDifference result =  new PixelsDifference(blankCanvas, canvas.getCurrentCanvas());
        
        return result;
    }

    public boolean loadCanvas(String canvasName) {
        CanvasLog canvasFromFile = loadFile(canvasName);

        if (canvasFromFile != null) {
            this.canvasName = canvasName;
            this.canvas = canvasFromFile;
            return true;
        } else
            return false;
    }

    /*
    public boolean save() {
        return saveAsCanvas(this.canvasName);
    }
    */

    public boolean saveAs(String canvasName) {
        saveFile(canvasName + ".wedraw");
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


    private void saveFile(String filename) {
        ObjectOutputStream fileWriter = null;
        try {
            fileWriter = new ObjectOutputStream(new FileOutputStream(filename));
            fileWriter.writeObject(canvas);
        } catch (Exception e) {
            // do nothing
        } finally {
            close(fileWriter);
        }
    }

    private CanvasLog loadFile(String filename) {
        CanvasLog result;
        ObjectInputStream fileReader = null;

        try {
            fileReader = new ObjectInputStream(new FileInputStream(filename));
            result = (CanvasLog) fileReader.readObject();

        } catch (Exception e) {
            result = null;
        } finally {
            close(fileReader);
        }

        return result;
    }


    /**
     * close a closeable object
     * @param toBeClosed
     */
    private static void close(Closeable toBeClosed) {
        if (toBeClosed != null) {
            try {
                toBeClosed.close();
            } catch (Exception e) {
                // do nothing
            }
        }
    }

}
