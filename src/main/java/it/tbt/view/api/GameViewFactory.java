package it.tbt.view.api;

import it.tbt.controller.ModelManager.ExploreState;
import it.tbt.controller.ViewControllerManager.api.ExploreController;
import it.tbt.controller.ViewControllerManager.api.ViewController;

public interface GameViewFactory {
    public GameView createMenu(final ViewController vc);
    public GameView createRoom(final ExploreController vc, final ExploreState exploreState);

}
