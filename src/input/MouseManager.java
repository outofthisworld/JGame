package input;

import game.Game;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

/**
 * Created by SeventhSense on 8/21/2017.
 */
public class MouseManager implements MouseListener {

    private static HashMap<Integer, MouseEvent> mouseClicked = new HashMap<>();

    private static Point getRelativeMouseCoordinates(int button) {
        MouseEvent e;
        if ((e = mouseClicked.get(button)) != null) {
            return e.getPoint();
        }
        return null;
    }

    private static Point getScreenMouseCoordinates(int button) {
        MouseEvent e;
        if ((e = mouseClicked.get(button)) != null) {
            return new Point(e.getXOnScreen(), e.getYOnScreen());
        }
        return null;
    }

    public static Point getPrimaryMouseContainerCoordinates() {
        return getRelativeMouseCoordinates(MouseEvent.BUTTON1);
    }

    public static Point getSecondaryMouseContainerCoordinates() {
        return getRelativeMouseCoordinates(MouseEvent.BUTTON2);
    }

    public static Point getMiddleMouseContainerCoordinates() {
        return getRelativeMouseCoordinates(MouseEvent.BUTTON3);
    }

    public static Point getPrimaryMouseScreenCoordinates() {
        return getScreenMouseCoordinates(MouseEvent.BUTTON1);
    }

    public static Point getSecondaryMouseScreenCoordinates() {
        return getScreenMouseCoordinates(MouseEvent.BUTTON2);
    }

    public static Point getMiddleMouseScreenCoordinates() {
        return getScreenMouseCoordinates(MouseEvent.BUTTON3);
    }

    public static boolean isMiddleMouseButtonDown() {
        return mouseClicked.containsKey(MouseEvent.BUTTON3);
    }

    public static boolean isSecondaryMouseButtonDown() {
        return mouseClicked.containsKey(MouseEvent.BUTTON2);
    }

    public static boolean isPrimaryMouseButtonDown() {
        return mouseClicked.containsKey(MouseEvent.BUTTON1);
    }

    public static boolean isAnyMouseButtonPressed() {
        return mouseClicked.size() > 0;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Game.get().getGameService().execute(() -> mouseClicked.put(e.getButton(), e));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Game.get().getGameService().execute(() -> mouseClicked.remove(e.getButton()));
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
