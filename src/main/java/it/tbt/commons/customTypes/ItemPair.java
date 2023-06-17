package it.tbt.commons.customTypes;

import it.tbt.model.entities.items.Item;
import javafx.util.Pair;

public class ItemPair extends Pair<Item, Integer> {
    /**
     * Creates a new pair
     *
     * @param key   The key for this pair
     * @param value The value to use for this pair
     */
    public ItemPair(Item key, Integer value) {
        super(key, value);
    }

    @Override
    public String toString(){
        return this.getKey() + "\t\tx" + this.getValue();
    }
}
