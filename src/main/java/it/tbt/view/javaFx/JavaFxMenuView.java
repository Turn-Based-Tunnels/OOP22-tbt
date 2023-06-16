package it.tbt.view.javaFx;

import it.tbt.controller.viewcontrollermanager.api.ViewController;
import it.tbt.controller.viewcontrollermanager.impl.MainMenuController;
import it.tbt.controller.modelmanager.MenuState;
import it.tbt.model.menu.api.MenuButton;
import it.tbt.model.menu.api.MenuItem;
import it.tbt.model.menu.impl.MenuSelect;
import it.tbt.view.api.GameView;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import javax.swing.text.View;
import java.util.*;


public class JavaFxMenuView extends /*ResizableApp*/ AbstractJavaFxView implements GameView {

    private Scene scene;
    private Group root;
    private ViewController menuController;
    private MenuState main;

    public void setData(MenuState modelState){
        this.main = modelState;
    }

    public JavaFxMenuView(ViewController menuController, Stage stage, Scene scene, Group root, MenuState menuState) {
        super(menuController, stage, scene, root);
        this.scene = scene;
        this.root = root;
        this.menuController = menuController;
        this.main = menuState;

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                System.out.println("ff15" + event.getCode());
                menuController.onKeyPressed(event.getCode().getCode());
            }
        });
    }

    /*@Override
    public void customizeVBox(VBox vbox) {
        vbox.getChildren().clear();
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
                button.setFont(buttonFont);
                button.setFocusTraversable(false);;
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
                button.setFocusTraversable(false);;
                vbox.getChildren().addAll(label, button);
            }


            count++;
        }


        // Add menu buttons
        /*Button startButton = new Button("Start Game");
        startButton.setOnAction(e -> {
            // Start the game
            System.out.println("Starting the game...");
        });

        Button optionsButton = new Button("Options");
        optionsButton.setOnAction(e -> {
            // Show options menu
            System.out.println("Opening options menu...");
        });

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> {
            // Exit the application
            Platform.exit();
        });

        // Customize menu buttons
        Font buttonFont = Font.font("Arial", FontWeight.BOLD, 16);
        startButton.setFont(buttonFont);
        optionsButton.setFont(buttonFont);
        exitButton.setFont(buttonFont);

        // Add buttons to the VBox
        vbox.getChildren().addAll(startButton, optionsButton, exitButton); 


        //vbox.setDisable(true);
        //vbox.setFocusTraversable(false);
        vbox.setOpacity(1);
        vbox.setStyle("-fx-opacity: 1;");
    }
    */

    private void updateAspectRatio(String selectedAspectRatio) {

        Map Rateos = new HashMap<String, Double>();
        Rateos.put("16:9", 16.0/9.0 );
        Rateos.put("4:3", 4.0/3.0);
        Rateos.put("3:2", 3.0/2.0);

       // ResizableApp.ASPECT_RATIO = (Double) Rateos.get(selectedAspectRatio);

    }
    /*
    void update(Stage primaryStage){
        customizeVBox((VBox) ((StackPane)primaryStage.getScene().getRoot()).getChildren().get(1));
    }*/

    /*
    @Override
    public void start(Stage primaryStage) {
        List<MenuItem> test = new ArrayList<>();
        NavigableMap<String, Double> rateos = new TreeMap<>();
        rateos.put("16:9", 16.0 / 9.0);
        rateos.put("4:3", 4.0 / 3.0);
        rateos.put("3:2", 3.0 / 2.0);
        test.addAll(Arrays.asList(new it.tbt.model.menu.impl.MenuButton("New Game"), new it.tbt.model.menu.impl.MenuButton("Exit"), new MenuSelect<Double>("Proporzioni", rateos), new it.tbt.model.menu.impl.MenuButton("Full Screen")));

        main = new MenuModel(test);
        super.start(primaryStage); // Call parent class's start method
        Scene scene = primaryStage.getScene();
        scene.addEventFilter(MouseEvent.ANY, Event::consume);
        // scene.addEventFilter(KeyEvent.ANY, Event::consume);
        scene.setFill(Color.BLACK); // Set the scene background color explicitly
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                System.out.println("ff15" + event.getCode());
                main.checkInput(event.getCode().getCode());
                update(primaryStage);
            }
        });
    }

     */


    @Override
    public void render() {
        Platform.runLater(() -> {
            root.getChildren().clear();
            VBox vbox = new VBox();
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
                    vbox.getChildren().addAll(label, button);
                }


                count++;
            }
            vbox.setAlignment(Pos.CENTER);
            root.getChildren().add(vbox);

        });
    }
}
