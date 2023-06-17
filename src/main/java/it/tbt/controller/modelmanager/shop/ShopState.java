package it.tbt.controller.modelmanager.shop;

import java.util.List;

import it.tbt.controller.modelmanager.ModelState;

/**
 * Shop state.
 */
public interface ShopState extends ModelState {
    void nextElement();

    void previousElement();

    int getFocus();

    List<ShopItem> getPartyItems();

    List<ShopItem> getShopItems();

    void execute();

    /**
     * Switch between the party inventory and the shop inventory.
     */
    void toggleList();

    void toExplore();
}
