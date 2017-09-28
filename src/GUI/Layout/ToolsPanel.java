package GUI.Layout;

import GUI.Tools.Rectangular;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import GUI.Tools.Pen;
import GUI.Tools.Eraser;
import GUI.Tools.Tool;


public class ToolsPanel extends HBox {

    public static String PEN_ICON = "../images/pen.png";
    public static String ERASER_ICON = "../images/erase.png";
    public static String RECT_ICON = "../images/rectangle.png";
    public static String OVAL_ICON = "../images/oval.png";
    public static String TEXT_ICON = "../images/text.png";

    public static int BTN_SIZE = 25;

    private Button penBtn;
    private Button eraserBtn;
    private Button rectBtn;
    private Button ovalBtn;
    private Button textBtn;

    private Tool tools;
    private CanvasArea canvasArea;


//    Create a ToolsPanel.

    public ToolsPanel(Tool tools, CanvasArea canvas) {
        super();

        // Setup variables.
        this.tools = tools;
        this.canvasArea = canvas;

        // Create the buttons.
        penBtn = new Button();
        eraserBtn = new Button();
        rectBtn = new Button();
        ovalBtn = new Button();
        textBtn = new Button();

        createBtn(penBtn, PEN_ICON);
        createBtn(eraserBtn, ERASER_ICON);
        createBtn(rectBtn,RECT_ICON);
        createBtn(ovalBtn,OVAL_ICON);
        createBtn(textBtn,TEXT_ICON);

        setupButtons();

        // The pen is the initial tool.
        setActive(penBtn);
    }


    private void createBtn(Button btn, String imgLocation) {
        // Create the button
        Image img = new Image(getClass().getResourceAsStream(imgLocation), BTN_SIZE, BTN_SIZE, true, true);
        ImageView imgView = new ImageView(img);
        btn.setGraphic(imgView);
        btn.setPrefSize(BTN_SIZE, BTN_SIZE);

        // Add it to the HBox.
        getChildren().add(btn);
    }


//      Setup the button actions.

    private void setupButtons() {
        // Change the tool to the pen when clicked.
        penBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                tools.setTool(new Pen(canvasArea));
                setActive(penBtn);
            }
        });

        // Change the tool to the eraser when clicked.
        eraserBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                tools.setTool(new Eraser(canvasArea));
                setActive(eraserBtn);
            }

        });

        // Change the tool to the rect when clicked.

       rectBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                tools.setTool(new Rectangular(canvasArea));
                setActive(rectBtn);
            }
        });

        // Change the tool to the oval when clicked.
        ovalBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                tools.setTool(new Pen(canvasArea));
                setActive(ovalBtn);
            }
        });

        textBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                tools.setTool(new Pen(canvasArea));
                setActive(textBtn);
            }
        });

    }


    private void setActive(Button btn) {
        // Show the active button.
        btn.setBorder(new Border(new BorderStroke(Color.CORNFLOWERBLUE, BorderStrokeStyle.SOLID, null, null)));

        // Deactivate the rest of the buttons.
        for (Node n : getChildren()) {
            if (!n.equals(btn)) {
                ((Button) n).setBorder(null);
            }
        }
    }

}
