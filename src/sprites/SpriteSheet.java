package sprites;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Objects;

/**
 * Created by SeventhSense on 8/19/2017.
 */
public class SpriteSheet {

    private final BufferedImage bufferedImage;
    private final int SPRITESHEET_WIDTH;
    private final int SPRITESHEET_HEIGHT;
    private final int SPRITE_WIDTH;
    private final int SPRITE_HEIGHT;
    private final HashMap<String, Sprite> spriteCache = new HashMap<>();

    /**
     * Instantiates a new Sprite sheet.
     *
     * @param bi           the bi
     * @param spriteWidth  the sprite width
     * @param spriteHeight the sprite height
     */
    public SpriteSheet(BufferedImage bi, int spriteWidth, int spriteHeight) {
        Objects.requireNonNull(bi);
        if (spriteWidth < 0 || spriteHeight < 0) throw new RuntimeException("spriteWidth && spriteHeight must be > 0");
        this.bufferedImage = bi;
        this.SPRITESHEET_WIDTH = bi.getWidth();
        this.SPRITESHEET_HEIGHT = bi.getHeight();
        this.SPRITE_WIDTH = spriteWidth;
        this.SPRITE_HEIGHT = spriteHeight;
    }

    /**
     * Gets sprite.
     *
     * @param x the x
     * @param y the y
     * @return the sprite
     */
    public Sprite getSprite(int x, int y) {
        return getSprite(x, y, SPRITE_WIDTH, SPRITE_HEIGHT);
    }

    private Sprite checkCache(int x, int y, int width, int height) {
        String cacheKey = "[" + x + "," + y + "," + width + "," + height + "]";
        if (spriteCache.containsKey(cacheKey)) {
            return spriteCache.get(cacheKey);
        }

        return new Sprite(bufferedImage.getSubimage(x * SPRITE_WIDTH, y * SPRITE_HEIGHT, width, height));
    }

    /**
     * Gets sprite.
     *
     * @param x      the x
     * @param y      the y
     * @param width  the width
     * @param height the height
     * @return the sprite
     */
    public Sprite getSprite(int x, int y, int width, int height) {
        return checkCache(x, y, width, height);
    }

    /**
     * Get pixels int [ ].
     *
     * @return the int [ ]
     */
    public int[] getPixels() {
        return bufferedImage.getRGB(0, 0, SPRITESHEET_WIDTH, SPRITESHEET_HEIGHT, null, 0, 1);
    }

    /**
     * Gets image.
     *
     * @return the image
     */
    public BufferedImage getImage() {
        return this.bufferedImage;
    }

    /**
     * Gets sprite sheet width.
     *
     * @return the sprite sheet width
     */
    public int getSpriteSheetWidth() {
        return SPRITESHEET_WIDTH;
    }

    /**
     * Gets sprite sheet height.
     *
     * @return the sprite sheet height
     */
    public int getSpriteSheetHeight() {
        return SPRITESHEET_HEIGHT;
    }

    /**
     * Gets sprite width.
     *
     * @return the sprite width
     */
    public int getSpriteWidth() {
        return SPRITE_WIDTH;
    }

    /**
     * Gets sprite height.
     *
     * @return the sprite height
     */
    public int getSpriteHeight() {
        return SPRITE_HEIGHT;
    }

}
