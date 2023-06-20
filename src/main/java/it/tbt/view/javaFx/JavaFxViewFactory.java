package it.tbt.view.javaFx;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.tbt.controller.modelmanager.MenuState;
import it.tbt.controller.modelmanager.FightState;
import it.tbt.controller.modelmanager.EndState;
import it.tbt.controller.modelmanager.InventoryState;
import it.tbt.controller.modelmanager.ExploreState;
import it.tbt.controller.modelmanager.shop.ShopState;
import it.tbt.controller.viewcontrollermanager.api.ViewController;
import it.tbt.controller.viewcontrollermanager.impl.ShopController;
import it.tbt.view.api.GameView;
import it.tbt.view.api.GameViewFactory;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * JavaFx Factory for GameViews.
 */

public class JavaFxViewFactory implements GameViewFactory {

    private static final int HEIGHT_WINDOW = 600;
    private static final int WIDTH_WINDOW = 800;
    private final Stage stage;

    /**
     * @param stage used as reference to where the GameViews will reside.
     */
    @SuppressFBWarnings (
            value = { "EI2 ", "Dm"},
            justification = "The Factory creates the Views in JavaFx so it needs a Stage, and this Stage"
                    + " is the one loaded at start of the javaFx application. And it needs to close the application"
    )
    public JavaFxViewFactory(final Stage stage) {
        this.stage = stage;
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(final WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameView createMenu(final ViewController menuController, final MenuState menuState) {
        Scene scene = new Scene(new Group(), WIDTH_WINDOW, HEIGHT_WINDOW);
        Platform.runLater(() -> {
            stage.setScene(scene);
            stage.show();
        });
        return new JavaFxMenuView(menuController, this.stage, scene, menuState);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameView createPause(final ViewController menuController, final MenuState menuState) {
        Scene scene = new Scene(new Group(), WIDTH_WINDOW, HEIGHT_WINDOW);
        Platform.runLater(() -> {
            stage.setScene(scene);
            stage.show();
        });

        return new JavaFxMenuView(menuController, this.stage, scene, menuState);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameView createRoom(final ViewController exploreController, final ExploreState exploreState) {
        Scene scene = new Scene(new Group(), WIDTH_WINDOW, HEIGHT_WINDOW);
        Platform.runLater(() -> {
            stage.setScene(scene);
            stage.show();
        });
        return new JavaFxExploreView(exploreController, exploreState, this.stage, scene);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameView createFight(final ViewController fightController, final FightState fightState) {
        Group group = new Group();
        Scene scene = new Scene(group, WIDTH_WINDOW, HEIGHT_WINDOW);
        Platform.runLater(() -> {
            stage.setScene(scene);
            stage.show();
        });
        return new JavaFxFightView(stage, scene, fightController, fightState);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameView createShop(final ShopController shopController, final ShopState shopState) {
        Group group = new Group();
        Scene scene = new Scene(group, WIDTH_WINDOW, HEIGHT_WINDOW);
        Platform.runLater(() -> {
            stage.setScene(scene);
            stage.show();
        });
        return new JavaFxShopView(shopController, stage, scene, shopState);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameView createInventory(final ViewController inventoryController, final InventoryState inventoryState) {
        Group group = new Group();
        Scene scene = new Scene(group, WIDTH_WINDOW, HEIGHT_WINDOW);
        Platform.runLater(() -> {
            stage.setScene(scene);
            stage.show();
        });

        return new JavaFxInventoryView(inventoryController, this.stage, scene, inventoryState);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameView createEndScreen(final ViewController endController, final EndState endState) {
        Group group = new Group();
        Scene scene = new Scene(group, WIDTH_WINDOW, HEIGHT_WINDOW);
        Platform.runLater(() -> {
            stage.setScene(scene);
            stage.show();
        });

        return new JavaFxEndingView(endController, this.stage, scene, endState);
    }
}
