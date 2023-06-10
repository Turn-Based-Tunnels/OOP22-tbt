package it.tbt.engine.impl;

import it.tbt.View.api.GameViewFactory;
import it.tbt.View.javaFx.JavaFxViewFactory;
import it.tbt.engine.api.Game;
import javafx.stage.Stage;

public class GameFactory {

    public static Game createJavaFxGame(Stage stage) {
        GameViewFactory viewFactory = new JavaFxViewFactory(stage);
        return new GameImpl(viewFactory);
    }

    public static Game createJavaSwingGame() {
        return null;
    }

}