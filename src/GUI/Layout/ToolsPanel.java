package GUI.Layout;

import GUI.DrawSettings.DrawSettings;
import GUI.Tools.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;


public class ToolsPanel extends VBox {

    public static String PEN_ICON = "../images/pen.png";
    public static String ERASER_ICON = "../images/erase.png";
    public static String RECT_ICON = "../images/rectangle.png";
    public static String OVAL_ICON = "../images/oval.png";
    public static String TEXT_ICON = "../images/text.png";
    public static String CIRCLE_ICON = "../images/circle.png";
    public static String LINE_ICON = "../images/line.png";
    public static String UNDO_ICON = "../images/undo.png";
    public static String FILLED_ICON = "../images/filled.png";
    public static String UNFILLED_ICON = "../images/unfilled.png";
    public static String RESIZE_ICON = "../images/resize.png";


    public static int BTN_SIZE = 25;

    private Button penBtn;
    private Button eraserBtn;
    private Button rectBtn;
    private Button ovalBtn;
    private Button textBtn;
    private Button circleBtn;
    private Button lineBtn;
    private Button resizeBtn;


    private Tool tools;
    private CanvasArea canvasArea;

    HBox hbox1 = new HBox();
    HBox hbox2 = new HBox();


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
        circleBtn = new Button();
        lineBtn = new Button();
        resizeBtn = new Button();

        getChildren().add(hbox1);
        getChildren().add(hbox2);




        Image img = new Image(getClass().getResourceAsStream(RESIZE_ICON), 30, 30, true, true);
        ImageView imgView = new ImageView(img);
        resizeBtn.setGraphic(imgView);
        resizeBtn.setPrefSize(BTN_SIZE, BTN_SIZE);

        Image undo_img = new Image(getClass().getResourceAsStream(UNDO_ICON),60, 30, true, true);
        Button undoBtn = new Button("Undo", new ImageView(undo_img));
        undoBtn.setStyle(" -fx-base: #b6e7c9;");

        undoBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                canvasArea.getModel().undo();
            }
        });

        Image filled = new Image(getClass().getResourceAsStream(FILLED_ICON),60, 30, true, true);
        Image unfilled = new Image(getClass().getResourceAsStream(UNFILLED_ICON),60, 30, true, true);

        Button isFilled = new Button("UnFilled", new ImageView(unfilled));

        isFilled.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                if(DrawSettings.IS_FIILED == true){
                    DrawSettings.IS_FIILED = false;
                    isFilled.setGraphic(new ImageView(unfilled));
                    isFilled.setText("UnFilled");
                }
                else
                {
                    DrawSettings.IS_FIILED = true;
                isFilled.setGraphic(new ImageView(filled));
                    isFilled.setText("Filled");


            }}
        });

        Pane spacePane = new Pane();
        HBox.setHgrow(spacePane, Priority.ALWAYS);

        hbox2.getChildren().add(undoBtn);
        hbox2.getChildren().add(isFilled);
        hbox2.getChildren().add(spacePane);
        hbox2.getChildren().add(resizeBtn);



        createBtn(eraserBtn, ERASER_ICON);
        createBtn(penBtn, PEN_ICON);
        createBtn(lineBtn,LINE_ICON);
        createBtn(rectBtn,RECT_ICON);
        createBtn(circleBtn,CIRCLE_ICON);
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
        hbox1.getChildren().add(btn);
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

        lineBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                tools.setTool(new Line(canvasArea));
                setActive(lineBtn);
            }
        });

        // Change the tool to the eraser when clicked.


        // Change the tool to the rect when clicked.

       rectBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                tools.setTool(new Rectangular(canvasArea));
                setActive(rectBtn);
            }
        });

        circleBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                tools.setTool(new Circle(canvasArea));
                setActive(circleBtn);
            }
        });

        // Change the tool to the oval when clicked.
        ovalBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                tools.setTool(new Oval(canvasArea));
                setActive(ovalBtn);
            }
        });

        textBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                tools.setTool(new Text(canvasArea));
                setActive(textBtn);
            }
        });

        eraserBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                tools.setTool(new Eraser(canvasArea));
                setActive(eraserBtn);
            }
        });

        resizeBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                tools.setTool(new Select(canvasArea));
                setActive(resizeBtn);
            }
        });


    }


    private void setActive(Button btn) {
        // Show the active button.
        btn.setBorder(new Border(new BorderStroke(Color.CORNFLOWERBLUE, BorderStrokeStyle.SOLID, null, null)));

        // Deactivate the rest of the buttons.
        for (Node n : hbox1.getChildren()) {
            if (!n.equals(btn)) {
                ((Button) n).setBorder(null);
            }
            if(!btn.equals(resizeBtn)){
                resizeBtn.setBorder(null);
            }
        }
    }

}
