package Data;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by zy on 20/09/2017.
 */
public class CanvasLog {

    private Stack<PixelsDifference> logs;

    private int[][] currentCanvas;

    public static final int MAX_LOG_LENGTH = 100;

    public CanvasLog(int[][] initialCanvas) {

        logs = new Stack<PixelsDifference>();
        //logs = new ArrayBlockingStack<PixelsDifference>(MAX_LOG_LENGTH);

        currentCanvas = initialCanvas;
    }

    /**
     * getter
     * @return currentCanvas
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
     * record the update operation which turns oldCanvas into newCanvas
     * @param oldCanvas
     * @param newCanvas
     */
    public void updateCanvas(int[][] newCanvas) {

        // to implement undo, the colors of the modified part of oldCanvas should be stored
        PixelsDifference operation = new PixelsDifference(newCanvas, currentCanvas);

        if (logs.size() >= MAX_LOG_LENGTH)
            logs.remove(0);

        logs.add(operation);

        for (int i = 0; i < newCanvas.length; i++)
            currentCanvas[i] = newCanvas[i].clone();
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

    /**
     * return the restored canvas
     * this method is used to update currentCanvas
     * @param operation
     * @return restored canvas, null if failed
     */
    private int[][] undo(PixelsDifference operation) {

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

        for (int i = 0; i < result.length; i++)
            currentCanvas[i] = result[i].clone();

        return result;
    }


}
