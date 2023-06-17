package it.tbt.view.javaFx;

import it.tbt.commons.customTypes.ItemPair;
import it.tbt.controller.modelmanager.InventoryPhase;
import it.tbt.controller.modelmanager.InventoryState;
import it.tbt.controller.viewcontrollermanager.api.ViewController;
import it.tbt.model.entities.characters.Ally;
import it.tbt.model.entities.items.Item;
import it.tbt.view.api.GameView;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Map;

public class JavaFxInventoryView extends AbstractJavaFxView implements GameView {

    private ViewController inventoryController;
    private InventoryState inventoryState;

    /**
     * @param inventoryController
     * @param stage
     * @param scene
     * @param root
     * @param inventoryState
     **/
    protected JavaFxInventoryView(ViewController inventoryController, Stage stage, Scene scene, Group root, InventoryState inventoryState) {
        super(inventoryController, stage, scene);
        this.inventoryController = inventoryController;
        this.inventoryState = inventoryState;

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                System.out.println("ff15" + event.getCode());
                inventoryController.onKeyPressed(event.getCode().getCode());
            }
        });

    }

    @Override
    public void render() {
        Platform.runLater(() -> {
            Group root = new Group();
            root.getChildren().clear();
            int counter = 0;
            VBox inventoryList = new VBox();
            for (Map.Entry<Item, Integer> x:
                 inventoryState.getInventory().entrySet()) {
                Label label = new Label(((new ItemPair(x.getKey(),x.getValue())).toString()));
                if(inventoryState.getPhase() == InventoryPhase.INVENTORY && inventoryState.getItemSelected() == -1){
                    if(counter == inventoryState.getItemFocus()){
                        label.setStyle("-fx-background-color: yellow;");
                    }
                }
                if(inventoryState.getPhase() == InventoryPhase.INVENTORY && inventoryState.getItemSelected() != -1){
                    if(counter == inventoryState.getItemSelected()){
                        label.setStyle("-fx-background-color: lightblue;");
                    }
                    if(counter == inventoryState.getItemFocus()){
                        label.setStyle("-fx-background-color: yellow;");
                    }
                }
                inventoryList.getChildren().add(label);
                counter++;
            }

            // Create a label for the inventory title
            Label inventoryTitle = new Label("Inventory");
            inventoryTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
            inventoryList.setFocusTraversable(false);

            // Create a VBox for the inventory
            VBox inventoryBox = new VBox(10, inventoryTitle, inventoryList);
            inventoryBox.setPadding(new Insets(10));

            // Create party member list
            VBox partyList = new VBox();
            counter = 0;
            for (Ally ally:
                    this.inventoryState.getPartyMembers()) {
                Label label = new Label(ally.getName());
                if(inventoryState.getPhase() == InventoryPhase.INVENTORY && inventoryState.getItemSelected() != 1){
                    if(counter == inventoryState.getAllyFocused()){
                        label.setStyle("-fx-background-color: yellow;");
                    }
                }
                if(inventoryState.getPhase() == InventoryPhase.MEMBERS && inventoryState.getAllySelected() == -1){
                    if(counter == inventoryState.getAllyFocused()){
                        label.setStyle("-fx-background-color: yellow;");
                    }
                }
                if(inventoryState.getPhase() == InventoryPhase.MEMBERS && inventoryState.getAllySelected() != -1){
                    if(counter == inventoryState.getAllySelected()){
                        label.setStyle("-fx-background-color: lightblue;");
                    }
                    if(counter == inventoryState.getAllyFocused()){
                        label.setStyle("-fx-background-color: yellow;");
                    }
                }
                partyList.getChildren().add(label);
                counter++;
            }


            // Create a label for the party title
            Label partyTitle = new Label("Party");
            partyTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
            partyList.setFocusTraversable(false);
            // Create a VBox for the party members
            VBox partyBox = new VBox(10, partyTitle, partyList);
            partyBox.setPadding(new Insets(10));

            // Create a BorderPane as the root pane
            BorderPane pane = new BorderPane();
            pane.setLeft(inventoryBox);
            pane.setRight(partyBox);
            pane.setStyle("-fx-background-color: #F5F5F5;"); // Set background color
            root.getChildren().add(pane);

        });
    }
}
