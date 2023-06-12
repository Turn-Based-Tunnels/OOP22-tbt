package it.tbt.view.javaFx;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class AbstractJavaFxView {

    protected Stage stage;
    protected Scene scene;
    protected Group root;

    public AbstractJavaFxView(Stage stage, Scene scene, Group root) {
        this.stage = stage;
        this.scene = scene;
        this.root = root;
    }

    protected void resizeView(int width, int height) {
        this.stage.setWidth(width);
        this.stage.setHeight(height);
    }

}
