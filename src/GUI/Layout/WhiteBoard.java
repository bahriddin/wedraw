package GUI.Layout;

import GUI.Tools.FreeDraw;
import GUI.Tools.Rectangular;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import GUI.Tools.Pen;
import GUI.Tools.Tool;
import javafx.scene.paint.Color;

import static GUI.DrawSettings.DrawSettings.color;

public class WhiteBoard extends BorderPane {

    VBox top;
    HBox bottom;
    Tool tools;
    CanvasArea canvasArea;

    public static final Color PANE_COLOR = Color.rgb(180, 200, 220);


    public WhiteBoard() {
        // Setup canvas
        tools = new Tool();
        
        canvasArea = new CanvasArea(tools);

        canvasArea.setStyle("-fx-background-color: white");

        // Setup tools

        tools.setTool(new Pen(canvasArea));

        // Setup top/bottom panels.
        setupToolBar();
        setupFooterBar();

        // Setup the border layout.
        setCenter(canvasArea);
        setTop(top);
        setBottom(bottom);
    }

    private void setupToolBar() {
        top = new VBox();
        HBox topControls = new HBox();
        ToolsPanel toolsPanel = new ToolsPanel(tools, canvasArea);
        TextPanel textPanel = new TextPanel();
        PenWidthPanel widthPanel = new PenWidthPanel();
        ColorPalette colorPalette = new ColorPalette();
        textPanel.setPrefWidth(200);

        // Setup some styling.
        top.setBackground(new Background(new BackgroundFill(PANE_COLOR,null, null)));
        topControls.setSpacing(10);
        topControls.setPadding(new Insets(5));
//        HBox.setHgrow(toolsPanel, Priority.ALWAYS);

        // Add children to top controls panel.
        topControls.getChildren().add(toolsPanel);
        topControls.getChildren().add(textPanel);
        topControls.getChildren().add(widthPanel);
        topControls.getChildren().add(colorPalette);

        top.getChildren().add(new WhiteBoardMenu());
        top.getChildren().add(topControls);
    }

    private void setupFooterBar() {
        bottom = new HBox();

        // Create a spacer.
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // Add the components.
        bottom.setBackground(new Background(new BackgroundFill(PANE_COLOR, null, null)));
        bottom.getChildren().add(spacer);
        bottom.getChildren().add(new CoordsPanel(canvasArea));
    }

    }


