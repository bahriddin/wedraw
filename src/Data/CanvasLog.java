package Data;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by zy on 20/09/2017.
 */
public class CanvasLog {

    Queue<PixelsDifference> logs;

    public static final int MAX_LOG_LENGTH = 100;

    CanvasLog() {
        logs = new ArrayBlockingQueue<PixelsDifference>(MAX_LOG_LENGTH);
    }

    /**
     * record the update operation which turns oldCanvas into newCanvas
     * @param oldCanvas
     * @param newCanvas
     */
    void updateCanvas(int[][] oldCanvas, int[][] newCanvas) {

        // to implement undo, the colors of the modified part of oldCanvas should be stored
        PixelsDifference operation = new PixelsDifference(newCanvas, oldCanvas);

        if (logs.size() >= MAX_LOG_LENGTH)
            logs.remove();

        logs.add(operation);
    }

    /**
     * pop and return the last operation
     * @return last operation, null if failed
     */
    PixelsDifference undo() {
        if (logs.isEmpty())
            return null;

        return logs.remove();
    }

    /**
     * pop the last operation, and apply it to the canvas
     * @param canvas
     * @return restored canvas, null if failed
     */
    int[][] undo(int[][] canvas) {
        if (logs.isEmpty())
            return null;

        PixelsDifference operation = logs.remove();

        if (operation.size() == 0 || canvas.length == 0)
            return null;

        int[][] result = new int[canvas.length][];

        try {
            for (int i = 0; i < canvas.length; i++)
                result[i] = canvas[i].clone();

            ArrayList<Pixel> pixels = operation.getPixels();
            for (Pixel pixel : pixels)
                result[pixel.x()][pixel.y()] = pixel.color();

        } catch (Exception e) {
            return null;
        }

        return result;
    }


}
