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
    private Sprite drawSprite;


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
        getDrawSprite();
        g.renderSprite(drawSprite, (int) getPosition().getX(), (int) getPosition().getY());
    }

    private void updateVelocity(double deltaT) {
        if (KeyboardManager.isUpArrowPressed())
            velocity.setY(-1);

        if (KeyboardManager.isDownArrowPressed())
            velocity.setY(1);

        if (KeyboardManager.isLeftArrowPressed())
            velocity.setX(-1);

        if (KeyboardManager.isRightArrowPressed())
            velocity.setX(1);

        if (!KeyboardManager.isAnyArrowPressed())
            velocity.setX(0).setY(0);
    }

    private void getDrawSprite() {

        System.out.println("magnitude 0");
        int angleNormalize = (int) velocity.getAngleDegreesNormalized();
        System.out.println(velocity.getAngleDegrees());
        switch (angleNormalize) {
            case 0:
                drawSprite = playerSpriteSheet.getSprite(3, 2);
                break;
            case 90:
                drawSprite = playerSpriteSheet.getSprite(0, 0);
                break;
            case 180:
                drawSprite = playerSpriteSheet.getSprite(3, 1);
                break;
            case 270:
                drawSprite = playerSpriteSheet.getSprite(0, 3);
                break;
            default:
                System.out.println("angle unknown");
                break;
        }

    }

    @Override
    public void update(double deltaT) {
        updateVelocity(deltaT);
        this.getPosition().add(velocity);
    }


}
