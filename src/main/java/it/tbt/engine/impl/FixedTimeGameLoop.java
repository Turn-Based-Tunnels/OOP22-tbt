package it.tbt.engine.impl;

import it.tbt.engine.api.Game;
import it.tbt.engine.api.GameLoop;
import javafx.animation.AnimationTimer;

public class FixedTimeGameLoop implements GameLoop {

    private static final long SECOND_IN_MILLISECOND = 1_000_000_000;
    private long timeSlice = SECOND_IN_MILLISECOND / 60;
    private long lastUpdateTime;
    private long timeAccumulator;
    private Boolean updated = false;
    private Boolean running = true;
    private final Game game;

    public FixedTimeGameLoop(final Game g) {
        super();
        this.game = g;
        this.lastUpdateTime = System.nanoTime();
        this.game.render();
        var gameLoopAnimationTimer = new AnimationTimer() {
            @Override
            public void handle(final long now) {
                run();
            }
        };
        gameLoopAnimationTimer.start();
    }

    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
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

    /**
     *
     */
    @Override
    public void start() {
        this.running = true;
        new Thread(this).start();
    }

    /**
     *
     */
    @Override
    public void stop() {
        this.running = false;
    }
}
