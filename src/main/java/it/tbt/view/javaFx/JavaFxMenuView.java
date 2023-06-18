package it.tbt.view.javaFx;

import it.tbt.controller.viewcontrollermanager.api.ViewController;
import it.tbt.controller.modelmanager.MenuState;
import it.tbt.model.menu.api.MenuButton;
import it.tbt.model.menu.api.MenuItem;
import it.tbt.model.menu.impl.MenuSelect;
import it.tbt.view.api.GameView;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.*;


public class JavaFxMenuView extends /*ResizableApp*/ AbstractJavaFxView implements GameView {

    private Scene scene;
    private ViewController menuController;
    private MenuState main;

    public JavaFxMenuView(ViewController menuController, Stage stage, Scene scene, MenuState menuState) {
        super(menuController, stage, scene);
        this.scene = scene;
        this.menuController = menuController;
        this.main = menuState;
    }



    @Override
    public void render() {
        Platform.runLater(() -> {
            StackPane root = new StackPane ();
            root.getChildren().clear();
            VBox vbox = new VBox();
            Label title = new Label (main.getTitle ());
            vbox.getChildren ().add (title);
            int count = 0;
            System.out.println(main.getFocus());
            for (MenuItem item:
                    main.getItems()) {
                if(item instanceof MenuButton){
                    Button button = new Button(item.getText());
                    Font buttonFont = Font.font("Arial", FontWeight.BOLD, 16);
                    if(count == main.getFocus()){
                        button.setStyle("-fx-background-color: red;");
                    }
                    button.setOnAction((event) -> {

                    });
                    button.setFont(buttonFont);
                    button.setFocusTraversable(false);
                    button.setMinWidth (vbox.getPrefWidth());
                    vbox.getChildren().add(button);
                }
                else if(item instanceof it.tbt.model.menu.api.MenuSelect){
                    Label label = new Label(item.getText());
                    label.setFocusTraversable(false);
                    Button button = new Button("<      " + ((MenuSelect)item).getLabel() + "      >");
                    Font buttonFont = Font.font("Arial", FontWeight.BOLD, 16);
                    if(count == main.getFocus()){
                        button.setStyle("-fx-background-color: red;");
                    }
                    button.setFont(buttonFont);
                    button.setFocusTraversable(false);
                    button.setMinWidth (vbox.getPrefWidth());
                    vbox.getChildren().addAll(label, button);

                }
                count++;
            }
            vbox.setAlignment(Pos.CENTER);
            vbox.setSpacing (10);
            root.getChildren().add(vbox);
            root.setAlignment (Pos.CENTER);
            this.scene.setCursor(Cursor.NONE);
            this.getScene().setRoot(root);

        });
    }
}
