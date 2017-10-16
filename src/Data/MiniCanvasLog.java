package Data;

import java.io.Serializable;
import java.util.Stack;

/**
 * Created by zy on 16/10/17.
 */

// a compressed canvas log, only used for transmission
public class MiniCanvasLog implements Serializable {


    private Stack<PixelsDifference> logs;

    private int width, height;

    private PixelsDifference pixels;

    /**
     * Constructor
     * Return a compressed canvas log given a normal canvas log
     */
    public MiniCanvasLog(CanvasLog initialLogs) {

        logs = initialLogs.getLogs();

        int[][] canvas = initialLogs.getCurrentCanvas();

        width = canvas.length;
        height = canvas[0].length;

        pixels = new PixelsDifference(canvas);
    }

    /*
     * @return the uncompressed log of the mini log
     */
    public CanvasLog getUncompressedLogs() {

        int[][] canvas = new int[width][height];

        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                canvas[i][j] = 0;

        for (Pixel pixel : pixels.getPixels())
            canvas[pixel.x()][pixel.y()] = pixel.color();

        return new CanvasLog(canvas, logs);
    }

}
