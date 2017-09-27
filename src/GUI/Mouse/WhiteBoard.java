package GUI.Mouse;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Color;

public class WhiteBoard extends JLabel {
    Dimension minSize = new Dimension(100, 50);

    public WhiteBoard(Color color) {
        setBackground(color);
        setOpaque(true);
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public Dimension getMinimumSize() {
        return minSize;
    }

    public Dimension getPreferredSize() {
        return minSize;
    }
}