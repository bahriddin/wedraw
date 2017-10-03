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


public class Move extends Selected{


    public Move(CanvasArea canvasArea) {
        super(canvasArea);
    }

    @Override
    public void startDraw() {
        this.model.startMoveArea(coord);
    }

    @Override
    public void continueDraw() {
        this.model.continueMoveArea(coord);
    }
    @Override
    public void endDraw() {
        this.model.stopMoveArea(coord);
    }

    @Override
    public String toString() {
        return "Move";
    }
}


