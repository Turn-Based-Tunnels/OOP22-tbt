package it.tbt.model.entities.items;

/**
 * Generic Armor.
 */
public abstract class Armor extends ItemImpl implements Equipement {
    private final int defence;

    /**
     * Default constructor.
     * @param defence
     * @param value
     */
    public Armor(final int defence, final int value) {
        super(value);
        this.defence = defence;
    }

    /**
     * get the defence of the armor.
     * @return int
     */
    public final int getDefence() {
        return defence;
    }
}
