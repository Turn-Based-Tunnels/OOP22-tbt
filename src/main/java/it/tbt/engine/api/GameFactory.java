package it.tbt.engine.api;

import javafx.stage.Stage;

/**

 */

public interface GameFactory {

    /**
     *
     */
    public Game createGame(GameFramework gameFramework);

    public Game createJavaFxGame(Stage stage);

}
