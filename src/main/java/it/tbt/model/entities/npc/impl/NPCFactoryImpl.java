package it.tbt.model.entities.npc.impl;

import it.tbt.model.entities.items.Item;
import it.tbt.model.entities.npc.api.NPC;

import java.util.Map;

public class NPCFactoryImpl {

    public static NPC createDialogueNPC(String name, String dialogue) {
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
