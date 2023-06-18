package it.tbt.model.entities.npc.impl;

import it.tbt.model.entities.SpatialEntity;
import it.tbt.model.party.IParty;
import it.tbt.model.shop.Shop;
import it.tbt.model.statechange.StateObserver;
import it.tbt.model.statechange.StateTrigger;

/**
 * The {@code ShopNPCImpl} class represents an implementation of a shop NPC.
 */
public class ShopNPCImpl extends AbstractNPCImpl implements it.tbt.model.entities.npc.api.ShopNPC, StateTrigger {


    private final Shop shop;
    private StateObserver stateObserver;

    /**
     * Creates a shop NPC with the specified name, position, dimensions, and shop.
     *
     * @param name   the name of the shop NPC
     * @param x      the X coordinate of the NPC's position
     * @param y      the Y coordinate of the NPC's position
     * @param height the height of the NPC
     * @param width  the width of the NPC
     * @param shop   the shop associated with the NPC
     */
    public ShopNPCImpl(final String name, final int x, final int y, final int height, final int width, final Shop shop) {
        super(name, x, y, height, width);
        this.shop = shop;
    }

    /**
     * Returns the shop associated with the shop NPC.
     *
     * @return the shop associated with the shop NPC
     */
    public Shop getShop() {
        return shop;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onInteraction(final SpatialEntity interactable) {
        if (interactable instanceof IParty) {
            this.stateObserver.onShop(shop);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setStateObserver(final StateObserver stateObserver) {
        this.stateObserver = stateObserver;
    }
}
