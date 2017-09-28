package GUI.DrawSettings;

import javafx.scene.paint.Color;

public class DrawSettings {

    public static final Color DEAFAULT_DRAW_COLOR = Color.BLACK;
    public static  Color color = DEAFAULT_DRAW_COLOR;

    public static final double DEAFAULT_DRAW_WIDTH = 5.0;
    private static double draw_width = DEAFAULT_DRAW_WIDTH;

    public static final String DEAFAULT_FONT = "fd";
//    private static double draw_width = DEAFAULT_DRAW_WIDTH;




//      Get the  color.

    public static Color getColor() {
        return color;
    }


//     Set the  Color.

    public static void setColor(Color color) {
        DrawSettings.color = color;
    }

    public static void setColor(double r, double g, double b) {
        setColor(new Color(r, g, b, 1.0));
    }


//     Get the pen width.

    public static double getWidth() {
        return draw_width;
    }


//     Set the pen width.

    public static void setWidth(double width) {
        DrawSettings.draw_width = width;

    }

    //     Set the pen width.



}
