package it.tbt.view.javaFx;

import it.tbt.controller.modelmanager.EndState;
import it.tbt.controller.modelmanager.MenuState;
import it.tbt.controller.viewcontrollermanager.api.ViewController;
import it.tbt.model.menu.api.MenuButton;
import it.tbt.model.menu.api.MenuItem;
import it.tbt.model.menu.impl.MenuSelect;
import it.tbt.view.api.GameView;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class JavaFxEndingView extends /*ResizableApp*/ AbstractJavaFxView implements GameView {

    private Scene scene;
    private ViewController endController;
    private EndState main;

    public JavaFxEndingView(ViewController endController, Stage stage, Scene scene, EndState endState) {
        super(endController, stage, scene);
        this.scene = scene;
        this.endController = endController;
        this.main = endState;
    }

    /**
     * @param viewController
     * @param stage
     * @param scene
     */
    protected JavaFxEndingView (ViewController viewController, Stage stage, Scene scene) {
        super (viewController, stage, scene);
    }


    @Override
    public void render() {
        Platform.runLater(() -> {
            StackPane root = new StackPane ();
            root.getChildren().clear();
            VBox vbox = new VBox();
            Label title = new Label (main.getMessage ());
            vbox.getChildren ().add (title);
            vbox.setAlignment(Pos.CENTER);
            vbox.setSpacing (10);
            root.getChildren().add(vbox);
            root.setAlignment (Pos.CENTER);
            this.scene.setCursor(Cursor.NONE);
            this.getScene().setRoot(root);

        });
    }
}