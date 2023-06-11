package it.tbt.model.entities.items;

/**
 * Generic item.
 */
class ItemImpl implements Item {
    private final String name;
    private final int value;

    /**
     * Item constructor.
     * @param name
     * @param value     the value of the item
     */
    protected ItemImpl(final String name, final int value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Return the item name.
     * @return name
     */
    @Override
    public String getName() {
        return name;
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
