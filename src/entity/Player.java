package entity;

import display.GameRenderer;
import game.Game;
import input.KeyboardManager;
import math.Vector2D;
import sprites.Sprite;
import sprites.SpriteSheet;

/**
 * Created by SeventhSense on 8/18/2017.
 */
public class Player extends Entity {

    private final SpriteSheet playerSpriteSheet;
    private Vector2D velocity = new Vector2D(0, 0);
    private Vector2D lastVelocity = new Vector2D(0, 0);
    private Sprite drawSprite;
    /*
        The movement speed of this character, in pixels per second.
    */
    private int movementSpeed = 100;


    /**
     * Instantiates a new Player.
     *
     * @param v the v
     */
    public Player(Vector2D v) {
        this.getPosition().become(v);
        playerSpriteSheet = Game.get().getSpriteSheetManager().getSpriteSheet("spritesheets.testl");
    }

    /**
     * Instantiates a new Player.
     *
     * @param x the x
     * @param y the y
     */
    public Player(int x, int y) {
        this(new Vector2D(x, y));
    }

    /**
     * Instantiates a new Player.
     */
    public Player() {
        this(22, 22);
    }


    @Override
    public void render(GameRenderer g) {
        updateDrawSprite();
        g.renderSprite(drawSprite, (int) getPosition().getX(), (int) getPosition().getY());
    }

    private void updateVelocity(double deltaT) {
        if (!KeyboardManager.isAnyArrowPressed()) {
            velocity.setX(0).setY(0);
        }
        if (KeyboardManager.isUpArrowPressed())
            velocity.setXY(0, -movementSpeed).multXY(deltaT);

        if (KeyboardManager.isDownArrowPressed())
            velocity.setXY(0, movementSpeed).multXY(deltaT);

        if (KeyboardManager.isLeftArrowPressed())
            velocity.setXY(-movementSpeed, 0).multXY(deltaT);

        if (KeyboardManager.isRightArrowPressed())
            velocity.setXY(movementSpeed, 0).multXY(deltaT);

    }

    public Sprite getMoveSprite(int angle) {
        switch (angle) {
            case 0:
                Sprite[] sprites = new Sprite[]{
                        playerSpriteSheet.getSprite(0, 2),
                        playerSpriteSheet.getSprite(1, 2),
                        playerSpriteSheet.getSprite(2, 2),
                        playerSpriteSheet.getSprite(3, 2)
                };
                return sprites[0];
            case 90:
                return playerSpriteSheet.getSprite(0, 0);
            case 180:
                return playerSpriteSheet.getSprite(3, 1);
            case 270:
                return playerSpriteSheet.getSprite(0, 3);
            default:
                System.out.println("angle unknown");
                break;
        }
        return null;
    }

    public Sprite getStandingSprite(int angle) {
        switch (angle) {
            case 0:
                return playerSpriteSheet.getSprite(3, 2);
            case 90:
                return playerSpriteSheet.getSprite(0, 0);
            case 180:
                return playerSpriteSheet.getSprite(3, 1);
            case 270:
                return playerSpriteSheet.getSprite(0, 3);
            default:
                System.out.println("angle unknown");
                break;
        }
        return null;
    }

    private void updateDrawSprite() {
        if (velocity.magnitude() > 0) {
            drawSprite = getMoveSprite((int) velocity.getAngleDegreesNormalized());
            lastVelocity = velocity.copy();
        } else if (lastVelocity != null) {
            drawSprite = getStandingSprite((int) lastVelocity.getAngleDegreesNormalized());
        }
    }

    @Override
    public void update(double deltaT) {
        updateVelocity(deltaT);

        this.getPosition().add(velocity);

        if (drawSprite != null)
            getPosition().clampInclusive(0, Game.get().getGameWidth() - drawSprite.getWidth() - 20, 0, Game.get().getGameHeight() - drawSprite.getHeight() - 30);
    }
}
