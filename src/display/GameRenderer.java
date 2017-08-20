package display;

import sprites.Sprite;

import java.awt.*;
import java.util.Objects;

/**
 * Created by SeventhSense on 8/20/2017.
 */
public class GameRenderer {
    private final Display display;

    /**
     * Instantiates a new Game renderer.
     *
     * @param screen the screen
     */
    public GameRenderer(Display screen) {
        Objects.requireNonNull(screen);
        this.display = screen;
    }


    /**
     * Render sprite.
     *
     * @param sprite the sprite
     * @param x      the x
     * @param y      the y
     */
    public void renderSprite(Sprite sprite, int x, int y) {
        Graphics2D g = display.getDrawGraphics();
        g.drawImage(sprite.getSpriteImage(), x, y, sprite.getWidth(), sprite.getHeight(), null);
    }


    /**
     * Gets graphics.
     *
     * @return the graphics
     */
    public Graphics2D getGraphics() {
        return display.getDrawGraphics();
    }

    /**
     * Render.
     */
    public void render() {
        display.render();
    }
}
