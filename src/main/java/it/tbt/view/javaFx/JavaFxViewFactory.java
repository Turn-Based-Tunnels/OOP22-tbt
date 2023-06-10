package it.tbt.view.javaFx;

import it.tbt.controller.ViewControllerManager.api.ExploreController;
import it.tbt.controller.ViewControllerManager.api.ViewController;

import it.tbt.view.api.GameView;
import it.tbt.view.api.GameViewExplore;
import it.tbt.view.api.GameViewFactory;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class JavaFxViewFactory implements GameViewFactory {

    private Stage stage;

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
    public GameView createMenu(final ViewController vc) {
        return new SimpleJavaFxViewMenu(vc);
    }


    @Override
    public GameViewExplore createRoom(ExploreController exploreController) {
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        Group group = new Group();
        Scene scene = new Scene(group, 300, 300);
        Platform.runLater(() -> {
            stage.setScene(scene);
            stage.show();
        });
        return new JavaFxExploreView(exploreController, this.stage, scene, group);
    }
}
