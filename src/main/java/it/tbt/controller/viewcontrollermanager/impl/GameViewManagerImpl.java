package it.tbt.controller.viewcontrollermanager.impl;

import it.tbt.controller.modelmanager.ExploreState;
import it.tbt.controller.modelmanager.MenuState;
import it.tbt.controller.modelmanager.ModelState;
import it.tbt.controller.modelmanager.FightState;
import it.tbt.controller.modelmanager.InventoryState;
import it.tbt.controller.modelmanager.EndState;
import it.tbt.controller.modelmanager.shop.ShopState;
import it.tbt.controller.viewcontrollermanager.api.ViewController;
import it.tbt.controller.viewcontrollermanager.api.ViewControllerManager;
import it.tbt.model.command.api.Command;
import it.tbt.model.GameState;
import it.tbt.view.api.GameView;
import it.tbt.view.api.GameViewFactory;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

/**
 * Default implementation of a ViewControllerManager.
 */

public class GameViewManagerImpl implements ViewControllerManager {

    private final GameViewFactory gameViewFactory;
    private ViewController currentController;
    private GameView currentGameView;

    /**
     * @param gameViewFactory the GameView factory which will be used to generate
     *                        the GameViews.
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
                    handleViewState(modelState,
                            ExploreState.class,
                            ExploreControllerImpl.class,
                            (controller, state) -> this.gameViewFactory.createRoom(state, controller));
                }
                case MENU -> {
                    handleViewState(modelState,
                            MenuState.class,
                            MainMenuController.class,
                            (controller, state) -> this.gameViewFactory.createMenu(state, controller));
                }
                case SHOP -> {
                    handleViewState(modelState,
                            ShopState.class,
                            ShopController.class,
                            (controller, state) -> this.gameViewFactory.createShop(state, controller));
                }
                case PAUSE -> {
                    handleViewState(modelState,
                            MenuState.class,
                            PauseMenuController.class,
                            (controller, state) -> this.gameViewFactory.createPause(state, controller));
                }
                case FIGHT -> {
                    handleViewState(modelState,
                            FightState.class,
                            FightControllerImpl.class,
                            (controller, state) -> this.gameViewFactory.createFight(state, controller));
                }
                case INVENTORY -> {
                    handleViewState(modelState,
                            InventoryState.class,
                            InventoryViewController.class,
                            (controller, state) -> this.gameViewFactory.createInventory(state, controller));

                }
                case ENDING -> {
                    handleViewState(modelState,
                            EndState.class,
                            EndViewController.class,
                            (controller, state) -> this.gameViewFactory.createEndScreen(state, controller));
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

    private <T extends ModelState, C extends ViewController> void handleViewState(
            final ModelState modelState,
            final Class<T> stateClass,
            final Class<C> controllerClass,
            final BiFunction<T, C, GameView> createViewFunction) {
        if (!stateClass.isInstance(modelState)) {
            throw new IllegalStateException("Data passed to View Manager inconsistent");
        }
        T state = stateClass.cast(modelState);
        C controller;
        try {
            controller = controllerClass.getConstructor(stateClass).newInstance(state);
        } catch (Exception e) {
            throw new IllegalStateException("Data inconsistent ViewManager.");
        }
        var x = createViewFunction.apply(state, controller);
        this.currentController = controller;
        this.currentGameView = x;
    }
}
