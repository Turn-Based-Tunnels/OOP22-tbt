package it.tbt.view.api;

import it.tbt.controller.modelmanager.ExploreState;
import it.tbt.controller.viewcontrollermanager.api.ExploreController;
import it.tbt.controller.viewcontrollermanager.api.ViewController;

public interface GameViewFactory {
    public GameView createMenu(final ViewController vc);
    public GameView createRoom(final ExploreController vc, final ExploreState exploreState);

}
