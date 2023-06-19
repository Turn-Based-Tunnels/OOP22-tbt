package it.tbt.view.javaFx;

import it.tbt.controller.modelmanager.EndState;
import it.tbt.controller.viewcontrollermanager.api.ViewController;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The {@code JavaFxEndingView} class represents a JavaFX implementation of the game ending view.
 * It extends the {@code AbstractJavaFxView} class and implements the {@code GameView} interface.
 */
public class JavaFxEndingView extends AbstractJavaFxView {

    private final Scene scene;
    private final EndState main;
    /**
     * Creates a new instance of {@code JavaFxEndingView} with the specified end controller, stage, scene, and end state.
     *
     * @param endController the end controller
     * @param stage         the stage
     * @param scene         the scene
     * @param endState      the end state
     */
    public JavaFxEndingView(final ViewController endController, final Stage stage,
                            final Scene scene, final EndState endState) {
        super(endController, stage, scene);
        this.scene = scene;
        this.main = endState;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render() {
        Platform.runLater(() -> {
            StackPane root = new StackPane();
            root.getChildren().clear();
            VBox vbox = new VBox();
            Label title = new Label(main.getMessage());
            vbox.getChildren().add(title);
            vbox.setAlignment(Pos.CENTER);
            vbox.setSpacing(10);
            root.getChildren().add(vbox);
            root.setAlignment(Pos.CENTER);
            this.scene.setCursor(Cursor.NONE);
            this.getScene().setRoot(root);
        });
    }
}
