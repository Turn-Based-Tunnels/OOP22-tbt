package it.tbt.view.javaFx;

import it.tbt.controller.viewcontrollermanager.api.ViewController;
import it.tbt.view.api.AbstractGameView;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * Abstract JavaFx View for defining common operations common to all GameViews
 * made in JavaFx.
 */

public abstract class AbstractJavaFxView extends AbstractGameView {

    protected Stage stage;
    protected Scene scene;
    protected Group root;

    /**
     * @param stage
     * @param scene
     * @param root
     */

    protected AbstractJavaFxView(ViewController viewController, Stage stage, Scene scene, Group root) {
        super(viewController);
        this.stage = stage;
        this.scene = scene;
        this.root = root;
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                viewController.onKeyPressed(event.getCode().getCode());
            }
        });
    }
}
