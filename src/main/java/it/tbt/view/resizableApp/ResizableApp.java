package it.tbt.view.resizableApp;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ResizableApp extends Application {

    protected static double ASPECT_RATIO = 16.0 / 9.0; // Desired aspect ratio
    public VBox vbox;
    public Rectangle content;
    public Stage primaryStageclone;
    @Override
    public void start(Stage primaryStage) {
        primaryStageclone = primaryStage;
        primaryStage.setTitle("Aspect Ratio Application");

        StackPane root = new StackPane();

        content = new Rectangle();
        content.setFill(Color.BLUE);

        vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);

        customizeVBox(vbox); // Customize the VBox with menu items

        root.getChildren().addAll(content, vbox);
        StackPane.setAlignment(content, Pos.CENTER);

        Scene scene = new Scene(root, Color.BLACK);
        scene.setFill(Color.BLACK); // Set the scene background color explicitly


        primaryStage.widthProperty().addListener(new it.tbt.view.resizableApp.ResizeListener(primaryStage, content, vbox));
        primaryStage.heightProperty().addListener(new it.tbt.view.resizableApp.ResizeListener(primaryStage, content, vbox));

        setInitialSize(primaryStage);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void customizeVBox(VBox vbox) {
        Text title = new Text("Game Main Menu");
        title.setFill(Color.WHITE);
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        vbox.getChildren().addAll(title);
    }


    private void setInitialSize(Stage stage) {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();

        double width = screenWidth * 0.8;
        double height = width / ASPECT_RATIO;

        if (height > screenHeight * 0.8) {
            height = screenHeight * 0.8;
            width = height * ASPECT_RATIO;
        }

        stage.setWidth(width);
        stage.setHeight(height);
    }



}