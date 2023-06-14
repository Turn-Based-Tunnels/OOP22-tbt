package it.tbt.view.javaFx;

import it.tbt.commons.resourceloader.ImageLoader;
import it.tbt.controller.modelmanager.ExploreState;
import it.tbt.controller.viewcontrollermanager.api.ExploreController;
import it.tbt.model.entities.MovableEntity;
import it.tbt.model.party.IParty;
import it.tbt.view.api.GameView;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.*;

public class JavaFxExploreView extends AbstractJavaFxView implements GameView {

    private ExploreState exploreState;
    private ExploreController exploreController;
    private Map<MovableEntity, ImageView> images = new HashMap<>(); //Images of objects who can move

    private Group staticImages = new Group();

    private Background bg;

    public JavaFxExploreView(ExploreController exploreController, ExploreState exploreState, Stage stage, Scene scene, Group root) {
        super(stage, scene, root);
        this.exploreController = exploreController;
        this.exploreState = exploreState;
        loadImagesRenderStatic();
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                System.out.println("ff15" + event.getCode());
                exploreController.onKeyPressed(event.getCode().getCode());
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render() {
        Platform.runLater(() -> {
            Pane rootPane = new Pane();
            Group dynamicImages = new Group();
            dynamicImages.getChildren().clear();
            for(var x: images.keySet()) {
                this.images.get(x).setX(x.getX());
                this.images.get(x).setY(x.getY());
                dynamicImages.getChildren().add(this.images.get(x));
            }
            rootPane.setBackground(this.bg);
            rootPane.getChildren().addAll(dynamicImages, staticImages);
            scene.setRoot(rootPane);
        });
    }

    private void loadImagesRenderStatic() {
        this.bg = new Background(new BackgroundImage(new Image(ImageLoader.getInstance().getFilePath(this.exploreState.getRoom().getClass())), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT));
        var party = new ImageView(ImageLoader.getInstance().getFilePath(IParty.class));
        this.images.put(this.exploreState.getParty(), party);
        party.setFitHeight(this.exploreState.getParty().getHeight());
        party.setFitWidth(this.exploreState.getParty().getWidth());
        for(var x: this.exploreState.getRoom().getEntities()) {
            var img = new ImageView(ImageLoader.getInstance().getFilePath(x.getClass()));
            if(x instanceof MovableEntity) {
                this.images.put((MovableEntity)x, img);
            }
            img.setFitWidth(x.getWidth());
            img.setFitHeight(x.getHeight());
            img.setX(x.getX());
            img.setY(x.getY());
            staticImages.getChildren().add(img);
        }
    }

}


