package it.tbt.view.javaFx;

import it.tbt.controller.viewcontrollermanager.api.ViewController;
import it.tbt.view.api.AbstractGameView;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * Abstract JavaFx View for defining common operations common to all GameViews
 * made in JavaFx.
 */

public abstract class AbstractJavaFxView extends AbstractGameView {
    private Stage stage;
    private Scene scene;
    public static final int WIDTH_WINDOW = 800;
    public static final int HEIGHT_WINDOW = 600;

    /**
     * @param viewController
     * @param stage
     * @param scene
     */
    protected AbstractJavaFxView(final ViewController viewController, final Stage stage, final Scene scene) {
        super(viewController);
        this.stage = stage;
        this.scene = scene;
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent event) {
                viewController.onKeyPressed(event.getCode().getCode());
            }
        });
    }

    /**
     * @return the scene on to which this view is rendered.
     */
    protected Scene getScene() {
        return this.scene;
    }
}
