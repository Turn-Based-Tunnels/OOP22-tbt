package it.tbt.model.statechange;

import it.tbt.model.fight.api.FightModel;
import it.tbt.model.shop.Shop;

/**
 * Interface implemented by the object who wants to be able to handle GameState transitions and define an appropriate response.
 */
public interface StateObserver {

    /**
     * Defines the action to be taken when the Explore GameState is to be triggered.
     */
    void onExplore();
    /**
     * Defines the action to be taken when the Main menu GameState is to be triggered.
     */
    void onMenu();
    /**
     * Defines the action to be taken when the Pause menu GameState is to be triggered.
     */
    void onPause();
    /**
     * Defines the action to be taken when the Fight GameState is to be triggered.
     */
    void onFight(FightModel fightModel);

    void onInventory();

    /**
     * Defines the action to be taken when the Shop GameState is to be triggered.
     * @param shop
     */
    void onShop(Shop shop);
}
