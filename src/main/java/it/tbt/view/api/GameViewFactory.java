package it.tbt.view.api;

import com.sun.glass.ui.View;
import it.tbt.controller.viewcontrollermanager.api.ViewController;
import it.tbt.controller.viewcontrollermanager.impl.MainMenuController;
import it.tbt.controller.modelmanager.ExploreState;
import it.tbt.controller.modelmanager.MenuState;
import it.tbt.controller.viewcontrollermanager.api.ExploreController;
import it.tbt.controller.viewcontrollermanager.impl.PauseMenuController;

/**
 * Interface for a Factory of GameViews. To be implemented with framework specific logic.
 */

public interface GameViewFactory {
    public GameView createMenu(ViewController menuController, MenuState menuState);

    public GameView createPause(PauseMenuController menuController, MenuState menuState);
    /**
     * @param exploreController the controller to be passed the view
     * @param exploreState the state the view will need to render
     * @return the GameView for the Explore Game State
     */
    public GameView createRoom(final ExploreController exploreController, final ExploreState exploreState);

}
