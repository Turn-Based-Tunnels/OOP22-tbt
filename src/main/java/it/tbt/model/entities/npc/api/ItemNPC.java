package it.tbt.model.entities.npc.api;

import it.tbt.model.entities.items.Item;

import java.util.Map;

public interface ItemNPC extends NPC {
    public Map<Item, Integer> getItems();
}
