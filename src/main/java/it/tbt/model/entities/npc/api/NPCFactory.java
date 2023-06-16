package it.tbt.model.entities.npc.api;

import it.tbt.model.entities.items.Item;
import it.tbt.model.entities.npc.impl.*;

import java.util.Map;

public interface NPCFactory {
    public static NPC createStandardNPC(String name, String dialogue) {
        return new DialogueNPCImpl(name, dialogue);
    }

    public static NPC createItemNPC(String name, String dialogue, Map<Item, Integer> items){
        return new ItemNPCImpl(name, dialogue, items);
    }

    public static NPC createShopNPC(String name, String dialogue, String shop){
        return new ShopNPCImpl(name, dialogue, shop);
    }

    public static NPC HealerNPC(String name, String dialogue,  int healAmount){
        return new HealerNPCImpl(name, dialogue, healAmount);
    }
}
