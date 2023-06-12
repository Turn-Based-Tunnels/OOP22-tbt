package it.tbt.controller.viewcontrollermanager.impl;

import it.tbt.controller.modelmanager.ExploreState;
import it.tbt.controller.modelmanager.ModelState;
import it.tbt.controller.viewcontrollermanager.api.ExploreController;
import it.tbt.controller.viewcontrollermanager.api.ViewController;
import it.tbt.controller.viewcontrollermanager.api.ViewControllerManager;
import it.tbt.model.command.api.Command;
import it.tbt.model.GameState;
import it.tbt.view.api.GameView;
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
