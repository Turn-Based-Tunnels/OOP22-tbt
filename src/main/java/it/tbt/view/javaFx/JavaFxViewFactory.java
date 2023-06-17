package it.tbt.view.javaFx;

import it.tbt.controller.viewcontrollermanager.api.ViewController;
import it.tbt.controller.modelmanager.ExploreState;
import it.tbt.controller.modelmanager.FightState;
import it.tbt.controller.modelmanager.MenuState;
import it.tbt.controller.viewcontrollermanager.api.ExploreController;
import it.tbt.model.world.api.Room;
import it.tbt.controller.viewcontrollermanager.api.FightController;
import it.tbt.controller.viewcontrollermanager.impl.PauseMenuController;
import it.tbt.engine.api.Game;
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
        Group group = new Group();
        Scene scene = new Scene(group, 300, 300);
        Platform.runLater(() -> {
            stage.setScene(scene);
            stage.show();
        });
        return new JavaFxMenuView(menuController, this.stage, scene, group, menuState);
    }

    @Override
    public GameView createPause(ViewController menuController, MenuState menuState) {
        Group group = new Group();
        Scene scene = new Scene(group, 300, 300);
        Platform.runLater(() -> {
            stage.setScene(scene);
            stage.show();
        });

        return new JavaFxMenuView(menuController, this.stage, scene, group, menuState);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameView createRoom(final ViewController exploreController, final ExploreState exploreState) {
        Group group = new Group();
        Scene scene = new Scene(group, Room.X_AXIS_UPPERBOUND, Room.Y_AXIS_UPPERBOUND);
        Platform.runLater(() -> {
            stage.setScene(scene);
            stage.show();
        });
        return new JavaFxExploreView(exploreController, exploreState, this.stage, scene, group);
    }

    @Override
    public GameView createFight(ViewController fightController, FightState fightState) {
        Group group = new Group();
        Scene scene = new Scene(group, 300, 300);
        Platform.runLater(() -> {
            stage.setScene(scene);
            stage.show();
        });
        return new JavaFxFightView(stage, scene, group, fightController, fightState);
    }
}
