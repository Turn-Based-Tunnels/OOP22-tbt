package it.tbt.view.api;

import it.tbt.control.menu.impl.MenuController;
import it.tbt.controller.modelmanager.ExploreState;
import it.tbt.controller.modelmanager.MenuState;
import it.tbt.controller.viewcontrollermanager.api.ExploreController;
import it.tbt.controller.viewcontrollermanager.api.ViewController;

/**
 * Interface for a Factory of GameViews. To be implemented with framework specific logic.
 */

public interface GameViewFactory {
    /**
     * @param menuController
     * @param menuState
     * @return the GameView for the Menu Game State
     */
    GameView createMenu(MenuController menuController, MenuState menuState);
    /**
     * @param exploreController the controller to be passed the view
     * @param exploreState the state the view will need to render
     * @return the GameView for the Explore Game State
     */
    GameView createRoom(ExploreController exploreController, ExploreState exploreState);

}
