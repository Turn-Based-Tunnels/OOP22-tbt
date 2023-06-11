package it.tbt.model.entities;

/**
 * Generic entity with a variable position in space.
 */
public class MovableEntityImpl extends EnemyImpl implements MovableEntity {
    private int x;
    private int y;

    /**
     * Default constructor.
     * @param name
     * @param x
     * @param y
     */
    public MovableEntityImpl(final String name, final int x, final int y) {
        super(name);
        this.x = x;
        this.y = y;
    }

    /**
     * Get X coordinate.
     * @return X coordinate
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * Get Y coordinate.
     * @return Y coordinate
     */
    @Override
    public int getY() {
        return y;
    }

    /**
     * Set X coordinate.
     * @param x
     */
    @Override
    public void setX(final int x) {
        this.x = x;
    }

    /**
     * Set Y coordinate.
     * @param y
     */
    @Override
    public void setY(final int y) {
        this.y = y;
    }
}
