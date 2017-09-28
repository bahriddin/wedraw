package Data;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by zy on 20/09/2017.
 */
public class CanvasLog {

    private Queue<PixelsDifference> logs;

    private int[][] currentCanvas;

    public static final int MAX_LOG_LENGTH = 100;

    public CanvasLog(int[][] initialCanvas) {
        logs = new ArrayBlockingQueue<PixelsDifference>(MAX_LOG_LENGTH);

        currentCanvas = initialCanvas;
    }

    /**
     * record the update operation which turns oldCanvas into newCanvas
     * @param oldCanvas
     * @param newCanvas
     */
    void updateCanvas(int[][] newCanvas) {

        // to implement undo, the colors of the modified part of oldCanvas should be stored
        PixelsDifference operation = new PixelsDifference(newCanvas, currentCanvas);

        if (logs.size() >= MAX_LOG_LENGTH)
            logs.remove();

        logs.add(operation);

        currentCanvas = newCanvas;
    }

    /**
     * pop and return the last operation
     * @return last operation, null if failed
     */
    PixelsDifference popLastOperation() {
        if (logs.isEmpty())
            return null;

        return logs.remove();
    }

    /**
     * pop the last operation, and apply it to current canvas
     * @return restored canvas, null if failed
     */
    int[][] undo() {
        if (logs.isEmpty())
            return null;

        PixelsDifference operation = logs.remove();

        if (operation.size() == 0 || currentCanvas.length == 0)
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

        currentCanvas = result;

        return result;
    }


}
