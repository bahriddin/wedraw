package Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by zy on 20/09/2017.
 */

// a log class consisting of logs and a canvas
public class CanvasLog implements Serializable {

    private Stack<PixelsDifference> logs;

    private int[][] currentCanvas;

    public static final int MAX_LOG_LENGTH = 100;

    public CanvasLog(int[][] initialCanvas) {

        logs = new Stack<PixelsDifference>();

        currentCanvas = new int[initialCanvas.length][];

            for (int i = 0; i< initialCanvas.length; i++)
                currentCanvas[i] = initialCanvas[i].clone();
    }


    /**
     * a constructor only should be called by MiniCanvasLog
     * @param initialCanvas
     * @param logs
     */
    CanvasLog(int[][] initialCanvas, Stack<PixelsDifference> logs) {

        this(initialCanvas);

        this.logs = (Stack<PixelsDifference>)logs.clone();
    }

    /**
     * getter
     * @return a deep copy of currentCanvas
     */
    public int[][] getCurrentCanvas() {

        if (currentCanvas == null || currentCanvas.length < 1)
            return null;

        int[][] result = new int[currentCanvas.length][];

        for (int i = 0; i < currentCanvas.length; i++)
            result[i] = currentCanvas[i].clone();

        return result;
    }

    /**
     * getter
     * @return a shallow copy of logs
     */
    public Stack<PixelsDifference> getLogs() {
        return (Stack<PixelsDifference>)logs.clone();
    }

    /**
     * record the update operation which turns oldCanvas into newCanvas
     * @param oldCanvas
     * @param newCanvas
     */
    public void updateCanvas(int[][] newCanvas) {

        // to implement undo, the colors of the modified part of oldCanvas should be stored
        PixelsDifference operation = new PixelsDifference(newCanvas, currentCanvas);

        // no need to update
        if (operation.size() == 0)
            return;

        if (logs.size() >= MAX_LOG_LENGTH)
            logs.remove(0);

        logs.add(operation);

        for (int i = 0; i < newCanvas.length; i++)
            currentCanvas[i] = newCanvas[i].clone();
    }

    /**
     * update the canvas based on pixel difference (operation) and update log
     * @param operation
     */
    public void updateCanvas(PixelsDifference operation) {

        /*int[][] oldCanvas = new int[currentCanvas.length][];

        for (int i = 0; i < currentCanvas.length; i++)
            oldCanvas = currentCanvas.clone();
            */
        int[][] newCanvas = getNewCanvasGivenOperation(operation);

        if (newCanvas != null)
            updateCanvas(newCanvas);
    }

    /**
     * pop and return the last operation
     * @return last operation, null if failed
     */
    public PixelsDifference popLastOperation() {
        if (logs.isEmpty())
            return null;

        PixelsDifference result = logs.pop();
        undo(result);
        return result;
    }

    /**
     * pop the last operation, then return the last(restored) canvas
     * @return restored canvas, null if failed
     */
    public int[][] popLastCanvas() {
        if (logs.isEmpty())
            return null;

        return undo(logs.pop());
    }

    private int[][] getNewCanvasGivenOperation(PixelsDifference operation) {

        if (operation == null || operation.size() == 0 ||
                currentCanvas == null || currentCanvas.length < 1)
            return null;

        int[][] result = new int[currentCanvas.length][];

        try {
            for (int i = 0; i < currentCanvas.length; i++)
                result[i] = currentCanvas[i].clone();

            ArrayList<Pixel> pixels = operation.getPixels();
            for (Pixel pixel : pixels)
                result[pixel.x()][pixel.y()] = pixel.color();

        } catch (Exception e) {
            return null;
        }

    }

    /**
     * return the restored canvas
     * this method is used to update currentCanvas
     * @param operation
     * @return restored canvas, null if failed
     */
    private int[][] undo(PixelsDifference operation) {

        int[][] result = getNewCanvasGivenOperation(operation);

        if (result != null)
            for (int i = 0; i < result.length; i++)
                currentCanvas[i] = result[i].clone();

        return result;
    }


}
