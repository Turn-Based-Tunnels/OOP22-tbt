package it.tbt.view.javaFx;

import it.tbt.controller.modelmanager.InventoryState;
import it.tbt.controller.viewcontrollermanager.api.ViewController;
import it.tbt.controller.modelmanager.ExploreState;
import it.tbt.controller.modelmanager.FightState;
import it.tbt.controller.modelmanager.MenuState;
import it.tbt.model.world.api.Room;
import it.tbt.controller.modelmanager.shop.ShopStateImpl;
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

    private static final int HEIGHT_WINDOW = 300;
    private static final int WIDTH_WINDOW = 300;
    private Stage stage;

    /**
     * @param stage used as reference to where the GameViews will reside.
     */
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

    @Override
    public GameView createPause(ViewController menuController, MenuState menuState) {
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
        Scene scene = new Scene(new Group(), Room.X_AXIS_UPPERBOUND, Room.Y_AXIS_UPPERBOUND);
        Platform.runLater(() -> {
            stage.setScene(scene);
            stage.show();
        });
        return new JavaFxExploreView(exploreController, exploreState, this.stage, scene);
    }

    @Override
    public GameView createFight(ViewController fightController, FightState fightState) {
        Group group = new Group();
        Scene scene = new Scene(group, 300, 300);
        Platform.runLater(() -> {
            stage.setScene(scene);
            stage.show();
        });
        return new JavaFxFightView(stage, scene, fightController, fightState);
    }

    @Override
    public GameView createShop(final ShopController shopControler, final ShopStateImpl shopState) {
        Group group = new Group();
        Scene scene = new Scene(group, 300, 300);
        Platform.runLater(() -> {
            stage.setScene(scene);
            stage.show();
        });
        return new JavaFxShopView(shopControler, stage, scene, shopState);
    }

    @Override
    public GameView createInventory(ViewController inventoryController, InventoryState inventoryState) {
        Group group = new Group();
        Scene scene = new Scene(group, 300, 300);
        Platform.runLater(() -> {
            stage.setScene(scene);
            stage.show();
        });


        return new JavaFxInventoryView(inventoryController, this.stage, scene, inventoryState);
    }
}
