package it.tbt.view.console;

import it.tbt.controller.ModelManager.ExploreState;
import it.tbt.controller.ViewControllerManager.api.ExploreController;
import it.tbt.controller.ViewControllerManager.api.ViewController;
import it.tbt.view.api.GameView;
import it.tbt.view.api.GameViewFactory;

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
     * @param exploreState
     * @return
     */
    @Override
    public GameView createRoom(ExploreController vc, ExploreState exploreState) {
        return null;
    }

}
