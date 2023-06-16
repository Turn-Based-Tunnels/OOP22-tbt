package it.tbt.model.entities.npc.impl;

import it.tbt.model.entities.SpatialEntity;

public class ShopNPCImpl extends AbstractNPCImpl implements it.tbt.model.entities.npc.api.ShopNPC {
    private String shop;
    private AbstractNPCImpl npc;

    public ShopNPCImpl(String name, String dialogue, String shop) {
        super(name);
        this.shop = shop;
    }

    public String getShop(){
        return shop;
    }

    @Override
    public void OnInteraction(SpatialEntity interactable) {
        /*Todo*/
    }
}
