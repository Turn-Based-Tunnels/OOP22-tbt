package it.tbt.model.entities.npc.impl;


import it.tbt.model.entities.SpatialEntity;
import it.tbt.model.entities.items.Item;
import it.tbt.model.party.IParty;

import java.util.Map;

public class ItemNPCImpl extends AbstractNPCImpl implements it.tbt.model.entities.npc.api.ItemNPC {
    private Map<Item, Integer> items;

    public ItemNPCImpl(String name, String dialogue, Map<Item, Integer> items) {
        super(name);
        this.items = items;
    }

    @Override
    public Map<Item, Integer> getItems(){
        return items;
    }

    @Override
    public void OnInteraction(SpatialEntity interactable) {
        if(interactable instanceof IParty){
            for (Map.Entry<Item, Integer> entry : items.entrySet()) {
                for(;entry.getValue() > 0;){
                    ((IParty)interactable).addItemToInventory(entry.getKey());
                    entry.setValue(entry.getValue()-1);
                }
            }
            items.clear();
        }
    }
}

