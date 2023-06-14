package it.tbt.view.javaFx;

import it.tbt.commons.resourceloader.ImageLoader;
import it.tbt.controller.modelmanager.ExploreState;
import it.tbt.controller.viewcontrollermanager.api.ExploreController;
import it.tbt.model.entities.MovableEntity;
import it.tbt.model.party.IParty;
import it.tbt.view.api.GameView;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.*;

/**
 * An implementation of a GameView that describes the Explore game state.
 */

public class JavaFxExploreView extends AbstractJavaFxView implements GameView {

    private ExploreState exploreState;
    private ExploreController exploreController;
    private Map<MovableEntity, ImageView> images = new HashMap<>(); //Images of objects who can move
    private Group staticImages = new Group();
    private Background bg;

    /**
     * @param exploreController The exploreController that provides the input to this view
     * @param exploreState The model data used to render the current game state
     * @param stage JavaFx main graphic component
     * @param scene JavaFx graphic component
     * @param root JavaFx graphic component
     */
    public JavaFxExploreView(final ExploreController exploreController, final ExploreState exploreState, final Stage stage, final Scene scene
            , final Group root) {
        super(stage, scene, root);
        this.exploreController = exploreController;
        this.setKeyMap();
        this.exploreState = exploreState;
        loadImagesRenderStatic();
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

    /**
     * Loads images, and populates the staticImages group with images of entities which
     * do not change overtime, so they do not need to be rendered more than one time.
     */
    private void loadImagesRenderStatic() {
        this.bg = new Background(new BackgroundImage(new Image(ImageLoader.getInstance().getFilePath(this.exploreState.getRoom().getClass())), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT));
        var party = new ImageView(ImageLoader.getInstance().getFilePath(IParty.class));
        this.images.put(this.exploreState.getParty(), party);
        party.setFitHeight(this.exploreState.getParty().getHeight());
        party.setFitWidth(this.exploreState.getParty().getWidth());
        for ( var x: this.exploreState.getRoom().getEntities() ) {
            var img = new ImageView(ImageLoader.getInstance().getFilePath(x.getClass()));
            if ( x instanceof MovableEntity ) {
                this.images.put((MovableEntity)x, img);
            }
            img.setFitWidth(x.getWidth());
            img.setFitHeight(x.getHeight());
            img.setX(x.getX());
            img.setY(x.getY());
            staticImages.getChildren().add(img);
        }
    }

    /**
     * Set the input mapping.
     */
    private void setKeyMap() {
        scene.setOnKeyPressed(event -> {
            KeyCode k = event.getCode();
            if( k.equals(KeyCode.D) ) {
                this.exploreController.moveRight();
            } else if( k.equals(KeyCode.W) ) {
                this.exploreController.moveUp();
            } else if( k.equals(KeyCode.A) ) {
                this.exploreController.moveLeft();
            } else if( k.equals(KeyCode.S) ) {
                this.exploreController.moveDown();
            } else if( k.equals(KeyCode.E) ) {
                this.exploreController.interactWithProximity();
            }
        });
    }
}


