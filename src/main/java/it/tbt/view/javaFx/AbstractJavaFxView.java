package it.tbt.view.javaFx;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Abstract JavaFx View for defining common operations common to all GameViews
 * made in JavaFx.
 */

public abstract class AbstractJavaFxView {

    protected Stage stage;
    protected Scene scene;
    protected Group root;

    /**
     * @param stage
     * @param scene
     * @param root
     */

    protected AbstractJavaFxView(Stage stage, Scene scene, Group root) {
        this.stage = stage;
        this.scene = scene;
        this.root = root;
    }

    /**
     * Resizes the stage to width and height provided by parameter.
     * @param width
     * @param height
     */
    protected void resizeView(int width, int height) {
        this.stage.setWidth(width);
        this.stage.setHeight(height);
    }
}
