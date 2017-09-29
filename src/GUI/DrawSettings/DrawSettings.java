package GUI.DrawSettings;

import javafx.scene.paint.Color;

public class DrawSettings {

    public static final Color DEAFAULT_DRAW_COLOR = Color.BLACK;
    public static  Color color = DEAFAULT_DRAW_COLOR;

    public static final double DEAFAULT_DRAW_WIDTH = 5.0;
    private static double draw_width = DEAFAULT_DRAW_WIDTH;

    public static final String DEAFAULT_FONT = "Arial";
    private static String font = DEAFAULT_FONT;

    public static final String DEAFAULT_TEXT_CONTENT = "Hello World";
    private static String content = DEAFAULT_TEXT_CONTENT;

    public static final int DEAFAULT_FONT_SIEZE = 10;
    private static int fontSize = DEAFAULT_FONT_SIEZE;

//    StringProperty content = new SimpleStringProperty();



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
    public static void setFont(String font) {
        DrawSettings.font = font;

    }

    public static String getFont(){
        return font;
    }

    public static void setContent(String content){
        DrawSettings.content = content;
    }


    public static String getContent(){
        return content;
    }


    public static void setFontSize(int fontSize) {
        DrawSettings.fontSize = fontSize;

    }

    public static int getFontSize(){
        return fontSize;
    }



}
