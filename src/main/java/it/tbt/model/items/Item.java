package it.tbt.model.items;

/**
 * Generic item.
 */
public class Item {
    private final int value;

    /**
     * Item constructor.
     * @param value     the value of the item
     */
    protected Item(final int value) {
        this.value = value;
    }

    /**
     * Return the monetary value of the item.
     * @return item's value
     */
    public final int getValue() {
        return value;
    }
}
