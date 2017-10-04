package Data;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by zy on 28/09/2017.
 */
public class CanvasHelper {

    /**
     * convert a canvas into a 2d matrix of int
     * @param canvas
     * @return int matrix, null if fails
     */
    public static int[][] canvasToMatrix(Canvas canvas) {

        if (canvas == null)
            return null;

        int width = (int)canvas.getWidth();
        int height = (int)canvas.getHeight();

        int[][] result = new int[width][height];

        WritableImage image = canvas.snapshot(null, null);

        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                result[i][j] = colorToInt(image.getPixelReader().getColor(i,j));

        return result;
    }

    /**
     * convert a 2d matrix of int into a Canvas
     * @param matrix
     * @return Canvas, null if fails
     */
    public static Canvas matrixToCanvas(int[][] matrix) {

        if (matrix == null || matrix.length < 1 || matrix[0] == null)
            return null;

        int width = matrix.length;
        int height = matrix[0].length;

        Canvas canvas = new Canvas(width, height);

        PixelWriter pixelWriter = canvas.getGraphicsContext2D().getPixelWriter();

        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                pixelWriter.setColor(i, j, intToColor(matrix[i][j]));

        return canvas;
    }

    /**
     * convert a 2d matrix of int into a list of Pixels
     * @param matrix
     * @return
     */
    public static ArrayList<Pixel> matrixToPixelList(int[][] matrix) {
        ArrayList<Pixel> result = new ArrayList<>();

        if (matrix == null || matrix.length < 1 || matrix[0] == null)
            return null;

        int width = matrix.length;
        int height = matrix[0].length;

        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                result.add(new Pixel(i, j, matrix[i][j]));

        return result;
    }

    /**
     * convert Color to int, ignore opacity
     * @param color
     * @return int
     */
    public static int colorToInt(Color color) {

        int red = (int)Math.round(color.getRed() * 255);
        int green = (int)Math.round(color.getGreen() * 255);
        int blue = (int)Math.round(color.getBlue() * 255);
        int rgb = red;
        rgb = (rgb << 8) | green;
        rgb = (rgb << 8) | blue;
        return rgb;
    }

    /**
     * convert int to Color
     * @param rgb
     * @return Color
     */
    public static Color intToColor(int rgb) {
        int red = (rgb >> 16) & 0xFF;
        int green = (rgb >> 8) & 0xFF;
        int blue = rgb & 0xFF;
        return Color.rgb(red, green, blue);
    }
}
