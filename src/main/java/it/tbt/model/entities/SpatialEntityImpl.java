package it.tbt.model.entities;

/**
 * Generic entity with a position in space.
 */
public abstract class SpatialEntityImpl extends EntityImpl implements SpatialEntity {
    private final int x;
    private final int y;

    /**
     * Default constructor.
     * @param name
     * @param x
     * @param y
     */
    protected SpatialEntityImpl(final String name, final int x, final int y) {
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
}
