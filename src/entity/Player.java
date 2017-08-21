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
        if (KeyboardManager.isUpArrowPressed())
            velocity.setY(-movementSpeed).setX(0).multXY(deltaT);

        if (KeyboardManager.isDownArrowPressed())
            velocity.setY(movementSpeed).setX(0).multXY(deltaT);

        if (KeyboardManager.isLeftArrowPressed())
            velocity.setX(-movementSpeed).setY(0).multXY(deltaT);

        if (KeyboardManager.isRightArrowPressed())
            velocity.setX(movementSpeed).setY(0).multXY(deltaT);

        if (!KeyboardManager.isAnyArrowPressed())
            velocity.setX(0).setY(0);
    }

    private Sprite getSpriteForAngle(int angle) {
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
            int angleNormalize = (int) velocity.getAngleDegreesNormalized();
            drawSprite = getSpriteForAngle(angleNormalize);
            lastVelocity = velocity.copy();
        } else if (lastVelocity != null) {
            int angleNormalize = (int) lastVelocity.getAngleDegreesNormalized();
            drawSprite = getSpriteForAngle(angleNormalize);
        }
    }

    @Override
    public void update(double deltaT) {
        updateVelocity(deltaT);
        this.getPosition().add(velocity);
    }


}
