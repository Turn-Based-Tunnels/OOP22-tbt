package it.tbt.view.javaFx;

import it.tbt.model.Entities.Entity;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

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
