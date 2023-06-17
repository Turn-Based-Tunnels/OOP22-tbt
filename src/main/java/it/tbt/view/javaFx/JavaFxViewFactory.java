package it.tbt.view.javaFx;

import it.tbt.controller.viewcontrollermanager.api.ViewController;
import it.tbt.controller.modelmanager.ExploreState;
import it.tbt.controller.modelmanager.MenuState;
import it.tbt.model.world.api.Room;
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
}
