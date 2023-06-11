package it.tbt.model.entities;

/**
 * Generic entity implementation.
 */
public abstract class EnemyImpl implements Entity {
    private final String name;

    /**
     * Default constructor.
     * @param name
     */
    protected EnemyImpl(final String name) {
        this.name = name;
    }

    /**
     * Return the entity name.
     * @return the entity name
     */
    @Override
    public String getName() {
        return name;
    }
}
