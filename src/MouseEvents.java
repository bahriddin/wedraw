import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class MouseEvents implements MouseListener,MouseMotionListener {
    JTextArea textArea = MouseEventDemo.textArea;
    static final String NEWLINE = System.getProperty("line.separator");
    void eventOutput(String eventDescription, MouseEvent e) {
        textArea.append(eventDescription + " detected on "
                + e.getComponent().getClass().getName()
                + "."+"<"+e.getX()+","+e.getY()+">" + NEWLINE);
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    public void mousePressed(MouseEvent e) {
        eventOutput("Mouse pressed (# of clicks: "
                + e.getClickCount() + ")", e);
    }

    public void mouseReleased(MouseEvent e) {
        eventOutput("Mouse released (# of clicks: "
                + e.getClickCount() + ")", e);
    }

    public void mouseEntered(MouseEvent e) {
        eventOutput("Mouse entered", e);
    }

    public void mouseExited(MouseEvent e) {
        eventOutput("Mouse exited", e);
    }

    public void mouseClicked(MouseEvent e) {
        eventOutput("Mouse clicked (# of clicks: "
                + e.getClickCount() + ")", e);
    }

    void eventOutputMotion(String eventDescription, MouseEvent e) {
        textArea.append(eventDescription
                + " (" + e.getX() + "," + e.getY() + ")"
                + " detected on "
                + e.getComponent().getClass().getName()
                + NEWLINE);
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    public void mouseMoved(MouseEvent e) {
        eventOutputMotion("Mouse moved", e);
    }

    public void mouseDragged(MouseEvent e) {
        eventOutputMotion("Mouse dragged", e);
    }
}
