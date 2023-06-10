package it.tbt.View.api;

import it.tbt.Controller.ViewControllerManager.api.ExploreController;
import it.tbt.Controller.ViewControllerManager.api.ViewController;

public interface GameViewFactory {
    public GameView createMenu(final ViewController vc);
    public GameViewExplore createRoom(final ExploreController vc);

}
