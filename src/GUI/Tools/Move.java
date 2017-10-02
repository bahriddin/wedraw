package GUI.Tools;

import Data.CanvasStatus;
import Data.Coord;
import GUI.DrawSettings.DrawSettings;
import GUI.Layout.CanvasArea;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


import java.awt.*;
import java.io.File;


public class Move extends FreeDraw{


    ImageCursor horizontal_curser = new ImageCursor(new Image(getClass().getResourceAsStream("../images/horizontal.png"), 30, 30, true, true),15,15);
    ImageCursor vertical_curser = new ImageCursor(new Image(getClass().getResourceAsStream("../images/vertical.png"), 30, 30, true, true),15,15);
   ImageCursor move_curser = new ImageCursor(new Image(getClass().getResourceAsStream("../images/move.png"), 30, 30, true, true),15,15);
    ImageCursor resize_curser = new ImageCursor(new Image(getClass().getResourceAsStream("../images/resize_curser.png"), 30, 30, true, true),15,15);




    public Move(CanvasArea canvasArea) {
        super(canvasArea);
        this.getCanvas().setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                coord = getCoord(e);
                if (model.getLocationStatus(coord) == CanvasStatus.HORIZONTALLY_NEAR ){
                    getCanvas().setCursor(horizontal_curser);
                }
                if (model.getLocationStatus(coord) == CanvasStatus.VERTICALLY_NEAR ){
                    getCanvas().setCursor(vertical_curser);
                }
                if (model.getLocationStatus(coord) == CanvasStatus.JUST_NEAR ){
                    getCanvas().setCursor(resize_curser);
                }
                if (model.getLocationStatus(coord) == CanvasStatus.INSIDE_SELECTED_AREA ){
                    getCanvas().setCursor(move_curser);


                }
                if (model.getLocationStatus(coord) == CanvasStatus.FAR_FROM_SELECTED_AREA ){
                    getCanvas().setCursor(Cursor.DEFAULT);
                }

            }

        });
    }

    @Override
    void startDraw() {
        this.model.startMoveArea(coord);
    }

    @Override
    void continueDraw() {
        this.model.continueMoveArea(coord);
    }
    @Override
    void endDraw() {
        this.model.stopMoveArea(coord);

    }

}


