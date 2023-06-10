package it.tbt.View.console;

import it.tbt.Controller.ViewControllerManager.api.ExploreController;
import it.tbt.Controller.ViewControllerManager.api.ViewController;
import it.tbt.Controller.ViewControllerManager.impl.ExploreControllerImpl;
import it.tbt.View.api.GameView;
import it.tbt.View.api.GameViewExplore;
import it.tbt.View.api.GameViewFactory;

public class ConsoleViewFactory implements GameViewFactory {
    /**
     * @param vc
     * @return
     */
    @Override
    public GameView createMenu(ViewController vc) {
        return null;
    }

    /**
     * @param vc
     * @return
     */
    @Override
    public GameViewExplore createRoom(ExploreController vc) {
        return null;
    }
}
