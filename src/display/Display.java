package display;

import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Created by SeventhSense on 8/20/2017.
 */
public interface Display {
    /**
     * Gets buffer strategy.
     *
     * @return the buffer strategy
     */
    public BufferStrategy getBufferStrategy();

    /**
     * Gets draw graphics.
     *
     * @return the draw graphics
     */
    public Graphics2D getDrawGraphics();

    /**
     * Render.
     */
    public void render();
}
