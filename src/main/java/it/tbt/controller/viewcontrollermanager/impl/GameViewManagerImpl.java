package it.tbt.controller.viewcontrollermanager.impl;

import it.tbt.controller.modelmanager.ExploreState;
import it.tbt.controller.modelmanager.MenuState;
import it.tbt.controller.modelmanager.MenuStateImpl;
import it.tbt.controller.modelmanager.ModelState;
import it.tbt.controller.viewcontrollermanager.api.ViewController;
import it.tbt.controller.viewcontrollermanager.api.ViewControllerManager;
import it.tbt.model.command.api.Command;
import it.tbt.model.GameState;
import it.tbt.view.api.GameView;
import it.tbt.view.api.GameViewFactory;

import java.util.List;
import java.util.Optional;

/**
 * Default implementation of a ViewControllerManager.
 */

public class GameViewManagerImpl implements ViewControllerManager {

    private GameViewFactory gameViewFactory;
    private ViewController currentController;
    private GameView currentGameView;

    /**
     * @param gameViewFactory the GameView factory which will be used to generate the GameViews.
     */
    public GameViewManagerImpl(final GameViewFactory gameViewFactory) {
        this.gameViewFactory = gameViewFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<List<Command>> getCommands() {
        return Optional.ofNullable(this.currentController.getCommands());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void renderView(final GameState gameState, final ModelState modelState, final Boolean hasChanged) {
        if (hasChanged) {
            switch (gameState) {
                case EXPLORE -> {
                    if (!(modelState instanceof ExploreState)) {
                        throw new IllegalStateException("Data passed to View Manager inconsistent");
                    }
                    ExploreState exploreState = (ExploreState) modelState;
                    ViewController exploreControllerImpl = new ExploreControllerImpl(exploreState);
                    var x = this.gameViewFactory.createRoom(exploreControllerImpl, exploreState);
                    this.currentController = exploreControllerImpl;
                    this.currentGameView = x;
                }
                case MENU -> {
                    if (!(modelState instanceof MenuState)) {
                        throw new IllegalStateException("Data passed to View Manager inconsistent");
                    }
                    MenuStateImpl menuState = (MenuStateImpl) modelState;
                    MainMenuController menuController = new MainMenuController(menuState);
                    var x = this.gameViewFactory.createMenu(menuController, menuState);
                    this.currentController = menuController;
                    this.currentGameView = x;
                }
                case PAUSE -> {
                    if (!(modelState instanceof MenuState)) {
                        throw new IllegalStateException("Data passed to View Manager inconsistent");
                    }
                    MenuStateImpl menuState = (MenuStateImpl) modelState;
                    PauseMenuController menuController = new PauseMenuController(menuState);
                    var x = this.gameViewFactory.createMenu(menuController, menuState);
                    this.currentController = menuController;
                    this.currentGameView = x;
                }
                default -> {
                    throw new IllegalStateException("GameState not handled by ViewManager.");
                }

            }
        }
        this.currentGameView.render();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void cleanCommands() {
        this.currentController.clean();
    }
}
