package GUI.Tools;

import Data.CanvasStatus;
import GUI.Layout.CanvasArea;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

/**
 * Created by luanboheng on 03/10/2017.
 */
public class Selected extends FreeDraw{

    ImageCursor horizontal_curser = new ImageCursor(new Image(getClass().getResourceAsStream("../images/horizontal.png"), 30, 30, true, true),15,15);
    ImageCursor vertical_curser = new ImageCursor(new Image(getClass().getResourceAsStream("../images/vertical.png"), 30, 30, true, true),15,15);
    ImageCursor move_curser = new ImageCursor(new Image(getClass().getResourceAsStream("../images/move.png"), 30, 30, true, true),15,15);
    ImageCursor resize_curser = new ImageCursor(new Image(getClass().getResourceAsStream("../images/resize_curser.png"), 30, 30, true, true),15,15);
    int PixType;
    static int PixType0;

    void updatePixType0(int PixType){
        PixType0 = PixType;
    }


    @Override
    public void onMouseDown(MouseEvent e) {
        super.onMouseDown(e);
        coord = getCoord(e);
        PixType = model.getLocationStatus(coord);
        if (PixType == CanvasStatus.FAR_FROM_SELECTED_AREA ){
            model.unselectArea();
            getCanvas().tools.setTool(new Select(getCanvas()));
        }
    }

    public Selected(CanvasArea canvasArea) {
        super(canvasArea);
        getCanvas().setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                coord = getCoord(e);
                PixType = model.getLocationStatus(coord);
                System.out.print("Selected"+coord + PixType +"\n");
                if (PixType != PixType0) {
                    updatePixType0(PixType);
                    System.out.println("change pix type");
                    if (PixType == CanvasStatus.HORIZONTALLY_NEAR) {
                        getCanvas().tools.setTool(new Resize(getCanvas(), PixType));
                        getCanvas().setCursor(horizontal_curser);

                    }
                    if (PixType == CanvasStatus.VERTICALLY_NEAR) {
                        getCanvas().tools.setTool(new Resize(getCanvas(), PixType));
                        getCanvas().setCursor(vertical_curser);

                    }
                    if (PixType == CanvasStatus.JUST_NEAR) {
                        getCanvas().tools.setTool(new Resize(getCanvas(), PixType));
                        getCanvas().setCursor(resize_curser);

                    }
                    if (PixType == CanvasStatus.INSIDE_SELECTED_AREA) {
                        getCanvas().tools.setTool(new Move(getCanvas()));
                        getCanvas().setCursor(move_curser);

                    }
                    if (PixType == CanvasStatus.FAR_FROM_SELECTED_AREA) {
                        getCanvas().tools.setTool(new Selected(getCanvas()));
                        getCanvas().setCursor(Cursor.DEFAULT);

                    }
                }

            }

        });



    }

}
