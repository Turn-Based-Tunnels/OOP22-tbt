package it.tbt.model.entities.items;

/**
 * Generic item.
 */
public interface Item {

    /**
     * Return the item name.
     * @return name
     */
    String getName();

    /**
     * Return the monetary value of the item.
     * @return item's value
     */
    int getValue();
}
