package it.tbt.view.javaFx;

import it.tbt.controller.modelmanager.FightState;
import it.tbt.controller.viewcontrollermanager.api.FightController;
import it.tbt.controller.viewcontrollermanager.api.ViewController;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class JavaFxFightView extends AbstractJavaFxView {

    private FightController fightController;
    private FightState main;

    public JavaFxFightView(Stage stage, Scene scene, ViewController fightController, FightState main) {
        super(fightController, stage, scene);
        this.fightController = (FightController) fightController;
        this.main = main;

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                System.out.println("ff15" + event.getCode());
                fightController.onKeyPressed(event.getCode().getCode());
            }
        });
    }

    private void setSelectedLable(Label l1, Label l2, Label l3, Label l4) {
        switch (main.getSelectedTargetIndex()) {
            case 0:
                l1.setTextFill(Color.web("#0000FF"));
                break;
            case 1:
                l2.setTextFill(Color.web("#0000FF"));
                break;
            case 2:
                l3.setTextFill(Color.web("#0000FF"));
                break;
            case 3:
                l4.setTextFill(Color.web("#0000FF"));
                break;
        }
    }

    private void setLableTextAlly(Label name, Label hp, int i) {
        if (main.getAllies().get(i).getMaxHealth() != 0) {
            name.setText(main.getAllies().get(i).getName());
            hp.setText(main.getAllies().get(i).getHealth() + "/" + main.getAllies().get(i).getMaxHealth());
        } else {
            name.setText("");
            hp.setText("");
        }
    }

    private void setLableTextEnemy(Label name, Label hp, int i) {
        if (main.getEnemies().get(i).getMaxHealth() != 0) {
            name.setText(main.getEnemies().get(i).getName());
            hp.setText(main.getEnemies().get(i).getHealth() + "/" + main.getEnemies().get(i).getMaxHealth());
        } else {
            name.setText("");
            hp.setText("");
        }
    }

    @Override
    public void render() {
        Platform.runLater(() -> {
            Group root = new Group();
            root.getChildren().clear();

            BorderPane pane = new BorderPane();
            // Creazione delle etichette per gli alleati
            Label allyLabel1 = new Label();
            Label allyLabel2 = new Label();
            Label allyLabel3 = new Label();
            Label allyLabel4 = new Label();
            Label allyHpLabel1 = new Label();
            Label allyHpLabel2 = new Label();
            Label allyHpLabel3 = new Label();
            Label allyHpLabel4 = new Label();

            setLableTextAlly(allyLabel1, allyHpLabel1, 0);
            setLableTextAlly(allyLabel2, allyHpLabel2, 1);
            setLableTextAlly(allyLabel3, allyHpLabel3, 2);
            setLableTextAlly(allyLabel4, allyHpLabel4, 3);

            // Creazione del layout per le etichette degli alleati
            GridPane allyLabelsGrid = new GridPane();
            allyLabelsGrid.setAlignment(Pos.BOTTOM_CENTER);
            allyLabelsGrid.setHgap(root.getLayoutX());
            allyLabelsGrid.addRow(0, allyLabel1, allyLabel2, allyLabel3, allyLabel4);
            allyLabelsGrid.addRow(1, allyHpLabel1, allyHpLabel2, allyHpLabel3, allyHpLabel4);

            // Creazione delle etichette per i nemici
            Label enemyLabel1 = new Label();
            Label enemyLabel2 = new Label();
            Label enemyLabel3 = new Label();
            Label enemyLabel4 = new Label();
            Label enemyHpLabel1 = new Label();
            Label enemyHpLabel2 = new Label();
            Label enemyHpLabel3 = new Label();
            Label enemyHpLabel4 = new Label();

            setLableTextEnemy(enemyLabel1, enemyHpLabel1, 0);
            setLableTextEnemy(enemyLabel2, enemyHpLabel2, 1);
            setLableTextEnemy(enemyLabel3, enemyHpLabel3, 2);
            setLableTextEnemy(enemyLabel4, enemyHpLabel4, 3);

            // Creazione del layout per le etichette dei nemici
            GridPane enemyLabelsGrid = new GridPane();
            enemyLabelsGrid.setAlignment(Pos.TOP_CENTER);
            enemyLabelsGrid.setHgap(10);
            enemyLabelsGrid.addRow(0, enemyLabel1, enemyLabel2, enemyLabel3, enemyLabel4);
            enemyLabelsGrid.addRow(1, enemyHpLabel1, enemyHpLabel2, enemyHpLabel3, enemyHpLabel4);

            // Creazione dei bottoni
            Button skillButton = new Button("Skill");
            Button attackButton = new Button("Attacco");
            Button potionButton = new Button("Pozione");
            Button antidoteButton = new Button("Antidoto");

            if (main.isUsingSkill()) {
                skillButton.setStyle("-fx-background-color: red;");
                setSelectedLable(enemyLabel1, enemyLabel2, enemyLabel3, enemyLabel4);
            } else if (main.isUsingAntidote()) {
                antidoteButton.setStyle("-fx-background-color: red;");
                setSelectedLable(allyLabel1, allyLabel2, allyLabel3, allyLabel4);
            } else if (main.isUsingPotion()) {
                potionButton.setStyle("-fx-background-color: red;");
                setSelectedLable(allyLabel1, allyLabel2, allyLabel3, allyLabel4);
            } else {
                attackButton.setStyle("-fx-background-color: red;");
                setSelectedLable(enemyLabel1, enemyLabel2, enemyLabel3, enemyLabel4);
            }

            // Creazione del layout per i bottoni
            VBox buttonBox = new VBox(root.getLayoutX());
            buttonBox.setAlignment(Pos.CENTER_RIGHT);
            buttonBox.getChildren().addAll(skillButton, attackButton, potionButton, antidoteButton);

            for (javafx.scene.Node b : buttonBox.getChildren()) {
                b.setFocusTraversable(false);
            }

            // Posizionamento delle etichette e dei bottoni nel layout principale
            pane.setTop(enemyLabelsGrid);
            pane.setCenter(buttonBox);
            pane.setBottom(allyLabelsGrid);

            root.getChildren().add(pane);
            this.getScene().setRoot(root);
        });
    }
}
