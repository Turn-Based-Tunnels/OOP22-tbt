package it.tbt.engine.impl;

import it.tbt.engine.api.Game;
import it.tbt.engine.api.GameLoop;
import javafx.animation.AnimationTimer;

public class JavaFxGameLoopManager {

    private GameLoop loop;
    private AnimationTimer gameLoopAnimationTimer;

    public JavaFxGameLoopManager(GameLoop gameLoop) {
        this.loop = gameLoop;
        var gameLoopAnimationTimer = new AnimationTimer() {
            @Override
            public void handle(final long now) {
                if(loop.isConsistent()) {
                    loop.loop();
                } else {
                    this.stop();
                }
            }
        };
        this.gameLoopAnimationTimer = gameLoopAnimationTimer;
    }

    public void startLoop() {
        gameLoopAnimationTimer.start();
    }
}
