package it.tbt.model.entities.items;

/**
 * Antidote, should remove the POISONED status.
 */
public class Antidote extends ItemImpl implements Consumable {

    /**
     * Default constructor.
     * @param name
     * @param value
     */
    public Antidote(final String name, final int value) {
        super(name, value);
    }
}
