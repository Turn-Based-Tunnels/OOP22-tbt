package it.tbt.controller.ViewControllerManager.impl;

import it.tbt.controller.ModelManager.ExploreState;
import it.tbt.controller.ModelManager.ModelState;
import it.tbt.controller.ViewControllerManager.api.ExploreController;
import it.tbt.controller.ViewControllerManager.api.ViewController;
import it.tbt.controller.ViewControllerManager.api.ViewControllerManager;
import it.tbt.model.Command.api.Command;
import it.tbt.model.GameState;
import it.tbt.view.api.GameView;
import it.tbt.view.api.GameViewExplore;
import it.tbt.view.api.GameViewFactory;

import java.util.List;
import java.util.Optional;

public class GameViewManagerImpl2 implements ViewControllerManager {

    private GameViewFactory gameViewFactory;
    private ViewController currentController;

    private GameView currentGameView;

    public GameViewManagerImpl2(final GameViewFactory gameViewFactory) {
        this.gameViewFactory = gameViewFactory;
    }

    /**
     * @return
     */
    @Override
    public Optional<List<Command>> getCommands() {
        return Optional.ofNullable(this.currentController.getCommands());
    }

    /**
     * @param gameState
     * @param modelState
     */
    @Override
    public void renderView(GameState gameState, ModelState modelState, Boolean hasChanged) {
        if(hasChanged) {
            switch (gameState) {
                case EXPLORE -> {
                    ExploreState exploreState = (ExploreState) modelState;
                    ExploreController exploreControllerImpl = new ExploreControllerImpl(exploreState);
                    var x = this.gameViewFactory.createRoom(exploreControllerImpl, exploreState);
                    this.currentController = exploreControllerImpl;
                    this.currentGameView = x;
                }
            }
        }
        this.currentGameView.render();
    }

    /**
     *
     */
    @Override
    public void cleanCommands() {
        this.currentController.clean();
    }
}
