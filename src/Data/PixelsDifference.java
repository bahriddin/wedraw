package Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import me.lemire.integercompression.differential.*;

/**
 * Created by zy on 20/09/2017.
 */

/**
 * this immutable class can be regarded as a compressed representation of a list of pixels
 */
public class PixelsDifference implements Serializable {

    // compressed list of coordinates x, y and color
    private int[] x, y, color;

    // the number of different pixels
    private int numberOfPixels;

    /**
     * Data.PixelsDifference Object will store the different pixels between oldPixels and newPixels
     * Among those pixels, only the colors of newPixels will be stored
     * Thus, Data.PixelsDifference can be used to construct newPixels given oldPixels.
     * @param oldPixels
     * @param newPixels
     */
    public PixelsDifference(int[][] oldPixels, int[][] newPixels) {

        numberOfPixels = 0;

        ArrayList<Pixel> pixels = new ArrayList<>();

        if (oldPixels.length != newPixels.length)
            return;

        for (int i = 0; i < oldPixels.length; i++) {

            if (oldPixels[i].length != newPixels[i].length)
                return;

            for (int j = 0; j < oldPixels[i].length; j++)
                if (oldPixels[i][j] != newPixels[i][j])
                    pixels.add(new Pixel(i, j, newPixels[i][j]));
        }

        constructFromPixelList(pixels);
    }

    /**
     * store a pixel matrix (cavnas) as PixelDifference.
     * it is basically to store the differences between a blank canvas and the given canvas
     * @param pixels
     */
    public PixelsDifference(int[][] canvas) {

        numberOfPixels = 0;

        ArrayList<Pixel> pixels = new ArrayList<>();

        int[][] blank = new int[canvas.length][];

        for (int i = 0; i < canvas.length; i++) {
            for (int j = 0; j < canvas[i].length; j++)
                if (canvas[i][j] != 0)
                    pixels.add(new Pixel(i, j, canvas[i][j]));
        }

        constructFromPixelList(pixels);
    }

    /**
     * Directly construct a Data.PixelsDifference Object from Data.Pixel ArrayList
     * @param pixels
     */
    public PixelsDifference(ArrayList<Pixel> pixels) {
        constructFromPixelList(pixels);
    }

    // return the number of pixels
    public int size() {
        return numberOfPixels;
    }

    /**
     * return the pixel list. return null if any error occurs
     * @return
     */
    public ArrayList<Pixel> getPixels() {

        if (numberOfPixels == 0)
            return null;

        ArrayList<Pixel> pixels = new ArrayList<>();

        IntegratedIntCompressor compressor = new IntegratedIntCompressor();

        int[] tmpX = compressor.uncompress(x);
        int[] tmpY = compressor.uncompress(y);
        int[] tmpColor = compressor.uncompress(color);

        if (tmpX.length != numberOfPixels || tmpY.length != numberOfPixels
                || tmpColor.length != numberOfPixels)
            return null;

        for (int i = 0; i < numberOfPixels; i++) {
            Pixel tmpPixel = new Pixel(tmpX[i], tmpY[i], tmpColor[i]);
            pixels.add(tmpPixel);
        }

        return pixels;
    }

    public static void main(String[] args) {

        //test

        int[][] a = { {1,2,3}, {4,5,6}, {7,8}};
        int[][] b = { {1,2,9}, {11,5,6}, {7,33}};

        PixelsDifference d = new PixelsDifference(a, b);

        CanvasLog log = new CanvasLog(b);

        log.updateCanvas(a);

        int[][] c = log.popLastCanvas();

        for (int i = 0; i < c.length; i++)
            System.out.println(Arrays.toString(c[i]));
    }

    /**
     * update Data.PixelsDifference based on Data.Pixel ArrayList
     * @param pixels
     */
    private void constructFromPixelList(ArrayList<Pixel> pixels) {

        // store different pixels in arrayList
        ArrayList<Integer> tmpX = new ArrayList<>(),
                tmpY = new ArrayList<>(),
                tmpColor = new ArrayList<>();

        for (Pixel pixel : pixels) {
            tmpX.add(pixel.x());
            tmpY.add(pixel.y());
            tmpColor.add(pixel.color());
        }

        IntegratedIntCompressor compressor = new IntegratedIntCompressor();

        numberOfPixels = tmpColor.size();

        // convert those 3 ArrayLists to Array and compress them
        x = compressor.compress(fromList(tmpX));
        y = compressor.compress(fromList(tmpY));
        color = compressor.compress(fromList(tmpColor));
    }

    /**
     * Convert an ArrayList to Array
     * @param list
     * @return
     */
    private static int[] fromList(ArrayList<Integer> list) {

        int[] result = new int [list.size()];

        for (int i = 0; i < list.size(); i++)
            result[i] = list.get(i);

        return result;
    }

}
