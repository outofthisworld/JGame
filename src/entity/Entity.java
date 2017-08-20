package entity;

import math.Vector2D;

/**
 * Created by SeventhSense on 8/18/2017.
 */
public abstract class Entity implements GameObject {

    private final Vector2D position = new Vector2D(0, 0);


    /**
     * Gets position.
     *
     * @return the position
     */
    public Vector2D getPosition() {
        return this.position;
    }
}
