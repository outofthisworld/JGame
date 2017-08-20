package display;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Created by SeventhSense on 8/18/2017.
 */
public class Screen implements Display {

    private final int gameWidth;
    private final int gameHeight;

    private final String WINDOW_TITLE;

    private final JFrame mainGameFrame;
    private final Canvas gameCanvas;


    public Screen(int width, int height) {
        this("", width, height);
    }

    public Screen(String title, int width, int height) {
        WINDOW_TITLE = title;
        this.gameWidth = width;
        this.gameHeight = height;
        mainGameFrame = new JFrame();
        gameCanvas = new Canvas();
        gameCanvas.setVisible(true);
        mainGameFrame.setResizable(false);
        mainGameFrame.setTitle(WINDOW_TITLE);
        Dimension d = new Dimension(gameWidth, gameHeight);
        gameCanvas.setSize(d);
        gameCanvas.setMinimumSize(d);
        gameCanvas.setMinimumSize(d);
        mainGameFrame.setMaximumSize(d);
        mainGameFrame.setPreferredSize(d);
        mainGameFrame.setMinimumSize(d);
        mainGameFrame.setLocationRelativeTo(null);
        mainGameFrame.setVisible(true);
        mainGameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainGameFrame.add(gameCanvas);
        gameCanvas.createBufferStrategy(3);
    }

    public JFrame getFrame() {
        return this.mainGameFrame;
    }

    public Canvas getCanvas() {
        return this.gameCanvas;
    }

    public BufferStrategy getBufferStrategy() {
        return getCanvas().getBufferStrategy();
    }

    public Graphics2D getDrawGraphics() {
        BufferStrategy bs;
        if ((bs = getBufferStrategy()) == null) return null;
        Graphics2D graphics = (Graphics2D) bs.getDrawGraphics();
        return graphics;
    }

    public void render() {
        BufferStrategy b = getBufferStrategy();
        if (b == null || b.contentsLost()) return;
        b.show();
    }

    public int getWidth() {
        return gameWidth;
    }

    public int getHeight() {
        return gameHeight;
    }
}
