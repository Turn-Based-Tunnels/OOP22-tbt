package it.tbt.view.api;

import it.tbt.controller.viewcontrollermanager.api.ViewController;
import it.tbt.controller.viewcontrollermanager.impl.MainMenuController;
import it.tbt.controller.modelmanager.ExploreState;
import it.tbt.controller.modelmanager.MenuState;
import it.tbt.controller.viewcontrollermanager.api.ExploreController;
import it.tbt.controller.viewcontrollermanager.impl.PauseMenuController;

import javax.swing.text.View;

/**
 * Interface for a Factory of GameViews. To be implemented with framework specific logic.
 */

public interface GameViewFactory {

    public GameView createMenu(ViewController menuController, MenuState menuState);
    public GameView createPause(ViewController menuController, MenuState menuState);
    /**
     * @param exploreController the controller to be passed the view
     * @param exploreState the state the view will need to render
     * @return the GameView for the Explore Game State
     */
    GameView createRoom(ViewController exploreController, ExploreState exploreState);

}
