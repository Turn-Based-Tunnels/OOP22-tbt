package it.tbt.view.javaFx;

import it.tbt.commons.resourceloader.ImageLoader;
import it.tbt.controller.modelmanager.ExploreState;
import it.tbt.controller.viewcontrollermanager.api.ViewController;
import it.tbt.model.entities.MovableEntity;
import it.tbt.model.world.api.Room;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BackgroundPosition;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;


/**
 * An implementation of a GameView that describes the Explore game state.
 */

public class JavaFxExploreView extends AbstractJavaFxView {

    private final ExploreState exploreState;
    private final Pane movingSpace = new Pane();
    private final BorderPane total = new BorderPane();
    private final Map<MovableEntity, ImageView> images = new HashMap<>(); //Images of objects who can move
    private final Group staticImages = new Group();

    /**
     * @param exploreController The exploreController that provides the input to this view
     * @param exploreState The model data used to render the current game state
     * @param stage JavaFx main graphic component
     * @param scene JavaFx graphic component
     */
    public JavaFxExploreView(final ViewController exploreController,
                             final ExploreState exploreState,
                             final Stage stage,
                             final Scene scene) {
        super(exploreController, stage, scene);
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
            for (var x: images.entrySet()) {
                x.getValue().setX(x.getKey().getX() - (x.getKey().getWidth() / 2));
                x.getValue().setY(x.getKey().getY() - (x.getKey().getHeight() / 2));
                this.movingSpace.getChildren().add(x.getValue());
            }
            this.movingSpace.getChildren().addAll(staticImages);
            this.total.setTop(this.movingSpace);
            this.getScene().setRoot(this.total);
        });
    }

    /**
     * Loads images, and populates the staticImages group with images of entities which
     * do not change overtime, so they do not need to be rendered more than one time.
     */
    private void loadImagesRenderStatic() {
        //Load Image of Party
        var party = new ImageView(ImageLoader.getInstance().getFilePath(this.exploreState.getParty().getClass()));
        this.images.put(this.exploreState.getParty(), party);
        party.setFitHeight(this.exploreState.getParty().getHeight());
        party.setFitWidth(this.exploreState.getParty().getWidth());
        //Iterates over all entities currently in the room
        for (var x: this.exploreState.getRoom().getEntities()) {
            //Loads Image of the entity
            var img = new ImageView(ImageLoader.getInstance().getFilePath(x.getClass()));
            //Adds it to the list of moving entities
            if (x instanceof MovableEntity) {
                this.images.put( (MovableEntity) x, img);
            }
            img.setFitWidth(x.getWidth());
            img.setFitHeight(x.getHeight());
            img.setX(x.getX() - x.getWidth() / 2);
            img.setY(x.getY() - x.getHeight() / 2);
            staticImages.getChildren().add(img);
        }
    }

    private void loadBackground() {
        Background bg = new Background(
                        new BackgroundImage(
                        new Image(ImageLoader.getInstance().getFilePath(this.exploreState.getRoom().getClass())),
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundPosition.DEFAULT,
                                new BackgroundSize(1.0, 1.0, true, true, false, false)));
        this.movingSpace.setBackground(bg);
    }
}


