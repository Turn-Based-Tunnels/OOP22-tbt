package it.tbt.engine.impl;

import it.tbt.engine.api.GameLoop;
import javafx.animation.AnimationTimer;

/**
 * GameLoopManager implemented with an JavaFx AnimationTimer.
 */
public class AnimationTimerGameLoopManager {
    private GameLoop loop;
    private AnimationTimer gameLoopAnimationTimer;

    /**
     * @param gameLoop that the Manger will manage.
     */
    public AnimationTimerGameLoopManager(final GameLoop gameLoop) {
        this.loop = gameLoop;
        var gameLoopAnimationTimer = new AnimationTimer() {
            @Override
            public void handle(final long now) {
                if (loop.isConsistent()) {
                    loop.loop();
                } else {
                    this.stop();
                }
            }
        };
        this.gameLoopAnimationTimer = gameLoopAnimationTimer;
    }

    /**
     * Starts the looping.
     */
    public void startLoop() {
        gameLoopAnimationTimer.start();
    }
}