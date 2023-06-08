package it.tbt.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import it.tbt.model.items.Item;

/**
 * Character's inventory.
 */
public class Inventory {
    private final List<Item> items;

    /**
     * Default/empty Constructor.
     */
    public Inventory() {
        items = new ArrayList<>();
    }

    /**
     * Create a new inventory based on the given collection of items.
     * @param newItems
     */
    public Inventory(final Collection<Item> newItems) {
        items = new ArrayList<>(newItems);
    }

    /**
     * Get inventory contents as a list of items.
     * @return list of items in the inventory
     */
    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }

    /**
     * Add an item to the inventory.
     * @param item
     */
    public void addItem(final Item item) {
        items.add(item);
    }

    /**
     * Remove an item from the inventory.
     * @param item
     * @return true if the item was found and removed
     */
    public boolean removeItem(final Item item) {
        final int index = items.indexOf(item);
        if (index > 0) {
            items.remove(index);
            return true;
        } else {
            return false;
        }
    }
}
