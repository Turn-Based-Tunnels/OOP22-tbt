package it.tbt.view.javaFx;

import it.tbt.commons.resourceloader.ImageLoader;
import it.tbt.controller.modelmanager.ExploreState;
import it.tbt.controller.viewcontrollermanager.api.ViewController;
import it.tbt.model.entities.MovableEntity;
import it.tbt.model.entities.SpatialEntity;
import it.tbt.model.world.api.Room;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * An implementation of a GameView that describes the Explore game state.
 */

public class JavaFxExploreView extends AbstractJavaFxView {

    private final ExploreState exploreState;
    private final Pane movingSpace = new Pane();
    private final StackPane total = new StackPane();
    private Map<MovableEntity, ImageView> images = new HashMap<>(); //Images of objects who can move
    private final Pane staticImages = new Pane();

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
        this.movingSpace.setMinSize(Room.X_AXIS_UPPERBOUND, Room.Y_AXIS_UPPERBOUND);
        this.movingSpace.setMaxSize(Room.X_AXIS_UPPERBOUND, Room.Y_AXIS_UPPERBOUND);
        loadBackground();
        loadAllImages();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render() {
        if(!Stream.concat(this.exploreState.getRoom().getEntities().stream().filter(l->l instanceof MovableEntity),
                Stream.of(this.exploreState.getParty())).collect(Collectors.toSet()).equals(this.images.keySet())) {
           loadAllImages();
        }
        Platform.runLater(() -> {
            this.total.getChildren().clear();
            loadImagesToRegion(this.movingSpace, this.images);
            this.movingSpace.getChildren().addAll(this.staticImages);
            this.total.getChildren().add(this.movingSpace);
            this.getScene().setRoot(this.total);
        });
    }

    /**
     * Loads images, and populates the staticImages group with images of entities which
     * do not change overtime, so they do not need to be rendered more than one time.
     * And populates the dynamic images, which need to change over time.
     */
    private void loadAllImages() {
        var x = getMapEntitiesImagesBasedOnPredicate(l -> true, Stream.concat(this.exploreState.getRoom().getEntities().stream(), Stream.of(this.exploreState.getParty())).collect(Collectors.toSet()));
        loadImagesToRegion(this.staticImages, x.entrySet().stream().filter(l -> !(l.getKey() instanceof MovableEntity)).map(l->Map.entry(l.getKey(), l.getValue())).collect(Collectors.toMap(l->l.getKey(), l->l.getValue())));
        loadImagesToRegion(this.movingSpace, x.entrySet().stream().filter(l->l.getKey() instanceof MovableEntity).map(l->Map.entry(l.getKey(),l.getValue())).collect(Collectors.toMap(l->l.getKey(), l->l.getValue())));
        this.images = x.entrySet().stream().filter(l->l.getKey() instanceof MovableEntity).map(l->Map.entry((MovableEntity)l.getKey(),l.getValue())).collect(Collectors.toMap(l->l.getKey(), l->l.getValue()));
    }

    private void loadImagesToRegion(Pane r, Map<? extends SpatialEntity, ImageView> images) {
        r.getChildren().clear();
        images.entrySet().stream().forEach(l -> {
            l.getValue().setX(l.getKey().getX() - l.getKey().getWidth() / 2);
            l.getValue().setY(l.getKey().getY() - l.getKey().getWidth() / 2);
            r.getChildren().add(l.getValue());
        });
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

    private Map<SpatialEntity, ImageView> getMapEntitiesImagesBasedOnPredicate(final Predicate<SpatialEntity> predicate, final Set<SpatialEntity> entitySet) {
        var x = entitySet.stream().
                filter(l -> predicate.test(l)).
                collect(Collectors.toMap(l->l, l-> new ImageView(ImageLoader.getInstance().getFilePath(l.getClass()))));

        x.entrySet().stream().forEach(l -> {
            l.getValue().setFitWidth(l.getKey().getWidth());
            l.getValue().setFitHeight(l.getKey().getHeight());
        });
        return x;
    }
}


