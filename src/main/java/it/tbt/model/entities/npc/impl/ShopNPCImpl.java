package it.tbt.model.entities.npc.impl;

import it.tbt.model.entities.SpatialEntity;
import it.tbt.model.party.IParty;
import it.tbt.model.shop.Shop;
import it.tbt.model.statechange.StateObserver;
import it.tbt.model.statechange.StateTrigger;

public class ShopNPCImpl extends AbstractNPCImpl implements it.tbt.model.entities.npc.api.ShopNPC, StateTrigger {
    private Shop shop;
    private AbstractNPCImpl npc;

    private StateObserver stateObserver;

    public ShopNPCImpl (String name, int x, int y, int height, int width, Shop shop) {
        super (name, x, y, height, width);
        this.shop = shop;
    }

    public Shop getShop () {
        return shop;
    }

    @Override
    public void onInteraction (SpatialEntity interactable) {
        if (interactable instanceof IParty) {
            this.stateObserver.onShop (shop);
        }
    }

    @Override
    public void setStateObserver (StateObserver stateObserver) {
        this.stateObserver = stateObserver;
    }
}
