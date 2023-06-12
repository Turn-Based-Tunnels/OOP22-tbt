package it.tbt.engine.impl;

import it.tbt.engine.api.Game;
import it.tbt.engine.api.GameLoop;
import javafx.animation.AnimationTimer;

public class FixedTimeGameLoop implements GameLoop {

    private static final long SECOND_IN_MILLISECOND = 1_000_000_000;
    private long timeSlice = SECOND_IN_MILLISECOND / 30;
    private long lastUpdateTime;
    private long timeAccumulator;
    private Boolean updated = false;
    private Boolean consistent = true;
    private final Game game;

    public FixedTimeGameLoop(final Game g) {
        super();
        this.game = g;
        this.lastUpdateTime = System.nanoTime();
        this.game.initialize();
        this.game.render();
    }
    /**
     *
     */
    @Override
    public void loop() {
        final long elapsedTime = System.nanoTime() - lastUpdateTime;

        lastUpdateTime += elapsedTime;
        timeAccumulator += elapsedTime;

        if(this.game.handleInput()) {
            updated = true;
        }

        while (timeAccumulator > timeSlice) {
            this.game.update(timeSlice);
            updated = true;
            timeAccumulator -= timeSlice;
        }

        if (updated) {
            this.game.render();
            updated = false;
        }
    }

    public Boolean isConsistent() {
        return !this.game.isOver();
    }
}
