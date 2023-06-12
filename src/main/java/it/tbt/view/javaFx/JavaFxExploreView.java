package it.tbt.view.javaFx;

import it.tbt.Commons.resourceloader.ImageLoader;
import it.tbt.controller.ModelManager.ExploreState;
import it.tbt.controller.ViewControllerManager.api.ExploreController;
import it.tbt.model.entities.SpatialEntity;
import it.tbt.model.party.IParty;
import it.tbt.view.api.GameView;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.util.*;

public class JavaFxExploreView extends AbstractJavaFxView implements GameView {

    private ExploreState exploreState;
    private ExploreController exploreController;
    private Map<SpatialEntity, ImageView> images = new HashMap<>(); //Images of objects who can move

    public JavaFxExploreView(ExploreController exploreController, ExploreState exploreState, Stage stage, Scene scene, Group root) {
        super(stage, scene, root);
        this.exploreController = exploreController;
        this.setKeyMap();
        this.exploreState = exploreState;
        loadImagesOfEntities();
    }

    /**
     *
     */
    @Override
    public void render() {
        //var entities = exploreState.getAllEntities();
        Platform.runLater(() -> {
            root.getChildren().clear();
            for(var x: images.keySet()) {
                this.images.get(x).setX(x.getX());
                this.images.get(x).setY(x.getY());
                root.getChildren().add(this.images.get(x));
            }
            scene.setRoot(root);
        });
    }

    private void loadImagesOfEntities() {
        var party = new ImageView(ImageLoader.getInstance().getFilePath(IParty.class));
        this.images.put(this.exploreState.getParty(), party);
        party.setFitHeight(50);
        party.setFitWidth(50);
    }

    private void setKeyMap() {
        scene.setOnKeyPressed(event -> {
            KeyCode k = event.getCode();
            if(k.equals(KeyCode.D)) {
                this.exploreController.moveRight();
            } else if(k.equals(KeyCode.W)) {
                this.exploreController.moveUp();
            } else if(k.equals(KeyCode.A)) {
                this.exploreController.moveLeft();
            } else if(k.equals(KeyCode.S)) {
                this.exploreController.moveDown();
            }
        });
    }
}


