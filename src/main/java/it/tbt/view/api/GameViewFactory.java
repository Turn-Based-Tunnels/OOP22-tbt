package it.tbt.view.api;

import it.tbt.controller.modelmanager.*;
import it.tbt.controller.viewcontrollermanager.api.ViewController;
import it.tbt.controller.modelmanager.shop.ShopStateImpl;
import it.tbt.controller.viewcontrollermanager.impl.EndViewController;
import it.tbt.controller.viewcontrollermanager.impl.ShopController;

/**
 * Interface for a Factory of GameViews. To be implemented with framework
 * specific logic.
 */

public interface GameViewFactory {

    /**
     * @param menuController
     * @param menuState
     * @return the Menu view which renders the menuState data and has InputHandling done by menuController.
     */
    GameView createMenu(ViewController menuController, MenuState menuState);

    /**
     * @param menuController
     * @param menuState
     * @return the Menu view which renders the menuState data and has InputHandling done by menuController.
     */
    GameView createPause(ViewController menuController, MenuState menuState);

    /**
     * @param exploreController the controller to be passed the view
     * @param exploreState the state the view will need to render
     * @return the GameView for the Explore Game State
     */
    GameView createRoom(ViewController exploreController, ExploreState exploreState);
    GameView createShop(ShopController shopControler, ShopStateImpl shopState);

    public GameView createFight(ViewController fightController, FightState fightState);

    GameView createInventory(ViewController inventoryController, InventoryState inventoryState);
    GameView createEndScreen(ViewController endViewController, EndState endState);

}
