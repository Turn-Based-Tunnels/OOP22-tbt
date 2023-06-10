package it.tbt.view.javaFx;

import it.tbt.controller.ModelManager.ExploreState;
import it.tbt.controller.ViewControllerManager.api.ExploreController;
import it.tbt.model.entities.Entity;
import it.tbt.model.party.Party;
import it.tbt.view.api.GameViewExplore;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class JavaFxExploreView extends AbstractJavaFxView implements GameViewExplore {

    private ExploreState exploreState;
    private ExploreController exploreController;
    private Scene scene;
    private Group root;
    private Map<Class<? extends Entity>, String> images = new HashMap<>();

    private ImageView imageView;


    public JavaFxExploreView(ExploreController exploreController, Stage stage, Scene scene, Group root) {
        super(stage, scene, root);
        images.put(Party.class, "./tbt/player2.png");
        this.scene = scene;
        this.root = root;
        this.exploreController = exploreController;
        this.scene.setOnKeyPressed(event -> {
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
        imageView = new ImageView(this.images.get(Party.class));
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        
    }

    /**
     *
     */
    @Override
    public void render() {
        //var entities = exploreState.getAllEntities();
        Platform.runLater(() -> {
            root.getChildren().clear();
            imageView.setX(this.exploreState.getParty().getX());
            imageView.setY(this.exploreState.getParty().getY());
            System.out.println("Y:"+this.exploreState.getParty().getY()+ "X:"+this.exploreState.getParty().getX());
            root.getChildren().add(imageView);
            scene.setRoot(root);
        });
    }


    /**
     * @param modelState
     */
    @Override
    public void setData(ExploreState modelState) {
        this.exploreState = modelState;
    }
}


