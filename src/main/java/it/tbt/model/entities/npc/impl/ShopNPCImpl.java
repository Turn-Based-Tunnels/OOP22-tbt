package it.tbt.model.entities.npc.impl;

import it.tbt.model.entities.SpatialEntity;

public class ShopNPCImpl extends AbstractNPCImpl implements it.tbt.model.entities.npc.api.ShopNPC {
    private String shop;
    private AbstractNPCImpl npc;

    public ShopNPCImpl(String name, int x, int y ,int height, int width, String shop) {
        super(name, x, y, height, width);
        this.shop = shop;
    }

    public String getShop(){
        return shop;
    }

    @Override
    public void onInteraction(SpatialEntity interactable) {
        /*Todo*/
    }
}
