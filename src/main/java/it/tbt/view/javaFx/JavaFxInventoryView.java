package it.tbt.view.javaFx;

import it.tbt.commons.customTypes.ItemPair;
import it.tbt.controller.modelmanager.InventoryPhase;
import it.tbt.controller.modelmanager.InventoryState;
import it.tbt.controller.viewcontrollermanager.api.ViewController;
import it.tbt.model.entities.characters.Ally;
import it.tbt.model.entities.characters.Status;
import it.tbt.model.entities.items.Item;
import it.tbt.view.api.GameView;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Map;

import static java.lang.Integer.MAX_VALUE;

public class JavaFxInventoryView extends AbstractJavaFxView implements GameView {

    private Scene scene;
    private ViewController inventoryController;
    private InventoryState inventoryState;

    /**
     * @param inventoryController
     * @param stage
     * @param scene
     * @param inventoryState
     **/
    protected JavaFxInventoryView(ViewController inventoryController, Stage stage, Scene scene, InventoryState inventoryState) {
        super(inventoryController, stage, scene);
        this.scene = scene;
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
            VBox root = new VBox ();


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
            if(inventoryList.getChildren().size() == 0){
                Label label = new Label("Inventario vuoto");
                inventoryList.getChildren().add(label);
            }

            // Create a label for the inventory title
            Label inventoryTitle = new Label("Inventory");
            inventoryTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
            inventoryList.setFocusTraversable(false);

            // Create a VBox for the inventory
            VBox inventoryBox = new VBox(10, inventoryTitle, inventoryList);
            inventoryBox.setPadding(new Insets(10));

            VBox memberBox = new VBox ();
            Label memberTitle = new Label("Member details");
            memberTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
            memberTitle.setFocusTraversable(false);
            memberBox.getChildren ().add (memberTitle);
            // Create party member list
            VBox partyList = new VBox();
            counter = 0;
            for (Ally ally:
                    this.inventoryState.getPartyMembers()) {
                Label label = new Label(ally.getName());
                if(counter<this.inventoryState.getPartySize()){
                    label.setStyle("-fx-border-color: red;");
                }
                if(inventoryState.getPhase() == InventoryPhase.MEMBERS && inventoryState.getAllySelected() != -1) {
                    if (counter == inventoryState.getAllySelected ()) {
                        label.setStyle (label.getStyle () + "-fx-background-color: lightblue;");
                    }
                }
                if((inventoryState.getPhase() == InventoryPhase.INVENTORY && inventoryState.getItemSelected() != -1) ||
                        (inventoryState.getPhase() == InventoryPhase.MEMBERS && inventoryState.getAllySelected() == -1) ||
                        (inventoryState.getPhase() == InventoryPhase.MEMBERS && inventoryState.getAllySelected() != -1)){
                    if(counter == inventoryState.getAllyFocused()){
                        label.setStyle(label.getStyle()+"-fx-background-color: yellow;");
                        memberBox.getChildren ().addAll (new Label ("Name: " + ally.getName ()),
                                                            new Label ("HP: " + ally.getHealth ()+"/"+ally.getMaxHealth ()),
                                                            new Label ("Status: " + ally.getStatuses ().toString ()),
                                                            new Label ("Skill: " + ally.getSkills ().get (0).getAttackMultiplier ()),
                                                            new Label ("Attack: " + ally.getAttack ()),
                                                            new Label ("Speed: " + ally.getSpeed ()),
                                                            new Label ("Weapon: "+ (ally.getWeapon ().isPresent ()?ally.getWeapon ().get ().toString ():"") ),
                                                            new Label ("Armor: " + (ally.getArmor ().isPresent ()?ally.getArmor ().get ().toString ():""))
                                );


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
            GridPane pane = new GridPane ();
            pane.setHgap(10);
            ColumnConstraints column1 = new ColumnConstraints();
            ColumnConstraints column2 = new ColumnConstraints();
            ColumnConstraints column3 = new ColumnConstraints();
            column1.setPercentWidth(33.33);
            column2.setPercentWidth(33.33);
            column3.setPercentWidth(33.33);
            pane.getColumnConstraints().addAll(column1, column2, column3);
            pane.add(inventoryBox, 0, 0);
            pane.add(partyBox, 1, 0);
            pane.add(memberBox, 2,0);
            pane.setStyle("-fx-background-color: #F5F5F5;"); // Set background color
            root.getChildren().add(pane);
            root.setAlignment (Pos.TOP_CENTER);
            scene.setRoot(root);

        });
    }
}
