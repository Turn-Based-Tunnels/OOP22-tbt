package it.tbt.view.api;

import it.tbt.controller.modelmanager.ExploreState;
import it.tbt.controller.viewcontrollermanager.api.ExploreController;
import it.tbt.controller.viewcontrollermanager.api.ViewController;

/**
 * Interface for a Factory of GameViews. To be implemented with framework specific logic.
 */

public interface GameViewFactory {
    public GameView createMenu(final ViewController vc);
    /**
     * @param exploreController the controller to be passed the view
     * @param exploreState the state the view will need to render
     * @return the GameView for the Explore Game State
     */
    public GameView createRoom(final ExploreController exploreController, final ExploreState exploreState);

}
