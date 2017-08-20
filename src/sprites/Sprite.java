package sprites;

import java.awt.image.BufferedImage;

/**
 * Created by SeventhSense on 8/19/2017.
 */
public class Sprite {
    private final int width;
    private final int height;
    private final BufferedImage spriteImage;
    private SpriteSheet spriteSheet;

    /**
     * Instantiates a new Sprite.
     *
     * @param bufferedImage the buffered image
     */
    public Sprite(BufferedImage bufferedImage) {
        this(null, bufferedImage);
    }

    /**
     * Instantiates a new Sprite.
     *
     * @param sheet         the sheet
     * @param bufferedImage the buffered image
     */
    public Sprite(SpriteSheet sheet, BufferedImage bufferedImage) {
        this.width = bufferedImage.getWidth();
        this.height = bufferedImage.getHeight();
        this.spriteImage = bufferedImage;
        this.spriteSheet = sheet;
    }

    /**
     * Gets sprite sheet.
     *
     * @return the sprite sheet
     */
    public SpriteSheet getSpriteSheet() {
        return spriteSheet;
    }

    /**
     * Gets sprite image.
     *
     * @return the sprite image
     */
    public BufferedImage getSpriteImage() {
        return spriteImage;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Get pixels int [ ].
     *
     * @return the int [ ]
     */
    public int[] getPixels() {
        int[] data = new int[getWidth() * getHeight()];
        spriteImage.getRGB(0, 0, getWidth(), getHeight(), data, 0, getWidth());
        return data;
    }

    /**
     * Copy sprite.
     *
     * @return the sprite
     */
    public Sprite copy() {
        return new Sprite(spriteSheet, spriteImage);
    }

    /**
     * Copy with new underlying image sprite.
     *
     * @return the sprite
     */
    public Sprite copyWithNewUnderlyingImage() {
        return new Sprite(spriteSheet, spriteImage.getSubimage(0, 0, getWidth(), getHeight()));
    }
}