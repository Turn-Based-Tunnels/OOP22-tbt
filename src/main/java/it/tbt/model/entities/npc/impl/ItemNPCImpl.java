package it.tbt.model.entities.npc.impl;

import it.tbt.model.entities.SpatialEntity;
import it.tbt.model.entities.items.Item;
import it.tbt.model.party.IParty;

import java.util.Map;

/**
 * The {@code ItemNPCImpl} class is an implementation of the {@link it.tbt.model.entities.npc.api.ItemNPC} interface.
 * It extends the {@link AbstractNPCImpl} class and represents an NPC that provides items to the player's party.
 */
public class ItemNPCImpl extends AbstractNPCImpl implements it.tbt.model.entities.npc.api.ItemNPC {

    private final Map<Item, Integer> items;

    /**
     * Constructs a new instance of the ItemNPCImpl class with the specified name, position, dimensions, and items.
     *
     * @param name   the name of the item NPC
     * @param x      the X coordinate of the item NPC's position
     * @param y      the Y coordinate of the item NPC's position
     * @param height the height of the item NPC
     * @param width  the width of the item NPC
     * @param items  the map of items provided by the NPC and their quantities
     * @throws IllegalArgumentException if name is null or empty, or if items is null
     */
    public ItemNPCImpl(String name, int x, int y, int height, int width, Map<Item, Integer> items) {
        super(name, x, y, height, width);
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (items == null) {
            throw new IllegalArgumentException("Items cannot be null");
        }
        this.items = items;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Item, Integer> getItems() {
        return items;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onInteraction(SpatialEntity interactable) {
        if (interactable instanceof IParty) {
            for (Map.Entry<Item, Integer> entry : items.entrySet()) {
                while (entry.getValue() > 0) {
                    ((IParty) interactable).addItemToInventory(entry.getKey());
                    entry.setValue(entry.getValue() - 1);
                }
            }
            items.clear();
        }
    }
}