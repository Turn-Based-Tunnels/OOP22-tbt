package it.tbt.model.items;

/**
 * Antidote, should remove the POISONED status.
 */
public class Antidote extends ItemImpl implements Consumable {

    /**
     * Default constructor.
     * @param value
     */
    public Antidote(final int value) {
        super(value);
    }
}
