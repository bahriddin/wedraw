import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import me.lemire.integercompression.differential.*;

/**
 * Created by zy on 20/09/2017.
 */
public class PixelsDifference {

    public final static int N = 100;

    // compressed list of coordinates x, y and color
    private int[] x, y, color;

    // the number of different pixels
    private int numberOfPixels;

    /**
     * PixelsDifference Object will store the different pixels between oldPixels and newPixels
     * Among those pixels, only the colors of newPixels will be stored
     * Thus, PixelsDifference can be used to construct newPixels given oldPixels.
     * @param oldPixels
     * @param newPixels
     */
    PixelsDifference(int[][] oldPixels, int[][] newPixels) {

        numberOfPixels = 0;

        // store different pixels in arrayList
        ArrayList<Integer> tmpX = new ArrayList<>(),
                           tmpY = new ArrayList<>(),
                           tmpColor = new ArrayList<>();

        if (oldPixels.length != newPixels.length)
            return;

        for (int i = 0; i < oldPixels.length; i++) {

            if (oldPixels[i].length != newPixels[i].length)
                return;

            for (int j = 0; j < oldPixels[i].length; j++)
                if (oldPixels[i][j] != newPixels[i][j]) {
                    tmpX.add(i);
                    tmpY.add(j);
                    tmpColor.add(newPixels[i][j]);
                }
        }

        IntegratedIntCompressor compressor = new IntegratedIntCompressor();

        numberOfPixels = tmpColor.size();

        // convert those 3 ArrayLists to Array and compress them
        x = compressor.compress(fromList(tmpX));
        y = compressor.compress(fromList(tmpY));
        color = compressor.compress(fromList(tmpColor));

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

        CanvasLog log = new CanvasLog();

        log.updateCanvas(a, b);

        int[][] c = log.undo(b);

        for (int i = 0; i < c.length; i++)
            System.out.println(Arrays.toString(c[i]));
    }

    private static int[] fromList(ArrayList<Integer> list) {

        int[] result = new int [list.size()];

        for (int i = 0; i < list.size(); i++)
            result[i] = list.get(i);

        return result;
    }

}
