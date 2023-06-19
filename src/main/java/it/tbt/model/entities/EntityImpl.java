package it.tbt.model.entities;

import java.util.Objects;

/**
 * Generic entity implementation.
 */
public abstract class EntityImpl implements Entity {
    private final String name;

    /**
     * Default constructor.
     * @param name
     */
    protected EntityImpl(final String name) {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(final Entity entity) {
        return this.getName().compareTo(entity.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityImpl entity = (EntityImpl) o;
        return Objects.equals(name, entity.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
