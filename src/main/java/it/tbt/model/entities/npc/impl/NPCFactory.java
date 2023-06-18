package it.tbt.model.entities.npc.impl;

import it.tbt.model.entities.characters.Ally;
import it.tbt.model.entities.items.Item;
import it.tbt.model.entities.npc.api.NPC;
import it.tbt.model.fight.api.FightModel;
import it.tbt.model.shop.Shop;

import java.util.Map;

public class NPCFactory {

    public static NPC createDialogueNPC (String name, int x, int y, int height, int width, String dialogue) {
        return new DialogueNPCImpl (name, x, y, height, width, dialogue);
    }

    public static NPC createItemNPC (String name, int x, int y, int height, int width, Map<Item, Integer> items) {
        return new ItemNPCImpl (name, x, y, height, width, items);
    }

    public static NPC createShopNPC (String name, int x, int y, int height, int width, Shop shop) {
        return new ShopNPCImpl (name, x, y, height, width, shop);
    }

    public static NPC createHealerNPC (String name, int x, int y, int height, int width, int healAmount) {
        return new HealerNPCImpl (name, x, y, height, width, healAmount);
    }

    public static NPC createFightNPC (String name, int x, int y, int height, int width, FightModel fightModel) {
        return new FightNPCImpl (name, x, y, height, width, fightModel);
    }

    public static NPC createAllyNPC (String name, int x, int y, int height, int width, Ally ally) {
        return new AllyNPCImpl (name, x, y, height, width, ally);
    }

}
