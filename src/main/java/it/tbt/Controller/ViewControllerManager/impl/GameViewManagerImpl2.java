package it.tbt.Controller.ViewControllerManager.impl;

import it.tbt.Controller.ModelManager.ExploreState;
import it.tbt.Controller.ModelManager.ModelState;
import it.tbt.Controller.ViewControllerManager.api.ExploreController;
import it.tbt.Controller.ViewControllerManager.api.ViewController;
import it.tbt.Controller.ViewControllerManager.api.ViewControllerManager;
import it.tbt.Model.Command.api.Command;
import it.tbt.Model.GameState;
import it.tbt.View.api.GameView;
import it.tbt.View.api.GameViewExplore;
import it.tbt.View.api.GameViewFactory;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

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
    public List<Command> getCommands() {
        return this.currentController.getCommands();
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
                    var x = this.gameViewFactory.createRoom(exploreControllerImpl);
                    this.currentController = exploreControllerImpl;
                    this.currentGameView = x;
                }
            }
        }
        switch (gameState) {
            case EXPLORE -> {
                ((GameViewExplore) this.currentGameView).setData((ExploreState) modelState);
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
