package it.tbt.view.javaFx;

import it.tbt.commons.resourceloader.ImageLoader;
import it.tbt.controller.modelmanager.ExploreState;
import it.tbt.controller.viewcontrollermanager.api.ExploreController;
import it.tbt.controller.viewcontrollermanager.api.ViewController;
import it.tbt.model.entities.MovableEntity;
import it.tbt.model.party.IParty;
import it.tbt.model.world.api.Room;
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

/**
 * An implementation of a GameView that describes the Explore game state.
 */

public class JavaFxExploreView extends AbstractJavaFxView implements GameView {

    private ExploreState exploreState;
    private Pane movingSpace = new Pane();
    private BorderPane total = new BorderPane();
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
    public JavaFxExploreView(final ViewController exploreController, final ExploreState exploreState, final Stage stage, final Scene scene
            , final Group root) {
        super(exploreController, stage, scene, root);
        this.exploreState = exploreState;
        this.movingSpace.setPrefSize(Room.X_AXIS_UPPERBOUND, Room.Y_AXIS_UPPERBOUND);
        loadBackground();
        loadImagesRenderStatic();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render() {
        Platform.runLater(() -> {
            this.movingSpace.getChildren().clear();
            for(var x: images.keySet()) {
                this.images.get(x).setX(x.getX()-x.getWidth()/2);
                this.images.get(x).setY(x.getY()-x.getHeight()/2);
                //System.out.println(x.getX()+", "+x.getY());
                this.movingSpace.getChildren().add(this.images.get(x));
            }
            this.movingSpace.getChildren().addAll(staticImages);
            this.total.setTop(this.movingSpace);
            scene.setRoot(this.total);
        });
    }

    /**
     * Loads images, and populates the staticImages group with images of entities which
     * do not change overtime, so they do not need to be rendered more than one time.
     */
    private void loadImagesRenderStatic() {
        var party = new ImageView(ImageLoader.getInstance().getFilePath(this.exploreState.getParty().getClass()));
        this.images.put(this.exploreState.getParty(), party);
        party.setFitHeight(this.exploreState.getParty().getHeight());
        party.setFitWidth(this.exploreState.getParty().getWidth());
        for ( var x: this.exploreState.getRoom().getEntities() ) {
            var img = new ImageView(ImageLoader.getInstance().getFilePath(x.getClass()));
            if ( x instanceof MovableEntity ) {
                this.images.put( (MovableEntity) x, img);
            }
            img.setFitWidth(x.getWidth());
            img.setFitHeight(x.getHeight());
            img.setX(x.getX()-x.getWidth()/2);
            img.setY(x.getY()-x.getHeight()/2);
            staticImages.getChildren().add(img);
        }
    }

    private void loadBackground() {
        this.bg = new Background(new BackgroundImage(new Image(ImageLoader.getInstance().getFilePath(this.exploreState.getRoom().getClass())), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1.0, 1.0, true, true, false, false)));
        this.movingSpace.setBackground(this.bg);
    }
}


