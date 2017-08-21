package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by SeventhSense on 8/21/2017.
 */
public class MouseManager implements MouseListener {


    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getX());
        System.out.println(e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
