package it.tbt.view.javaFx;

import it.tbt.control.menu.impl.MenuController;
import it.tbt.controller.modelmanager.ExploreState;
import it.tbt.controller.modelmanager.MenuState;
import it.tbt.controller.viewcontrollermanager.api.ExploreController;
import it.tbt.controller.viewcontrollermanager.api.ViewController;

import it.tbt.view.api.GameView;
import it.tbt.view.api.GameViewFactory;
import it.tbt.view.mainMenu.MainMenu;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * JavaFx Factory for GameViews
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
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
    }

    /**
     * @return
     */
    @Override
    public GameView createMenu(final MenuController menuController, final MenuState menuState) {
        System.out.println("lel");
        Group group = new Group();
        Scene scene = new Scene(group, 300, 300);
        Platform.runLater(() -> {
            stage.setScene(scene);
            stage.show();
        });


        return new MainMenu(menuController, this.stage, scene, group, menuState);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameView createRoom(ExploreController exploreController, ExploreState exploreState) {
        Group group = new Group();
        Scene scene = new Scene(group, 300, 300);
        Platform.runLater(() -> {
            stage.setScene(scene);
            stage.show();
        });
        return new JavaFxExploreView(exploreController, exploreState, this.stage, scene, group);
    }
}
