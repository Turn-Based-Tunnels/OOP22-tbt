package it.tbt.model.entities.items;

/**
 * Generic item.
 */
public class ItemImpl implements Item {
    private final int value;

    /**
     * Item constructor.
     * @param value     the value of the item
     */
    protected ItemImpl(final int value) {
        this.value = value;
    }

    /**
     * Return the monetary value of the item.
     * @return item's value
     */
    @Override
    public final int getValue() {
        return value;
    }
}
