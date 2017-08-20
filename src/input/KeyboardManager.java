package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by SeventhSense on 8/18/2017.
 */
public class KeyboardManager implements KeyListener {

    private static final Map<Integer, Boolean> keyMap = new HashMap<>();

    /**
     * Is up arrow pressed boolean.
     *
     * @return the boolean
     */
    public static boolean isUpArrowPressed() {
        return keyMap.containsKey(KeyEvent.VK_UP);
    }

    /**
     * Is down arrow pressed boolean.
     *
     * @return the boolean
     */
    public static boolean isDownArrowPressed() {
        return keyMap.containsKey(KeyEvent.VK_DOWN);
    }

    /**
     * Is left arrow pressed boolean.
     *
     * @return the boolean
     */
    public static boolean isLeftArrowPressed() {
        return keyMap.containsKey(KeyEvent.VK_LEFT);
    }

    /**
     * Is right arrow pressed boolean.
     *
     * @return the boolean
     */
    public static boolean isRightArrowPressed() {
        return keyMap.containsKey(KeyEvent.VK_RIGHT);
    }

    /**
     * Is any arrow pressed boolean.
     *
     * @return the boolean
     */
    public static boolean isAnyArrowPressed() {
        return isDownArrowPressed() || isUpArrowPressed() || isLeftArrowPressed() || isRightArrowPressed();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyMap.put(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyMap.remove(e.getKeyCode());
    }
}
